package com.example.service;

import com.example.entity.Courses;
import com.example.entity.StudentCourses;
import com.example.entity.Users;
import com.example.mapper.CoursesMapper;
import com.example.mapper.StudentsMapper;

import java.util.List;

public class CoursesService {

    StudentsMapper studentsMapper = new StudentsMapper();
    CoursesMapper coursesMapper = new CoursesMapper();
    public List<Courses> selectAll() {
        return coursesMapper.selectCourseAll();

    }

    public int add(Courses course) {
        Courses dbCourse = coursesMapper.selectByCoursename(course.getName());
        if(dbCourse != null) {
            return 0;
        }else{
            coursesMapper.insertCourse(course);
            return 1;
        }
    }

    public void delete(Integer id) {
        coursesMapper.deleteCourseById(id);
    }

    public List<StudentCourses> selectByStudent(Users user) {
        return coursesMapper.selectCourseByStudent(user);
    }

    public List<StudentCourses> selectByCourse(Courses course) {
        return coursesMapper.selectCourseByCourse(course);
    }

    public List<StudentCourses> selectAvailable() {
        return coursesMapper.selectAvailable();
    }

    public int addChoice(StudentCourses studentCourse) {
        Users dbuser = studentsMapper.selectByStudentId(studentCourse.getStudentId());
        if(dbuser.getCount() >= 5)return 0;
        List<StudentCourses> dbstudentcourses = this.selectByStudent(dbuser);
        for (StudentCourses dbstudentCourse : dbstudentcourses) {
            if(dbstudentCourse.getCourseId().equals(studentCourse.getCourseId())) {
                return 0;
            }
        }
        coursesMapper.addCoursesChoice(studentCourse);
        dbuser.setCount(dbuser.getCount() + 1);
        studentsMapper.updateStudentById(dbuser);
        return 1;

    }

    public int deleteChoice(StudentCourses studentCourse) {
        Users dbuser = studentsMapper.selectByStudentId(studentCourse.getStudentId());
        if(dbuser.getCount() <= 0)return 0;
        List<StudentCourses> dbstudentcourses = this.selectByStudent(dbuser);
        for (StudentCourses dbstudentCourse : dbstudentcourses) {
            if(dbstudentCourse.getCourseId().equals(studentCourse.getCourseId())) {
                coursesMapper.deleteCourseChoice(studentCourse);
                dbuser.setCount(dbuser.getCount() - 1);
                studentsMapper.updateStudentById(dbuser);
                return 1;
            }
        }
        return 0;
    }

//    public void addChoice(Users user) {
//        mapper.addCourseChoice(user);
//    }
//
//    public void deleteChoice(Users user) {
//        mapper.deleteCourseChoice(user);
//    }


}
