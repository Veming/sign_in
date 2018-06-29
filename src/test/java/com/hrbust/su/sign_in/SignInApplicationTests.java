package com.hrbust.su.sign_in;

import com.hrbust.su.sign_in.bean.Teacher;
import com.hrbust.su.sign_in.controller.WeChatController;
import com.hrbust.su.sign_in.dao.TeacherDao;
import com.hrbust.su.sign_in.dao.UserDao;
import com.hrbust.su.sign_in.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void contextLoads() {
        String sessionKey = "ece9003bf0004822b5fcb12223de1e90";
        teacherService.createSourceCode(sessionKey);
//        System.out.println(userDao.getIdBySessionKey(sessionKey));
    }


}
