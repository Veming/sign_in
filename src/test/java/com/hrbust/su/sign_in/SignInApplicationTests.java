package com.hrbust.su.sign_in;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.dao.TeacherDao;
import com.hrbust.su.sign_in.dao.UserDao;
import com.hrbust.su.sign_in.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignInApplicationTests {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    UserDao userDao;

    @Autowired
    TeacherService teacherService;
    @Test
    public void contextLoads() throws IOException {




    }

}
