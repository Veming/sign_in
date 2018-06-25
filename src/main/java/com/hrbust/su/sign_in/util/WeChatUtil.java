package com.hrbust.su.sign_in.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class WeChatUtil {

    public static String getGUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }



    public static String getOpenId(String code){
        //define WeChat API
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxb52cd79506a20dcc&secret=b2183fecec639f9819786ddd38944e26&js_code=" + code + "&grant_type=authorization_code";
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        //获取openid和access_token的连接
        //获取返回的code
        URL realURL = null;
        try {
            realURL = new URL(url);
            URLConnection conn = realURL.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // flush输出流的缓冲
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(result.toString()).getString("openid");
    }
}
