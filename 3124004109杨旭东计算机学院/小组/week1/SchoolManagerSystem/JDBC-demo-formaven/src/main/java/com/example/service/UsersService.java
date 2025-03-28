package com.example.service;

import com.example.entity.Users;
import com.example.mapper.Mapper;

import java.util.List;

public class UsersService {
    Mapper mapper = new Mapper();
    public int add(Users user){
        Users dbUser = mapper.selectByUsername(user.getUsername());
        if(dbUser != null){
            return 0;
        }else{
            mapper.insertUser(user);
            return 1;
        }
    }

    public Users login(Users user){
        Users dbUser = mapper.selectByUsername(user.getUsername());
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
    public int register(Users user){
        return add(user);
    }

    public void updateById(Users user) {
        mapper.updateUserById(user);
    }

    public List<Users> selectAll() {
    return mapper.selectUserAll();
    }

    public Users selectByUsername(String username) {
        return mapper.selectByUsername(username);
    }
}
