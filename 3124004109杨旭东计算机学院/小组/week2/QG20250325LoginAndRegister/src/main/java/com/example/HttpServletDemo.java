package com.example;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/HttpServletDemo")
public class HttpServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method =req.getMethod();
        System.out.println(method);
        String contextPath = req.getContextPath();
        System.out.println(contextPath);
        StringBuffer url = req.getRequestURL();
        System.out.println(url.toString());
        String uri=req.getRequestURI();
        System.out.println(uri);
        String queryString = req.getQueryString();
        System.out.println(queryString);
        System.out.println("------------------");
        Map<String,String[]> map = req.getParameterMap();
        System.out.println(map);
        for(String key:map.keySet()){
            System.out.print(key+":");
            String[] values = map.get(key);
            for(String value:values){
                System.out.print(value+" ");
            }
            System.out.println();
        }
        System.out.println("------------------");
        String[] hobbies =req.getParameterValues("hobby");
        for(String hobby:hobbies){
            System.out.println(hobby);
        }
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        if(method.equals("GET")){
            System.out.println("GET...");
        }else if(method.equals("POST")){
            System.out.println("POST...");
        }

        //req.getRequestDispatcher("/demo").forward(req,resp);//请求转发
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
