package com.hrbust.su.sign_in.service;

import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public String signIn(String sCode){

        return null;
    }

    public Student getStudentInfo(){
        return studentDao.findById("").get();
    }



    public void setSessionKey(Student student){
        studentDao.save(student);
    }


}
