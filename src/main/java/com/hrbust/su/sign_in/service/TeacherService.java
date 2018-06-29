package com.hrbust.su.sign_in.service;

import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.bean.Teacher;
import com.hrbust.su.sign_in.dao.StudentDao;
import com.hrbust.su.sign_in.dao.TeacherDao;
import com.hrbust.su.sign_in.dao.UserDao;
import com.hrbust.su.sign_in.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Random;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    UserDao userDao;

    public String getTeacherInfoBySession(String sessionKey){
        String tid = userDao.getIdBySessionKey(sessionKey);
        Teacher teacher = teacherDao.getTeacherByTid(tid);
        JSONObject json = (JSONObject) JSONObject.toJSON(teacher);
        return json.toString();
    }

    public String createSourceCode(String sessionKey){
        System.out.println(sessionKey);
//        获取教师tid
        String tid = userDao.getIdBySessionKey(sessionKey);
        System.out.println(tid);
        String sourceCode = Util.generating6BitRandomNumbers();
        System.out.println(sourceCode);

        //保存进Redis数据库中
        Jedis jedis = new Jedis();
        jedis.set("sourceCode:"+sourceCode,tid);
        // 设置 课程码 生存时间
        jedis.expire("sourceCode:"+sourceCode,900);

        return sourceCode;
    }

}
