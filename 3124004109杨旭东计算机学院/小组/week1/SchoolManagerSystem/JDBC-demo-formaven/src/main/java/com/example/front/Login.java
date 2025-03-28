package com.example.front;

import com.example.common.Result;
import com.example.controller.WebController;

import java.util.Scanner;

public class Login {
    WebController webController=new WebController();
    public Result login() {
        Result result;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入登录账号:");
        String username = scanner.nextLine();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();
        String role;
        while(true){
            System.out.println("按1作为管理员，按2作为学生:");
            int temp = scanner.nextInt();
            if (temp == 1) {
                role="ADMIN";
                break;
            }else if (temp == 2) {
                role="STUDENT";
                break;
            }else System.out.println("请重新输入...");
        }
        result = webController.login(username, password, role);
        return result;
    }
}
