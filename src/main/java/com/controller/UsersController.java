package com.controller;


import com.entity.Users;
import com.github.pagehelper.PageInfo;
import com.service.UserService;
import com.util.UserConditioin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getRUser")
    @ResponseBody
    public Map<String,Object> getRUser(UserConditioin conditioin){
        PageInfo<Users> pageInfo=userService.getAllRUsers(conditioin);
        //返回结果
        HashMap<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
