package com.hrbust.sign_in.controller;

import com.hrbust.sign_in.bean.Student;
import com.hrbust.sign_in.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/SignIn")
public class SignInController {
    @Autowired
    private StudentService studentService;

    @GetMapping(name = "")
    @ResponseBody
    public Student getStuInfo(){
        Student student = new Student();

        student.setId("1");
        student.setClassName("1");
        student.setDormNbr("1");
        student.setRealName("1");
        student.setFlatName("1");
        student.setIdCard("1");

        return student;
    }
}
