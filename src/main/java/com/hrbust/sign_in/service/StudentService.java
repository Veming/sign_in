package com.hrbust.sign_in.service;

import com.hrbust.sign_in.bean.Student;
import com.hrbust.sign_in.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public Student getStudentInfo(){
        return studentDao.findById("").get();
    }
}
