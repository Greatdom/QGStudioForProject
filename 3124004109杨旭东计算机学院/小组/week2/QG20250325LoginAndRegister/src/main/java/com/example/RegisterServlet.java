package com.example;

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

        System.out.println("RegisterServlet");
        if("GET".equals(req.getMethod())){
            System.out.println("GET...");
        }else if("POST".equals(req.getMethod())){
            System.out.println("POST...");
        }
        System.out.println("------------------");
        Map<String,String[]> map = req.getParameterMap();
        for(String key:map.keySet()){
            System.out.print(key+":");
            String[] values = map.get(key);
            for(String value:values){
                System.out.print(value+" ");
            }
            System.out.println();
        }
        System.out.println("------------------");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        resp.setHeader("Content-Type", "text/html; charset=UTF-8");
        resp.getWriter().write("用户名:"+username+" 密码:"+password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
