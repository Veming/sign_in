package com.hrbust.su.sign_in.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.service.EmailService;
import com.hrbust.su.sign_in.service.StudentService;
import com.hrbust.su.sign_in.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/WeChat")
public class WeChatController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/find_students_by_class",method = RequestMethod.GET)
    public List<Student> findStudentByClass(@RequestParam(value = "sessionKey",required = false) String sessionKey){

        return studentService.findStudentByClass(sessionKey);
    }

    @GetMapping(value = "/get_stu_info")
    public String getStuInfo(@RequestParam(value = "sessionKey",required = false) String sessionKey){
        return studentService.getStuInfoBySession(sessionKey);
    }

    @PostMapping(value = "/change_stu_state")
    public String changeStuState(@RequestBody String jsonStr){
        JSONObject json = JSON.parseObject(jsonStr);
        String sid = json.getString("sid");
        return studentService.changeStuState(sid);
    }

    @RequestMapping(value = "/class_begin", method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT})
    public String classBegin(@RequestBody String jsonStr){
        JSONObject json = JSON.parseObject(jsonStr);
        return teacherService.createSourceCode(json);
    }

    @RequestMapping(value = "/sign_in",method = RequestMethod.POST)
    public String signIn(@RequestBody String jsonStr){
        JSONObject json = JSONObject.parseObject(jsonStr);
        return studentService.signIn(json);
    }

    @RequestMapping(value = "/send_code", method = {RequestMethod.POST})
    public String getCode(@RequestBody String jsonStr){
        JSONObject json = JSONObject.parseObject(jsonStr);
        return emailService.sendMessage(json.getString("to"),json.getString("code"));
    }

    @RequestMapping(value = "/check_source_code",method = RequestMethod.POST)
    public String checkSourceCode(@RequestBody String jsonStr){
        JSONObject json = JSONObject.parseObject(jsonStr);
        return teacherService.checkSourceCode(json);
    }

    @RequestMapping(value = "/who_has_check_in",method = RequestMethod.POST)
    public String whoHasCheckIn(@RequestBody String jsonStr){
        JSONObject json = JSONObject.parseObject(jsonStr);
        return teacherService.whoHasCheckIn(json);
    }

    @RequestMapping(value = "/check_sign_in",method = RequestMethod.POST)
    public String checkSignIn(@RequestBody String jsonStr){
        JSONObject json = JSONObject.parseObject(jsonStr);
        return studentService.checkSignIn(json);
    }

}
