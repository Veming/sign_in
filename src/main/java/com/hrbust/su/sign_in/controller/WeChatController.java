package com.hrbust.su.sign_in.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.hrbust.su.sign_in.bean.Student;
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

//    @GetMapping("")
//    public void index(){
//
//    }

    @RequestMapping(value = "/sign_in")
    public String signIn(){
        //获取学生学号

        return null;
    }



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
        String sessionKey = json.getString("sessionKey");
        return teacherService.createSourceCode(sessionKey);


    }
}
