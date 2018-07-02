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
        JSONObject json = null;
        try {
            json = Util.URL2JSON(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.getString("openid");
    }
}
