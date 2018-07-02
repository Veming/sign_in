package com.hrbust.su.sign_in.service;

import com.alibaba.fastjson.JSONObject;
import com.hrbust.su.sign_in.bean.Student;
import com.hrbust.su.sign_in.dao.StudentDao;
import com.hrbust.su.sign_in.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    @Autowired
    UserDao userDao;

    public String signIn(JSONObject data){
        Jedis jedis = new Jedis();

        String sessionKey = data.getString("sessionKey");
        String fLng = data.getString("longitude");
        String fLat = data.getString("latitude");
        String sourceCode = data.getString("sourceCode");

        String tLng = jedis.get("sourceCode:"+sourceCode+"_longitude");
        String tLat = jedis.get("sourceCode:"+sourceCode+"_latitude");







        String sid = userDao.getIdBySessionKey(sessionKey);



        return null;
    }


    public String getStuInfoBySession(String sessionKey){
        String sid = userDao.getIdBySessionKey(sessionKey);
        Student student = studentDao.getStudentBySid(sid);
        JSONObject json = (JSONObject) JSONObject.toJSON(student);
        return json.toString();
    }

    public List<Student> findStudentByClass(String sessionKey){
        String sid = userDao.getIdBySessionKey(sessionKey);
        Student student = studentDao.getStudentBySid(sid);

        List<Student> StudentList =
                 studentDao.getStudentsByProfessionAndGradeAndClassName
                        (student.getProfession(),student.getGrade(),student.getClassName(),sid);
//        JSONObject json = (JSONObject) JSONObject.toJSON(StudentList);
        return StudentList;
    }


    public String changeStuState(String sid) {
        try{
            studentDao.setState(sid);
        }catch (Exception e){
            return "fail";
        }
        return "success";
    }
}
