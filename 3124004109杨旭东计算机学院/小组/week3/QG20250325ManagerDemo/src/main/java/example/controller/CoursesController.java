package com.example.controller;

import com.example.common.Result;
import com.example.entity.Courses;
import com.example.service.CoursesService;

import java.util.List;

public class CoursesController {
    CoursesService coursesService = new CoursesService();
    public Result selectAll() {
        List<Courses> courses =coursesService.selectAll();
        return Result.success(courses);
    }
    public Result selectByCoursename(String name) {
        Courses course=coursesService.selectByCoursename(name);
        return Result.success(course);
    }
    public Result insert(String Name,String Status) {
        Courses course = new Courses();
        course.setName(Name);
        course.setStatus(Status);
        int status = 2;
        status = coursesService.add(course);
        if (status == 1) {
            return Result.success();
        }else if (status == 0) {
            return Result.error();
        }
        return Result.error();
    }
    public Result delete(Integer Id) {
        coursesService.delete(Id);
        return Result.success();
    }
}
