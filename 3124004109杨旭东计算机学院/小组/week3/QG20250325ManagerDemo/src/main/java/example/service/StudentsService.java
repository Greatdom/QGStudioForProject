package com.example.service;

import com.example.entity.Users;
import com.example.mapper.StudentsMapper;

import java.util.List;

public class StudentsService {

    StudentsMapper studentsMapper = new StudentsMapper();
    public int add(Users user){
        Users dbUser = studentsMapper.selectByStudentname(user.getUsername());
        if(dbUser != null){
            return 0;
        }else{
            studentsMapper.insertStudent(user);
            return 1;
        }
    }
    public Users login(Users user) {
        Users dbUser = studentsMapper.selectByStudentname(user.getUsername());
        if(dbUser == null){
            return null;
        }else{
            if(dbUser.getPassword().equals(user.getPassword())){
                return dbUser;
            }else{
                return null;
            }
        }
    }
    public int register(Users user) {
        return add(user);
    }

    public void updateById(Users student) {
        studentsMapper.updateStudentById(student);
    }

    public List<Users> selectAll() {
        return studentsMapper.selectStudentAll();
    }

    public Users selectByStudentname(String username) {
        return studentsMapper.selectByStudentname(username);
    }
//    public Users selectById(Integer id) {
//        return mapper.selectByStudentId(id);
//    }
}
