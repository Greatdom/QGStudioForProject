package com.example.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/Home/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

//        if(username!=null){
//            System.out.println("right");
//            filterChain.doFilter(request, response);
//        }else{
//            System.out.println("wrong");
//            filterChain.doFilter(request, response);
//            //req.getRequestDispatcher("/test.html").forward(req, response);
//            //res.sendRedirect("http://localhost:8080/myapp/FRONT/src/front/LoginAndRegister.html");
//            //res.sendRedirect("http://localhost:8080/QG20250325LoginAndRegister_war/FRONT/src/front/LoginAndRegister.html");
//        }


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");

        if (username != null) {
            // 用户已登录，放行
            System.out.println("right");
            filterChain.doFilter(request, response);
        } else {
            // 用户未登录
            System.out.println("wrong");
            if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
                // 是AJAX请求
                res.setStatus(HttpServletResponse.SC_FORBIDDEN); // 返回403状态码
                res.setHeader("REDIRECT", "REDIRECT");
                //res.setHeader("CONTEXTPATH", req.getContextPath() + "/LoginAndRegister.html");
                res.setHeader("CONTEXTPATH", "http://localhost:8080/QG20250325LoginAndRegister_war/FRONT/src/front/LoginAndRegister.html");
            } else {
                // 不是AJAX请求，直接重定向到登录页面
                //res.sendRedirect(req.getContextPath() + "/LoginAndRegister.html");
                res.sendRedirect("http://localhost:8080/QG20250325LoginAndRegister_war/FRONT/src/front/LoginAndRegister.html");
            }
        }


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
