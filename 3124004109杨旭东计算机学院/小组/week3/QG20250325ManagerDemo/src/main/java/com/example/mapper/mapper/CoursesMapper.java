package com.example.mapper;

import com.example.connPool.DataSourceManager;
import com.example.entity.Courses;
import com.example.entity.StudentCourses;
import com.example.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursesMapper {
    public List<Courses> selectCourseAll(){
        List<Courses> courses = new ArrayList<>();
        String sql = "select * from courses";
        Courses course = null;

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                course =new Courses();
                course.setId(id);
                course.setName(name);
                course.setStatus(status);
                courses.add(course);
            }
            rs.close();
            ps.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }
    public int deleteCourseById(Integer id){
        int count=0;
        String sql = "delete from courses where id=?";

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public Courses selectByCoursename(String Name){
        Courses course = null;
        String sql = "select * from courses where name=?";

        try {
            Connection conn =DataSourceManager.getConn();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, Name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                course =new Courses();
                course.setId(id);
                course.setName(name);
                course.setStatus(status);
            }
            rs.close();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }
    public void insertCourse(Courses course){
        String sql = "insert into courses(name,status) values(?,?)";

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, course.getName());
            pstmt.setString(2, course.getStatus());
            pstmt.executeUpdate();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<StudentCourses> selectCourseByStudent(Users user){
        String sql = "SELECT student_courses.*, " +
                "       students.username AS studentName, " +
                "       courses.name AS courseName " +
                "FROM student_courses " +
                "LEFT JOIN students ON student_courses.student_id = students.id " +
                "LEFT JOIN courses ON student_courses.course_id = courses.id " +
                "WHERE student_courses.student_id = ?";
        List<StudentCourses> studentCourses = new ArrayList<>();
        StudentCourses studentCourse = null;

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                studentCourse = new StudentCourses();
                int courseId = rs.getInt("course_id");
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String courseName = rs.getString("course_name");
                studentCourse.setStudentId(studentId);
                studentCourse.setCourseId(courseId);
                studentCourse.setStudentName(studentName);
                studentCourse.setCourseName(courseName);
                studentCourses.add(studentCourse);
            }
            rs.close();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentCourses;
    }
    public List<StudentCourses> selectCourseByCourse(Courses course){
        String sql = "SELECT student_courses.*, " +
                "       students.username AS studentName, " +
                "       courses.name AS courseName " +
                "FROM student_courses " +
                "LEFT JOIN students ON student_courses.student_id = students.id " +
                "LEFT JOIN courses ON student_courses.course_id = courses.id " +
                "WHERE student_courses.course_id = ?";
        List<StudentCourses> studentCourses = new ArrayList<>();
        StudentCourses studentCourse = null;

        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setInt(1, course.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                studentCourse = new StudentCourses();
                int courseId = rs.getInt("course_id");
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                String courseName = rs.getString("course_name");
                studentCourse.setStudentId(studentId);
                studentCourse.setCourseId(courseId);
                studentCourse.setStudentName(studentName);
                studentCourse.setCourseName(courseName);
                studentCourses.add(studentCourse);
            }
            rs.close();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentCourses;
    }
    public List<StudentCourses> selectAvailable() {
        return null;
    }
    public void addCoursesChoice(StudentCourses studentCourse) {
        String sql = "insert into student_courses(student_id,course_id) values(?,?)";
        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentCourse.getStudentId());
            pstmt.setInt(2, studentCourse.getCourseId());
            pstmt.executeUpdate();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteCourseChoice(StudentCourses studentCourse) {
        String sql = "delete from student_courses where student_id = ? and course_id = ?";
        try {
            Connection conn = DataSourceManager.getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentCourse.getStudentId());
            pstmt.setInt(2, studentCourse.getCourseId());
            pstmt.executeUpdate();
            pstmt.close();
            DataSourceManager.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
