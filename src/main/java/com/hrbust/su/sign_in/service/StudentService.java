package com.hrbust.su.sign_in.service;

import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.CheckInRecord;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.dao.CheckInRecordDao;
import com.hrbust.su.sign_in.dao.StudentDao;
import com.hrbust.su.sign_in.dao.UserDao;
import com.hrbust.su.sign_in.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CheckInRecordDao checkInRecordDao;

    public String signIn(JSONObject data) {
        Jedis jedis = new Jedis();
        JSONObject reJson = new JSONObject();
        String sessionKey = data.getString("sessionKey");
        String fLng = data.getString("longitude");
        String fLat = data.getString("latitude");
        String sourceCode = data.getString("sourceCode");
        System.out.println(data.toString());
        try {
            if (! jedis.exists("sourceCode:"+sourceCode+":tid")){
                reJson.put("state","fail");
                return reJson.toString();
            }
            String tLng = jedis.get("sourceCode:" + sourceCode + ":longitude");
            String tLat = jedis.get("sourceCode:" + sourceCode + ":latitude");
            String crid = jedis.get("sourceCode:" + sourceCode + ":crid");
            //计算两坐标之间的距离
            String distance = Util.ranging(fLat, fLng, tLat, tLng);
            // 获取学生基本信息
            String sid = userDao.getIdBySessionKey(sessionKey);
            Student student = studentDao.getStudentBySid(sid);
            // 格式化日期
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            // 生成新的签到记录元组
            CheckInRecord checkInRecord = new CheckInRecord();
            checkInRecord.setCheckInTime(sdf.format(date));
            checkInRecord.setSourceCode(sourceCode);
            checkInRecord.setLatitude(fLat);
            checkInRecord.setLongitude(fLng);
            checkInRecord.setDistance(distance);
            checkInRecord.setStuId(sid);
            checkInRecord.setCrid(crid);
            checkInRecordDao.save(checkInRecord);
            // 检查是否存在过签到记录 并存入Redis数据库中， 若不存在生成新的list 并设置生存时间
            if(! jedis.exists("sourceCode:" + sourceCode + ":idList")){
                jedis.lpush("sourceCode:" + sourceCode + ":idList",sid);
                jedis.lpush("sourceCode:" + sourceCode + ":distanceList",distance);
                jedis.lpush("sourceCode:" + sourceCode + ":nameList",student.getRealName());
                jedis.expire("sourceCode:" + sourceCode + ":idList",900);
                jedis.expire("sourceCode:" + sourceCode + ":distanceList",900);
                jedis.expire("sourceCode:" + sourceCode + ":nameList",900);
            }
            else {
                jedis.lpush("sourceCode:" + sourceCode + ":idList",sid);
                jedis.lpush("sourceCode:" + sourceCode + ":distanceList",distance);
                jedis.lpush("sourceCode:" + sourceCode + ":nameList",student.getRealName());
            }

            jedis.set("sid:"+sid+":hasSigned","true");
            jedis.expire("sid:"+sid+":hasSigned",900);

        } catch (Exception e) {
            System.out.println("sign in fail");
            reJson.put("state", "fail");
            return reJson.toString();
        }

        reJson.put("state", "success");
        return reJson.toString();
    }

    public String getStuInfoBySession(String sessionKey) {
        String sid = userDao.getIdBySessionKey(sessionKey);
        Student student = studentDao.getStudentBySid(sid);
        JSONObject json = (JSONObject) JSONObject.toJSON(student);
        return json.toString();
    }

    public List<Student> findStudentByClass(String sessionKey) {
        String sid = userDao.getIdBySessionKey(sessionKey);
        Student student = studentDao.getStudentBySid(sid);

        List<Student> StudentList =
                studentDao.getStudentsByProfessionAndGradeAndClassName
                        (student.getProfession(), student.getGrade(), student.getClassName(), sid);
//        JSONObject json = (JSONObject) JSONObject.toJSON(StudentList);
        return StudentList;
    }


    public String changeStuState(String sid) {
        try {
            studentDao.setState(sid);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }

    public String checkSignIn(JSONObject json) {
        String sessionKey = json.getString("sessionKey");
        String sid = userDao.getIdBySessionKey(sessionKey);
        Jedis jedis = new Jedis();
        JSONObject reJson = new JSONObject();
        try{
            if (jedis.exists("sid:"+sid+":hasSigned")){
                reJson.put("isExist","true");
            }
            reJson.put("state","success");
        }catch (Exception e){
            System.out.println("checkSignIn() ERROR!");
            reJson.put("state","fail");
        }
        return reJson.toString();
    }
}
