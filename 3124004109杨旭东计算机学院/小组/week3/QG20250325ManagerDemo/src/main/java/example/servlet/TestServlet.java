package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.controller.WebController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/test")
public class TestServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

//        Cookie cookie=new Cookie("username","CookieUsername");
//        resp.addCookie(cookie);
//        Cookie[] cookies = req.getCookies();
//        for(Cookie cookie : cookies){
//            String name=cookie.getName();
//            if("username".equals(name)){
//                String value=cookie.getValue();
//                System.out.println(name+":"+value);
//                break;
//            }
//
//        }

        HttpSession session = req.getSession();
        session.setAttribute("username","sessionUsername");

        System.out.println("-----------");
        Object username = session.getAttribute("username");
        System.out.println(username);

        resp.setHeader("Content-Type", "text/html; charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
