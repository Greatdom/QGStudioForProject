package com.example.text;

import com.example.entity.Users;
import com.example.service.StudentsService;
import com.example.service.UsersService;

import java.util.List;

public class Text {
    public static void main(String[] args) {
        Users user = new Users();
        user.setRole("USER");
        user.setUsername("test");
        user.setPassword("123456");
        StudentsService studentsService = new StudentsService();

        studentsService.add(user);

    }
}
