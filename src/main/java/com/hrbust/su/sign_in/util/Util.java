package com.hrbust.su.sign_in.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class Util {
    private static final Integer CODELENGTH = 6;

    public static String generating6BitRandomNumbers(){
        Random random = new Random();
        StringBuilder sourceCode = new StringBuilder();
        for (int i = 0; i< CODELENGTH; i++)
        {
            sourceCode.append(random.nextInt(10));
        }
        return sourceCode.toString();
    }

    public static String ranging(String fLat, String fLng,
                          String tLat, String tLng){
        String url = "https://apis.map.qq.com/ws/distance/v1/?mode=driving&" +
                "from="+fLat+","+fLng+"&" +
                "to="+tLat+","+tLng+"&" +
                "key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77";
        String distance = null;

        try {
            distance =  URL2JSON(url).getJSONObject("result").getJSONArray("elements").getJSONObject(0).getString("distance");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distance;
    }

    public static JSONObject URL2JSON(String s) throws IOException {
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
//        System.out.println(str);
        JSONObject json = JSON.parseObject(str);
        return json;
    }
}
