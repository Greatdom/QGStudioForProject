package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.controller.StudentCoursesController;
import com.example.controller.StudentsController;
import com.example.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Home/FrontSelfCoursesServlet")
public class FrontSelfCoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String command=req.getParameter("command");


        StudentsController studentsController=new StudentsController();
        StudentCoursesController studentCoursesController=new StudentCoursesController();
        Result result=null;
        if(command==null|| command.isEmpty()){
            result=Result.error();
        }else if("SelectSelfCourses".equals(command)){
            result=studentsController.selectByStudentname(req.getParameter("username"));

            if(result.getCode().equals("200")){
                result=studentCoursesController.selectByStudent((Users)result.getData());
            }else{
                result=Result.error();
            }
        }else{
            result=Result.error();
        }

        String jsonString = JSON.toJSONString(result);
        System.out.println(jsonString);

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
