package com.example.demo.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResult<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 调用返回码
     */
    private int code;
    
    /**
     * 返回code说明
     */
    private String msg;
    
    /**
     * 授权token
     */
    private String token;
    
    /**
     * 接口调用返回说明
     */
    private String description;
    
    /**
     * 返回数据对象
     */
    private T data;
    
    /**
     * 返回数据对象count结果
     */
    private int count;
    
    private ApiResult() {
    }
    
    private ApiResult(T data) {
        this.code = 200;
        this.msg = "成功";
        this.data = data;
    }
    
    private ApiResult(T data, com.alibaba.fastjson.JSONObject description) {
        this.code = 200;
        this.msg = "成功";
        this.data = data;
        this.description = description.toString();
    }
    
    private ApiResult(T data, com.alibaba.fastjson.JSONObject description, String token) {
        this.code = 200;
        this.msg = "成功";
        this.data = data;
        this.token = token;
        this.description = description.toString();
    }
    
    private ApiResult(T data, Integer count, com.alibaba.fastjson.JSONObject description) {
        this.code = 200;
        this.msg = "成功";
        this.data = data;
        this.count = count;
        this.description = description.toString();
    }
    
    private ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    private ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public static <T> ApiResult<T> success() {
        return new ApiResult<T>(null);
    }
    
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(data);
    }
    
    public static <T> ApiResult<T> success(T data, JSONObject description) {
        return new ApiResult<T>(data, description);
    }
    
    public static <T> ApiResult<T> success(T data, Integer count, JSONObject description) {
        return new ApiResult<T>(data, count, description);
    }
    
    public static <T> ApiResult<T> success(T data, JSONObject description, String token) {
        return new ApiResult<T>(data, description, token);
    }
    
    public static <T> ApiResult<T> fail() {
        return new ApiResult<T>(500, "出错了");
        
    }
    
    public static <T> ApiResult<T> fail(String msg) {
        return new ApiResult<T>(500, msg);
    }
    
    public static <T> ApiResult<T> fail(int code, String msg) {
        return new ApiResult<T>(code, msg);
    }
    
}
