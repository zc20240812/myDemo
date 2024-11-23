package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.ApiResult;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @Author zhangchen
     * @Descript 查询
     * @Date 2024-11-18
     */
    @RequestMapping(value = "/getuser", method = RequestMethod.POST)
    public ApiResult<Object> getUser() {
        List<UserModel> list = userService.getUser();
        JSONObject description = new JSONObject(true);
        description.put("id", "自增id");
        description.put("username", "用户名");
        description.put("password", "密码");
        description.put("nickname", "昵称");
        description.put("apitoken", "token");
        description.put("flag", "状态");
        description.put("createtime", "更新时间");
        return ApiResult.success(list, description);
    }

    /**
     * @Author zhangchen
     * @Descript 分页查询
     * @Date 2024-11-18
     */
    @RequestMapping(value = "/getuserbypage", method = RequestMethod.POST)
    public ApiResult<Object> getUserByPage(@RequestBody String Params) {
        JSONObject jsonObject = (JSONObject) JSON.parse(Params);
        String PageNo = jsonObject.getObject("pageNo", String.class);
        if (PageNo == null || PageNo.equals("")) {
            log.info("页码不能为空！");
            return ApiResult.fail("页码不能为空！");
        }
        String PageSize = jsonObject.getObject("pageSize", String.class);
        if (PageSize == null || PageSize.equals("")) {
            log.info("分页条数不能为空！");
            return ApiResult.fail("分页条数不能为空！");
        }
        Page<UserModel> page = new Page<>(Integer.parseInt(PageNo), Integer.parseInt(PageSize));
        IPage<UserModel> list = userService.selectByPage(page);
        JSONObject description = new JSONObject(true);
        description.put("id", "自增id");
        description.put("username", "用户名");
        description.put("password", "密码");
        description.put("nickname", "昵称");
        description.put("apitoken", "token");
        description.put("flag", "状态");
        description.put("createtime", "更新时间");
        return ApiResult.success(list, description);
    }

    /**
     * @Author zhangchen
     * @Descript 插入或批量插入
     * @Date 2024-11-18
     */
    @RequestMapping(value = "/adduserlist", method = RequestMethod.POST)
    public ApiResult<Object> addUserList(@Validated @RequestBody UserModel[] userModels) {
        List<UserModel> list = Arrays.asList(userModels);
        boolean add = userService.addUserList(list);
        if (add) {
            return ApiResult.success("新增成功！");
        } else {
            return ApiResult.fail("新增失败！");
        }
    }
}
