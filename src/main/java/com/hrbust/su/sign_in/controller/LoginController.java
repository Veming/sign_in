package com.hrbust.su.sign_in.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.bean.User;
import com.hrbust.su.sign_in.service.LoginService;
import com.hrbust.su.sign_in.service.StudentService;
import com.hrbust.su.sign_in.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author su
 */
@RestController
public class LoginController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "checkSession", method = RequestMethod.POST)
    public String checkSession(@RequestBody String jsonStr){
        JSONObject json = JSON.parseObject(jsonStr);
        String sessionKey = json.getString("sessionKey");
        return loginService.checkSession(sessionKey);
    }

    /**
     * 注册新用户
     * @param jsonStr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@RequestBody String jsonStr){
        JSONObject studentJson = null;
        try {
            JSONObject json = JSON.parseObject(jsonStr);
            Student student = studentService.getStudentInfo(json.getString("idNbr"),json.getString("stuName"));
            if (student == null){
                return "error";
            }
            String sessionKey = loginService.register(json.getString("code"),student.getSid());
            student.setSessionKey(sessionKey);
            studentService.setSessionKey(student);
            studentJson= (JSONObject) JSON.toJSON(student);
            return studentJson.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentJson.toString();
    }
}
