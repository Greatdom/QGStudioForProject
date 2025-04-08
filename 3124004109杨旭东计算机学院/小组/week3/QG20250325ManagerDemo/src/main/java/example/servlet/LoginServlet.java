package com.example.servlet;

import com.alibaba.fastjson.JSON;
import com.example.common.Result;
import com.example.controller.StudentsController;
import com.example.controller.UsersController;
import com.example.controller.WebController;
import com.example.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String command = req.getParameter("command");
        if("Login".equals(command)){
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            String role=req.getParameter("role");
            String remember=req.getParameter("remember");
            WebController webController=new WebController();
            Result result=webController.login(username,password,role);

            if("true".equals(remember)&&"200".equals(result.getCode())){
                Cookie c_username=new Cookie("username",username);
                Cookie c_password=new Cookie("password",password);
                Cookie c_role=new Cookie("role",role);

                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);

                resp.addCookie(c_username);
                resp.addCookie(c_password);
                resp.addCookie(c_role);

            }

            String jsonStr=JSON.toJSONString(result);
            resp.setHeader("Content-Type", "text/html; charset=UTF-8");
            resp.getWriter().write(jsonStr);
        }else if("Load".equals(command)){

            Cookie[] cookies = req.getCookies();
            String username=null;
            String password=null;
            String role=null;
            for(Cookie cookie : cookies){
                String name=cookie.getName();
                if("username".equals(name)){
                    username=cookie.getValue();
                }
                if("password".equals(name)){
                    password=cookie.getValue();
                }
                if("role".equals(name)){
                    role=cookie.getValue();
                }
                if(username!=null&&password!=null&&role!=null){
                    break;
                }
            }
            resp.setHeader("Content-Type", "text/html; charset=UTF-8");
            if(username!=null&&password!=null&&role!=null){
                Result result=null;
                if("STUDENT".equals(role)){
                    StudentsController studentsController=new StudentsController();
                    result=studentsController.selectByStudentname(username);

                }else if("ADMIN".equals(role)){
                    UsersController usersController=new UsersController();
                    result=usersController.selectByUsername(username);
                }else{
                    result=Result.error();
                }
                String jsonStr=JSON.toJSONString(result);
                System.out.println(jsonStr);
                resp.setHeader("Content-Type", "text/html; charset=UTF-8");
                resp.getWriter().write(jsonStr);

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
