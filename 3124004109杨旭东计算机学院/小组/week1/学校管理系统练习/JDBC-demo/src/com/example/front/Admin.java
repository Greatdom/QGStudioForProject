package com.example.front;

import com.example.common.Result;
import com.example.controller.CoursesController;
import com.example.controller.StudentsController;
import com.example.controller.UsersController;
import com.example.entity.Users;

import java.util.List;
import java.util.Scanner;

public class Admin {

    static UsersController usersController = new UsersController();
    static StudentsController studentsController = new StudentsController();
    static CoursesController coursesController = new CoursesController();

    private static boolean RETURN_KEY = true ;
    public static void admin(Users user){
        while(RETURN_KEY){
            System.out.println("管理者"+user.getUsername()+"界面");
            System.out.println("1.返回");
            System.out.println("2.改密码");
            System.out.println("3.查看自己信息");
            System.out.println("4.查看学生信息");
            System.out.println("5.查看所有课程");
            System.out.println("6.增课程");
            System.out.println("7.删课程");
            System.out.println("8.某个课程哪些人选了");
            System.out.println("9.某个人选了什么课");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch(choice){
                case 1:Cls.cls();return;
                case 2:Cls.cls();
                    System.out.println("请修改密码:");
                    Scanner scChangePassword = new Scanner(System.in);
                    String password = scChangePassword.nextLine();
                    user.setPassword(password);
                    if("200".equals(usersController.updateById(user).getCode()))
                        System.out.println("已修改");
                    else System.out.println("修改失败");
                break;
                case 3:Cls.cls();
                    if("200".equals(usersController.selectByUsername(user.getUsername()).getCode()))
                    {
                        System.out.println("用户名"+user.getUsername());
                        System.out.println("密码"+user.getPassword());
                    }else {
                        System.out.println("查询失败");
                    }
                break;
                case 4:Cls.cls();
                    Result studentsData =studentsController.selectAll(user);
                if("200".equals(studentsData.getCode())){
                    System.out.println(studentsData.getData());
                }else
                    System.out.println("查询失败");
                break;
                case 5:Cls.cls();
                    Result courseData =coursesController.selectAll();
                if("200".equals(courseData.getCode())){
                    System.out.println(courseData.getData());
                }else
                    System.out.println("查询失败");
                break;
                case 6:Cls.cls();
                    System.out.println("请输入课程名字:");
                    Scanner scInsertCourse = new Scanner(System.in);
                    String courseName = scInsertCourse.nextLine();
                    System.out.println("是否已开课(输入1/是或2/否)");
                    String courseStatus ;
                    int tempForStatus = scInsertCourse.nextInt();
                    if(tempForStatus==1){courseStatus="是";}
                    else courseStatus="否";
                    if("200".equals(coursesController.insert(courseName, courseStatus).getCode())){
                        System.out.println("成功");
                    }else System.out.println("失败");
                break;
                case 7:Cls.cls();
                    Scanner scDeleteCourse = new Scanner(System.in);
                    System.out.println("请输入课程号:");
                    int tempForDelete = scDeleteCourse.nextInt();
                    if("200".equals(coursesController.delete(tempForDelete).getCode())){
                        System.out.println("成功");
                    }else System.out.println("失败");
                break;
                case 8:Cls.cls();break;
                case 9:Cls.cls();break;
                default:Cls.cls();System.out.println("无法识别的指令");
            }
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 400) {
                // 空体，等待 0.4 秒
            }
        }
    }

}
