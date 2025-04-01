package com.example.mapper;

import com.example.connPool.DataSourceManager;
import com.example.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentsMapper {
    public Users selectByStudentname(String userName){
        String sql = "select * from students where username=?";
        Users user =null;
        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                user = new Users();
                int id =rs.getInt("id");
                String name =rs.getString("name");
                String username =rs.getString("username");
                String password =rs.getString("password");
                String role =rs.getString("role");
                String phone =rs.getString("phone");
                Integer count =rs.getInt("count");

                user.setId(id);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                user.setPhone(phone);
                user.setCount(count);
            }
            rs.close();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public void insertStudent(Users user){
        String sql ="insert into students(username,password) values(?,?)";
        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.executeUpdate();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Users> selectStudentAll(){
        String sql = "select * from students";
        Users user =null;
        List<Users> users =new ArrayList<>();
        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id =rs.getInt("id");
                String name =rs.getString("name");
                String username =rs.getString("username");
                String password =rs.getString("password");
                String role =rs.getString("role");
                String phone =rs.getString("phone");
                Integer count =rs.getInt("count");
                user = new Users();
                user.setId(id);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                user.setPhone(phone);
                user.setCount(count);
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
    public int updateStudentById(Users user){
        int count =0;
        String sql="update students set name=?,username=?,password=?,role=?where id=?";

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
    public int deleteStudentById(Integer id){
        int count =0;
        String sql="delete from students where id=?";

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
    public Users selectByStudentId(Integer Id){
        String sql = "select * from students where id=?";
        Users user =null;

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,Id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                user = new Users();
                int id =rs.getInt("id");
                String name =rs.getString("name");
                String username =rs.getString("username");
                String password =rs.getString("password");
                String role =rs.getString("role");
                String phone =rs.getString("phone");
                Integer count =rs.getInt("count");

                user.setId(id);
                user.setName(name);
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                user.setPhone(phone);
                user.setCount(count);
            }
            rs.close();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
