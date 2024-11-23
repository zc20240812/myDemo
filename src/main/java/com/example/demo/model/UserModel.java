package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@TableName(value = "dk_user", autoResultMap = true)
public class UserModel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "nickname")
    private String nickname;
    @TableField(value = "roleid")
    private Integer roleid;
    @TableField(value = "rolename")
    private String rolename;
    @TableField(value = "flag")
    private Integer flag;
    @TableField(value = "createtime")
    private String createtime;
}
