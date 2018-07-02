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


        String url = "https://apis.map.qq.com/ws/distance/v1/?mode=driving&" +
                "from=39.983171,116.308479&" +
                "to=39.996060,116.353455;39.949227,116.394310&" +
                "key=TVFBZ-4ALC4-KGLUR-XMVQ7-4JZSK-XGBYT";




    }

    private static JSONObject URL2JSON(String s) throws IOException {
        StringBuffer buffer = new StringBuffer();
        // 通过js的执行路径获取后台数据进行解析
        URL url = new URL(s);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setDoOutput(true);
        http.setDoInput(true);
        http.setUseCaches(false);
        http.setRequestMethod("GET");
        http.connect();
        // 将返回的输入流转换成字符串
        InputStream inputStream = http.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        // 释放资源
        inputStream.close();
        inputStream = null;
        http.disconnect();
        str = buffer.toString();
        System.out.println(str);
        JSONObject json = JSON.parseObject(str);
        return json;
    }

}
