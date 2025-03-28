package com.example.front;

import com.example.common.Result;
import com.example.entity.Users;
import com.example.mapper.Mapper;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

        Login login=new Login();
        Result current;
        Register register = new Register();
        boolean EXIT_KEY=true;
        while(EXIT_KEY){
            System.out.println("学校管理系统");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("3.退出");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if(choice == 1){
                Cls.cls();
                current=login.login();
                if ("200".equals(current.getCode())){
                    System.out.println(current.getMsg());
                    Users currentUser= (Users) current.getData();
                    if("ADMIN".equals(currentUser.getRole()))
                        Admin.admin(currentUser);
                    else
                        Student.student(currentUser);
                }else System.out.println(current.getMsg());
            }else if(choice == 2){
                Cls.cls();
                current=register.register();
                if ("200".equals(current.getCode())){
                    System.out.println(current.getMsg());
                }else System.out.println(current.getMsg());
            }else if(choice == 3){
                EXIT_KEY=false;
            }else System.out.println("无法识别的指令");;
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 400) {
                // 空体，等待 0.4 秒
            }
        }
        try {
            Mapper.getConn().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
