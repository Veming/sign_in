package com.hrbust.su.sign_in.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping(value = "/func")
public class FunctionController {
    //
    @GetMapping(value = "/tecReg")
    public String teacherReg(){
        Jedis jedis = new Jedis();
        JSONObject reJson = new JSONObject();
        try{
            if (!jedis.exists("tecReg")){
                jedis.set("tecReg","off");
                reJson.put("tecReg","off");
            }
            else {
                reJson.put("tecReg",jedis.get("tecReg"));
                reJson.put("state","success");
            }
        }catch (Exception e){
            System.out.println("teacherReg() ERROR!!");
            reJson.put("state","fail");
        }
        return reJson.toString();
    }

}
