package com.example.controller;

import com.example.common.Result;
import com.example.entity.Courses;
import com.example.entity.StudentCourses;
import com.example.entity.Users;
import com.example.service.CoursesService;

import java.util.List;

public class StudentCoursesController {
    CoursesService coursesService = new CoursesService();

    //查询和某个学生有关的课程
    public Result selectByStudent(Users user){
        List<StudentCourses> courses = coursesService.selectByStudent(user);
        return Result.success(courses);
    }

    //查询和某个课程有关的学生
    public Result selectByCourse(Courses course){
        List<StudentCourses> courses = coursesService.selectByCourse(course);
        return Result.success(courses);
    }

    //查看可选课程
    public Result selectAvailable(){
        List<StudentCourses> courses = coursesService.selectAvailable();
        return Result.success(courses);
    }

//    //增加一条选课记录
//    //删除一条选课记录(Users user){
//        coursesService.addChoice(user);
//        if(user.getCount()<5)
//            return Result.success();
//        else
//            return Result.error();
//    }
//
//    //删除一条选课记录
//    public Result deleteChoice(Users user){
//        coursesService.deleteChoice(user);
//        if(user.getCount()>0)
//            return Result.success();
//        else
//    }
    //增加一条选课记录
    public Result addChoice(StudentCourses studentCourses){
        int status = 2;
        status = coursesService.addChoice(studentCourses);
        if(status == 1){
            return Result.success();
        }else if(status == 0){
            return Result.error();
        }
        return Result.error();
    }
    //删除一条选课记录
    public Result deleteChoice(StudentCourses studentCourses){
        int status = 2;
        status = coursesService.deleteChoice(studentCourses);
        if(status == 1){
            return Result.success();
        }else if(status == 0){
            return Result.error();
        }
        return Result.error();
    }
}
