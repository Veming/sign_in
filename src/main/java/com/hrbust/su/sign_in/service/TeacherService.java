package com.hrbust.su.sign_in.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.ClassRecord;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.bean.Teacher;
import com.hrbust.su.sign_in.dao.ClassRecordDao;
import com.hrbust.su.sign_in.dao.StudentDao;
import com.hrbust.su.sign_in.dao.TeacherDao;
import com.hrbust.su.sign_in.dao.UserDao;
import com.hrbust.su.sign_in.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ClassRecordDao classRecordDao;

    @Autowired
    StudentDao studentDao;

    public String getTeacherInfoBySession(String sessionKey){
        String tid = userDao.getIdBySessionKey(sessionKey);
        Teacher teacher = teacherDao.getTeacherByTid(tid);
        JSONObject json = (JSONObject) JSONObject.toJSON(teacher);
        return json.toString();
    }

    public String createSourceCode(JSONObject json){
        // 提取 json 数据
        String sessionKey = json.getString("sessionKey");
        String latitude = json.getString("latitude");
        String longitude = json.getString("longitude");
        // 获取教师tid
        String tid = userDao.getIdBySessionKey(sessionKey);
        String sourceCode = Util.generating6BitRandomNumbers();
        // 生成时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 存放实体类
        ClassRecord classRecord = new ClassRecord();
        classRecord.setCourseCode(sourceCode);
        classRecord.setCreateTime(sdf.format(date));
        classRecord.setLongitude(longitude);
        classRecord.setLatitude(latitude);
        classRecord.setTid(tid);

        JSONObject reJson = new JSONObject();

        try {
            // 上课记录保存进 保存进数据库
            ClassRecord tmp = classRecordDao.save(classRecord);
            Jedis jedis = new Jedis();
            jedis.set("sourceCode:"+sourceCode+":tid",tid);
            jedis.set("sourceCode:"+sourceCode+":longitude",longitude);
            jedis.set("sourceCode:"+sourceCode+":latitude",latitude);
            jedis.set("sourceCode:"+sourceCode+":crid",tmp.getRid().toString());
            // 设置 课程码 生存时间
            jedis.expire("sourceCode:"+sourceCode+":tid",900);
            jedis.expire("sourceCode:"+sourceCode+":longitude",900);
            jedis.expire("sourceCode:"+sourceCode+":latitude",900);
            jedis.expire("sourceCode:"+sourceCode+":crid",900);
        }catch (Exception e){
            System.out.println("createSourceCode error 可能数据库服务未运行");
            reJson.put("state","fail");
            return reJson.toString();
        }
        reJson.put("state","success");
        reJson.put("sourceCode",sourceCode);
//        返回 json 字符串
        return reJson.toString();
    }

    public String checkSourceCode(JSONObject json) {
        String sourceCode = json.getString("sourceCode");
        Jedis jedis = new Jedis();
        JSONObject reJson = new JSONObject();
        try {
//            System.out.println("sourceCode:"+sourceCode+":tid");
//            System.out.println(jedis.exists("sourceCode:"+sourceCode+":tid"));
            if(jedis.exists("sourceCode:"+sourceCode+":tid")){
                reJson.put("isExist","true");
            }
            else {
                reJson.put("isExist","false");
            }
        }catch (Exception e){
            System.out.println("请检查 Redis 数据库服务");
            reJson.put("state","fail");
            return reJson.toString();
        }
        reJson.put("state","success");
        System.out.println(reJson.toString());
        return reJson.toString();
    }

    public String whoHasCheckIn(JSONObject json){
        String sourceCode = json.getString("sourceCode");
        JSONObject reJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        Jedis jedis = new Jedis();

        try {
            long len = jedis.llen("sourceCode:" + sourceCode + ":idList");
            for (int i = 0; i < len; i++) {
                String sid = jedis.lindex("sourceCode:" + sourceCode + ":idList", i);
                String name = jedis.lindex("sourceCode:" + sourceCode + ":nameList", i);
                String distance = jedis.lindex("sourceCode:" + sourceCode + ":distanceList", i);
                JSONObject tempJson = new JSONObject();
                tempJson.put("sid",sid);
                tempJson.put("name",name);
                tempJson.put("distance",distance);
                jsonArray.add(tempJson);
            }
            reJson.put("number",len);
            reJson.put("list",jsonArray);
            reJson.put("state","success");
        }catch (Exception e ){
            System.out.println("whoHasCheckIn fail");
            reJson.put("state","fail");
            return reJson.toString();
        }
        return reJson.toString();
    }
}
