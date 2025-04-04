package com.example.servlet;


import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.controller.StudentsController;
import com.example.controller.UsersController;
import com.example.controller.WebController;
import com.example.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/UserUpdate")
public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //response.setCharacterEncoding("UTF-8");
        System.out.println("UserUpdateing...");


        StringBuilder json = new StringBuilder();
        String line;
        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        String jsonString = json.toString();
        Users user = JSON.parseObject(jsonString, Users.class);
        System.out.println("Received user: " + user);
        Result result = null;
        if(user.getRole().equals("ADMIN")){
            UsersController usersController = new UsersController();
            result=usersController.updateById(user);
        }else if(user.getRole().equals("STUDENT")){
            StudentsController studentsController = new StudentsController();
            result=studentsController.updateById(user);
        }else{
            result=Result.error();
        }
        jsonString = JSON.toJSONString(result);
        System.out.println(jsonString);

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
