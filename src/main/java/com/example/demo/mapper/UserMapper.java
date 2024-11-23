package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserModel> {
    @Select("select * from dk_user")
    List<UserModel> getUser();

    @Select("<script>select * from dk_user</script>")
    IPage<UserModel> selectByPage(IPage<UserModel> page);
}
