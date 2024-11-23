package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService extends IService<UserModel> {
    List<UserModel> getUser();

    IPage<UserModel> selectByPage(IPage<UserModel> page);

    boolean addUserList(List<UserModel> list);
}
