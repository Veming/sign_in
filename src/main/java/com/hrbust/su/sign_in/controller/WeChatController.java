package com.hrbust.su.sign_in.controller;


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

    @RequestMapping(value = "/class_begin")
    public String classBegin(){

        return teacherService.createSourceCode("");
    }

    @RequestMapping(value = "/findStudentsByClass",method = RequestMethod.GET)
    public List<Student> findStudentByClass(@RequestParam(value = "sessionKey",required = false) String sessionKey){

        return studentService.findStudentByClass(sessionKey);
    }

    @GetMapping(value = "/getStuInfo")
    public String getStuInfo(@RequestParam(value = "sessionKey",required = false) String sessionKey){
        return studentService.getStuInfoBySession(sessionKey);
    }
}
