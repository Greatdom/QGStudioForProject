package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.controller.StudentsController;
import com.example.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManagerSelectAllStudentsServlet")
public class ManagerSelectAllStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String command=req.getParameter("command");


        StudentsController studentsController=new StudentsController();
        Result result=null;
        if(command==null|| command.isEmpty()){
            result=Result.error();
        }else if("SelectAllStudents".equals(command)){
            result=studentsController.selectAll();
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
