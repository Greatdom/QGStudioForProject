package com.example.service;

import com.example.entity.Users;
import com.example.mapper.Mapper;

import java.util.List;

public class StudentsService {
    Mapper mapper = new Mapper();
    public int add(Users user){
        Users dbUser = mapper.selectByStudentname(user.getUsername());
        if(dbUser != null){
            return 0;
        }else{
            mapper.insertStudent(user);
            return 1;
        }
    }
    public Users login(Users user) {
        Users dbUser = mapper.selectByStudentname(user.getUsername());
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
        mapper.updateStudentById(student);
    }

    public List<Users> selectAll() {
        return mapper.selectStudentAll();
    }

    public Users selectByStudentname(String username) {
        return mapper.selectByStudentname(username);
    }
//    public Users selectById(Integer id) {
//        return mapper.selectByStudentId(id);
//    }
}
