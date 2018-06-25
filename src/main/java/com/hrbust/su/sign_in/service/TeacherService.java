package com.hrbust.su.sign_in.service;

import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.dao.StudentDao;
import com.hrbust.su.sign_in.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Random;

@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;

    public String createSourceCode(String sourceId){
        Random random = new Random();
        StringBuilder sourceCode = new StringBuilder();
        for (int i=0;i<6;i++)
        {
            sourceCode.append(random.nextInt(10));
        }

        Jedis jedis = new Jedis();
        jedis.set("sourceCode",sourceCode.toString());
        jedis.expire("sourceCode",900);

        return sourceCode.toString();
    }

}
