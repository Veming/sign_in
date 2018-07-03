package com.hrbust.su.sign_in.service;

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
        System.out.println(tid);
        String sourceCode = Util.generating6BitRandomNumbers();
        System.out.println(sourceCode);
        //保存进Redis数据库中
        Jedis jedis = new Jedis();
        jedis.set("sourceCode_"+sourceCode+"_tid",tid);
        jedis.set("sourceCode_"+sourceCode+"_longitude",longitude);
        jedis.set("sourceCode_"+sourceCode+"_latitude",latitude);
        // 设置 课程码 生存时间
        jedis.expire("sourceCode:"+sourceCode+"_tid",900);
        jedis.expire("sourceCode_"+sourceCode+"_longitude",900);
        jedis.expire("sourceCode_"+sourceCode+"_latitude",900);
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
            classRecordDao.save(classRecord);
        }
        catch (Exception e){
            reJson.put("state","fail");
            return reJson.toString();
        }

        reJson.put("state","success");
        reJson.put("sourceCode",sourceCode);
//        返回 json 字符串
        return reJson.toString();
    }

}
