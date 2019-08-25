package com.service;
import com.entity.Users;
import com.github.pagehelper.PageInfo;
import com.util.UserConditioin;

public interface UserService {
      //查询分页
    public PageInfo<Users> getAllRUsers(UserConditioin userConditioin);

    //实现用户注册
    public int regUser(Users users);

    //检查用户名是否存在
    public int isUserNameExists(String name);

    //登入功能
    public Users login(String username,String password);




}
