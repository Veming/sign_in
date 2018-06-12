package com.hrbust.su.sign_in.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.bean.User;
import com.hrbust.su.sign_in.dao.StudentDao;
import com.hrbust.su.sign_in.dao.UserDao;
import com.hrbust.su.sign_in.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginService {
    @Autowired
    UserDao userDao;

    @Autowired
    StudentDao studentDao;

    public String register(String code,String sid) throws IOException {
        // 生成新生信息
        User user = new User();
        //获取用户openID
        String openId = WechatUtil.getOpenId(code);
        String sessionKey = WechatUtil.getGUID();
        user.setOpenId(openId);
        user.setSessionKey(sessionKey);
        user.setType(2);
        userDao.save(user);
        return sessionKey;
    }


    public String checkSession(String sessionKey) {
        User user = userDao.getUserInfo(sessionKey);
        JSONObject json = new JSONObject();
        if (user != null){
           if (user.getType() == 0){
               //搜索教师表 获取教师信息

           }
           else {
               // 搜索学生表 获取学生信息
               Student student = studentDao.getStudentBySessionKey(sessionKey);
               json = (JSONObject) JSON.toJSON(student);
               json.put("type",user.getType());
               return json.toString();
           }

        }
        return null;
    }
}
