package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.ApiResult;
import com.example.demo.utils.TokenGenerator;
import com.example.demo.utils.TokenVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class TestController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private TokenVerifier tokenVerifier;

    /**
     * @Author zhangchen
     * @Descript 测试redis
     * @Date 2024-11-21
     */
    @RequestMapping(value = "/getredis", method = RequestMethod.POST)
    public ApiResult<Object> getRedis(@RequestBody String Params) {
        JSONObject jsonObject = (JSONObject) JSON.parse(Params);
        String redisKey = jsonObject.getObject("key", String.class);
        String redisValue = jsonObject.getObject("value", String.class);
        String res = "";
        if (!isDataExists(redisKey)) {
            redisTemplate.opsForValue().set(redisKey, redisValue); // 保存到redis
            redisTemplate.expire(redisKey, 60, TimeUnit.SECONDS);  // 设置缓存有效期
            res = "缓存不存在，保存key：" + redisKey + "，保存value：" + redisValue + "有效期时间1分钟";
        } else {
            res = "缓存已存在，保存key：" + redisKey + "，保存value：" + redisValue + "有效期时间1分钟";
        }
        return ApiResult.success(res);
    }

    /**
     * @Author zhangchen
     * @Descript 判断redis缓存是否已经存在
     * @Date 2024-11-21
     */
    public boolean isDataExists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * @Author zhangchen
     * @Descript 生成token
     * @Date 2024-11-23
     */
    @RequestMapping(value = "/gettoken", method = RequestMethod.POST)
    public ApiResult<Object> getToken(@RequestBody String Params) {
        // String secret = tokenGenerator.generatorSecretKey();   //生成随机密钥的方法
        JSONObject jsonObject = (JSONObject) JSON.parse(Params);
        String userName = jsonObject.getObject("username", String.class);
        //根据用户生成token
        String token = tokenGenerator.createToken(userName);
        //判读token是否在有效期
        boolean isExpired = tokenVerifier.validateToken(token, userName);
        System.out.println("当前用户token是否在有效期：" + isExpired);
        return ApiResult.success(token);
    }
}
