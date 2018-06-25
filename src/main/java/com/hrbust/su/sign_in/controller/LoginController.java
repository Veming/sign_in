package com.hrbust.su.sign_in.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.service.LoginService;
import com.hrbust.su.sign_in.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    @RequestMapping(value = "/WeChat/checkSession", method = RequestMethod.POST)
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
    @RequestMapping(value = "/WeChat/register", method = RequestMethod.POST)
    public String register(@RequestBody String jsonStr){
        JSONObject json = JSON.parseObject(jsonStr);
        return loginService.register(json.getString("code"),json.getString("idNbr"),json.getString("stuName"));
    }

    @RequestMapping(value = "/WeChat/IdentityCheck", method = {RequestMethod.POST, RequestMethod.GET})
    public String identityCheck(@RequestBody String jsonStr){
        //转化为json
        JSONObject json = JSON.parseObject(jsonStr);
        // 若code存在，使用code查询， 返回session和用户类别， 否则查询通过session查询， 若session查询失败，返回fail，并使小程序session清空，重新运行小程序
        System.out.println(jsonStr);
        String sessionKey = json.getString("sessionKey");
        if (sessionKey != null){
            return loginService.checkSession(sessionKey);
        }
        else {
            return loginService.checkCode(json.getString("code"));
        }
    }
}
