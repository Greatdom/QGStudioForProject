package com.example.service;

import com.example.entity.Users;
import com.example.mapper.UsersMapper;

import java.util.List;

public class UsersService {
    UsersMapper usersMapper = new UsersMapper();
    public int add(Users user){
        Users dbUser = usersMapper.selectByUsername(user.getUsername());
        if(dbUser != null){
            return 0;
        }else{
            usersMapper.insertUser(user);
            return 1;
        }
    }

    public Users login(Users user){
        Users dbUser = usersMapper.selectByUsername(user.getUsername());
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
        usersMapper.updateUserById(user);
    }

    public List<Users> selectAll() {
    return usersMapper.selectUserAll();
    }

    public Users selectByUsername(String username) {
        return usersMapper.selectByUsername(username);
    }
}
