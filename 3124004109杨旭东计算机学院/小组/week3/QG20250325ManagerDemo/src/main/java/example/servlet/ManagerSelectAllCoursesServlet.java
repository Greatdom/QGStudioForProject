package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.controller.CoursesController;
import com.example.controller.StudentCoursesController;
import com.example.entity.Courses;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Home/ManagerSelectAllCoursesServlet")
public class ManagerSelectAllCoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String command=req.getParameter("command");

        CoursesController coursesController=new CoursesController();
        StudentCoursesController studentCoursesController=new StudentCoursesController();
        Result result=null;
        if(command==null|| command.isEmpty()){
            result=Result.error();
        }else if("SelectAllCourses".equals(command)){
            result=coursesController.selectAll();
        }else if("SelectCourseByStudent".equals(command)){
            result=coursesController.selectByCoursename(req.getParameter("name"));
            if("200".equals(result.getCode())){
                Courses courses=(Courses)result.getData();
                result=studentCoursesController.selectByCourse(courses);
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
