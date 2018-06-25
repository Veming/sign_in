package com.hrbust.su.sign_in.controller;


import com.hrbust.su.sign_in.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/WeChat")
public class WeChatController {

    @Autowired
    TeacherService teacherService;

//    @GetMapping("")
//    public void index(){
//
//    }

    @RequestMapping(value = "/sign_in")
    public String signIn(){
        //获取学生学号

        return null;
    }

    @RequestMapping(value = "/class_begin")
    public String classBegin(){

        return teacherService.createSourceCode("");
    }
}
