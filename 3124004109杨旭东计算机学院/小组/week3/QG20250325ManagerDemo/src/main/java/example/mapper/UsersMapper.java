package com.example.mapper;

import com.example.connPool.DataSourceManager;
import com.example.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersMapper {

    public Users selectByUsername(String userName){
        String sql = "select * from users where username=?";
        Users user =null;

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new Users();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                user.setId(id);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
            }
            rs.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public void insertUser(Users user){
        String sql = "insert into users(username,password) values(?,?)";

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt =conn.prepareStatement(sql);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.executeUpdate();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Users> selectUserAll(){
        String sql = "select * from users";
        Users user =null;
        List<Users> users=new ArrayList<>();
        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                user = new Users();
                user.setId(id);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                users.add(user);
            }
            rs.close();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public int updateUserById(Users user){
        int count = 0;
        String sql="update users set name=?,username=?,password=?,role=? where id=?";
        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getUsername());
            pstmt.setString(3,user.getPassword());
            pstmt.setString(4,user.getRole());
            pstmt.setInt(5,user.getId());
            count = pstmt.executeUpdate();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public int deleteUserById(Integer id){
        int count = 0;
        String sql="delete from users where id=?";
        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            count = pstmt.executeUpdate();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
