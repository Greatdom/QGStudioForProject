package com.example.front;

import com.example.common.Result;
import com.example.controller.CoursesController;
import com.example.controller.StudentsController;
import com.example.entity.Users;
import com.example.front.Cls;

import java.util.Scanner;

public class Student {

    static StudentsController studentsController = new StudentsController();
    static CoursesController coursesController = new CoursesController();

    private static boolean RETURN_KEY = true ;
    public static void student(Users user){
        while(RETURN_KEY){
            System.out.println("学生"+user.getUsername()+"界面");
            System.out.println("1.返回");
            System.out.println("2.改电话号码");
            System.out.println("3.查看自己信息");
            System.out.println("4.查看所有课程");
            System.out.println("5.选择进修课程");
            System.out.println("6.退出你的课程");
            System.out.println("7.查看你的课程");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    Cls.cls();return;
                case 2:Cls.cls();
                    System.out.println("请修改电话号码");
                    Scanner scChangePhone = new Scanner(System.in);
                    String phone = scChangePhone.nextLine();
                    user.setPhone(phone);
                    if("200".equals(studentsController.updateById(user).getCode()))
                        System.out.println("已修改");
                    else System.out.println("修改失败");
                break;
                case 3:Cls.cls();
                    if("200".equals(studentsController.selectByStudentname(user.getUsername()).getCode()))
                    {
                        System.out.println("用户名"+user.getUsername());
                        System.out.println("密码"+user.getPassword());
                        System.out.println("已选课数"+user.getCount());
                        System.out.println("电话"+user.getPhone());
                    }else {
                        System.out.println("查询失败");
                    }
                    break;
                case 4:Cls.cls();
                    Result courseData = coursesController.selectAll();
                    if("200".equals(courseData.getCode()))
                        System.out.println(courseData.getData());
                    else
                        System.out.println("查询失败");
                    break;
                case 5:break;
                case 6:break;
                case 7:break;
                default:System.out.println("无法识别的指令");
            }
        }
    }

}
