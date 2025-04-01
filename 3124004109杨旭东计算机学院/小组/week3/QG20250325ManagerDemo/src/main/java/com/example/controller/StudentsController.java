package com.example.controller;

import com.example.common.Result;
import com.example.entity.Users;
import com.example.service.StudentsService;

import java.util.List;

public class StudentsController {
    StudentsService studentsService = new StudentsService();
    public Result updateById(Users student){
        studentsService.updateById(student);
        return Result.success();
    }
    public Result selectAll(Users user){
        List<Users> students = studentsService.selectAll();
        return Result.success(students);
    }
    public Result selectByStudentname(String username){
        Users user =studentsService.selectByStudentname(username);
        return Result.success(user);
    }
}
