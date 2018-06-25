package com.hrbust.su.sign_in.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.bean.Teacher;
import com.hrbust.su.sign_in.bean.User;
import com.hrbust.su.sign_in.dao.StudentDao;
import com.hrbust.su.sign_in.dao.TeacherDao;
import com.hrbust.su.sign_in.dao.UserDao;
import com.hrbust.su.sign_in.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginService {
    @Autowired
    UserDao userDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeacherDao teacherDao;

    public String register( String code, String idNbr, String stuName) {
        Student student = studentDao.getStudentByRealNameAndIdCard(stuName,idNbr);
        if (student == null){
            return "None";
        }
        else {
            // 生成新生信息
            User user = new User();
            //获取用户openID
            String openId = WeChatUtil.getOpenId(code);
            String sessionKey = WeChatUtil.getGUID();
            user.setOpenId(openId);
            user.setSessionKey(sessionKey);
            user.setType(2);
            user.setId(student.getSid());
            userDao.save(user);
            return sessionKey;
        }
    }


    public String checkSession(String sessionKey) {
        User user = userDao.getUserInfoBySessionKey(sessionKey);
        JSONObject json ;
        if (user != null){
           if (user.getType() == 0){
               //搜索教师表 获取教师信息
               Teacher teacher = teacherDao.getTeacherByTid(user.getId());
               json = (JSONObject) JSON.toJSON(teacher);
               json.put("type",0);
               return json.toString();
           }
           else {
               // 搜索学生表 获取学生信息
               Student student = studentDao.getStudentBySid(user.getId());
               json = (JSONObject) JSON.toJSON(student);
               json.put("type",user.getType());
               return json.toString();
           }
        }
        return  null;
    }

    public String checkCode(String code) {
        if ("".equals(code) || code == null){
            return "code is null";
        }
        String openId = WeChatUtil.getOpenId(code);
        String sessionKey = WeChatUtil.getGUID();
        User user = userDao.getUserInfoByOpenId(openId);
        if (user != null){
            user.setSessionKey(sessionKey);
            userDao.save(user);
            JSONObject json = new JSONObject();
            json.put("type",user.getType());
            json.put("sessionKey",sessionKey);
            return json.toString();
        }
        else {
            return "unknown";
        }
    }
}