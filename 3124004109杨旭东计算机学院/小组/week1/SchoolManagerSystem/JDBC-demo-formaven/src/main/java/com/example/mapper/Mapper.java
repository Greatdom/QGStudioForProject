package com.example.mapper;

import com.example.entity.Courses;
import com.example.entity.StudentCourses;
import com.example.entity.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
//    private static final String url="jdbc:mysql:///qg?useSSL=false";
//    private static final String mysqlUsername = "root";
//    private static final String mysqlPassword = "Aa1341512355";
//    private static Connection conn;
//
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn= DriverManager.getConnection(url,mysqlUsername,mysqlPassword);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public static Connection getConn() {
//        return conn;
//    }


    private static final String url="jdbc:mysql://localhost:3306/qg?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String mysqlUsername = "root";
    private static final String mysqlPassword = "Aa1341512355";
    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url,mysqlUsername,mysqlPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConn() {
        return conn;
    }

    public Users selectByUsername(String userName) {
        String sql = "select * from users where username=?";
        Users user = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
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
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return user;
    }



    public Users selectByStudentname(String userName) {
        String sql = "select * from students where username=?";
        Users user = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                user = new Users();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String phone = rs.getString("phone");
                Integer count = rs.getInt("count");

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return user;
    }

    public void insertUser(Users user) {
        String sql="insert into users(username,password) values(?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertStudent(Users user) {
        String sql="insert into students(username,password) values(?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Users> selectUserAll() {
        String sql = "select * from users;";
        Users user = null;
        List<Users> users = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public List<Users> selectStudentAll() {
        String sql = "select * from students;";
        Users user = null;
        List<Users> users = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String phone = rs.getString("phone");
                Integer count = rs.getInt("count");
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public int updateUserById(Users user) {
        int count=0;
        String sql = "update users set name=?,username=?,password=?,role=? where id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getId());
            count = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public int updateStudentById(Users user) {
        int count=0;
        String sql="update students set name=?,username=?,password=?,role=?,phone=? where id=?";
        try {
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getPhone());
            pstmt.setInt(6, user.getId());
            count = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public int deleteUserById(int id) {
        int count;
        String sql="delete from users where id=?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public int deleteStudentById(int id) {
        int count;
        String sql="delete from students where id=?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public List<Courses> selectCourseAll() {
        List<Courses> courses = new ArrayList<>();
        String sql = "select * from courses;";
        Courses course = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                course = new Courses();
                course.setId(id);
                course.setName(name);
                course.setStatus(status);
                courses.add(course);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    public int deleteCourseById(Integer id) {
        int count;
        String sql="delete from courses where id=?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    public Courses selectByCoursename(String Name) {
        Courses course = null;
        String sql = "select * from courses where name=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Name);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                course = new Courses();
                course.setId(id);
                course.setName(name);
                course.setStatus(status);

            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    public void insertCourse(Courses course) {
        String sql = "insert into courses(name,status) values(?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course.getName());
            pstmt.setString(2, course.getStatus());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    public Users selectByStudentId(Integer Id) {
        String sql = "select * from students where id=?";
        Users user = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                user = new Users();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String phone = rs.getString("phone");
                Integer count = rs.getInt("count");

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return user;
    }

    public List<StudentCourses> selectCourseByStudent(Users user) {
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
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                studentCourse = new StudentCourses();
                int courseId = rs.getInt("course_id");
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("studentName");
                String courseName = rs.getString("courseName");
                studentCourse.setStudentId(studentId);
                studentCourse.setCourseId(courseId);
                studentCourse.setStudentName(studentName);
                studentCourse.setCourseName(courseName);
                studentCourses.add(studentCourse);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentCourses;
    }

    public List<StudentCourses> selectCourseByCourse(Courses course) {
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
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, course.getId());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                studentCourse = new StudentCourses();
                int courseId = rs.getInt("course_id");
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("studentName");
                String courseName = rs.getString("courseName");
                studentCourse.setStudentId(studentId);
                studentCourse.setCourseId(courseId);
                studentCourse.setStudentName(studentName);
                studentCourse.setCourseName(courseName);
                studentCourses.add(studentCourse);
            }
            rs.close();
            pstmt.close();
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
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentCourse.getStudentId());
            pstmt.setInt(2, studentCourse.getCourseId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteCourseChoice(StudentCourses studentCourse) {
        String sql = "delete from student_courses where student_id = ? and course_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentCourse.getStudentId());
            pstmt.setInt(2, studentCourse.getCourseId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
