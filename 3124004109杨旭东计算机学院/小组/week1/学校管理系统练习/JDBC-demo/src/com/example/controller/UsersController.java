package com.example.controller;

import com.example.common.Result;
import com.example.entity.Users;
import com.example.service.UsersService;

import java.util.List;

public class UsersController {
    UsersService usersService = new UsersService();
    public Result updateById(Users user){
        usersService.updateById(user);
        return Result.success();
    }
    public Result selectAll(Users user){
        List<Users> users = usersService.selectAll();
        return Result.success(users);
    }
    public Result selectByUsername(String username){
        Users user =usersService.selectByUsername(username);
        return Result.success(user);
    }
}
