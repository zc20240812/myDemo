package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserModel> getUser() {
        return userMapper.getUser();
    }

    @Override
    public IPage<UserModel> selectByPage(IPage<UserModel> page) {
        return userMapper.selectByPage(page);
    }

    @Override
    public boolean addUserList(List<UserModel> list) {
        int record = 0;
        for (UserModel userModel : list) {
            record += userMapper.insert(userModel);
        }
        return record > 0;
    }
}
