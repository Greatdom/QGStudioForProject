package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.controller.WebController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String role=req.getParameter("role");
        WebController webController=new WebController();
        Result result=webController.register(username,password,role);
        String jsonStr= JSON.toJSONString(result);
        System.out.println(jsonStr);


        //String jsonStr = JSON.toJSONString(obj);
        //User user = JSON.parseObject(jsonStr,User.class);


//        Users user = new Users();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setRole(role);
//        System.out.println(user);
//        String jsonStr= JSON.toJSONString(user);

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.getWriter().write(jsonStr);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
