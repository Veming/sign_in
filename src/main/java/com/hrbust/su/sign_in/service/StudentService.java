package com.hrbust.su.sign_in.service;

import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    public Student getStudentInfo(){
        return studentDao.findById("").get();
    }

    public Student getStudentInfo(String idNbr, String stuName) {
        return studentDao.getUserInfo(stuName,idNbr);
    }


}
