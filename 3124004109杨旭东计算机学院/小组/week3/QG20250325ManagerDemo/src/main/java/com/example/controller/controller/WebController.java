package com.example.controller;

import com.example.common.Result;
import com.example.entity.Users;
import com.example.service.StudentsService;
import com.example.service.UsersService;

public class WebController {

    UsersService usersService=new UsersService();
    StudentsService studentsService=new StudentsService();

    public Result login(String username, String password,String role) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        if(user.getRole().equals("ADMIN")){
            user=usersService.login(user);
        }else if(user.getRole().equals("STUDENT")){
            user=studentsService.login(user);
        }else{
            user=null;
        }
        if(user!=null)
            return Result.success(user);
        else
            return Result.error();
    }
    public Result register(String username, String password, String role) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        int status=2;
        if(user.getRole().equals("ADMIN")){
            status=usersService.register(user);
        }else if(user.getRole().equals("STUDENT")){
            status=studentsService.register(user);
        }else{
            status=0;
        }
        if(status==1)
            return Result.success();
        else if(status==0)
            return Result.error();
        return Result.error();
    }
}
