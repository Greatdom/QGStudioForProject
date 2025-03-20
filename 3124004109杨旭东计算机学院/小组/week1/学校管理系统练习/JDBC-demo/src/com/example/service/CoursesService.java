package com.example.service;

import com.example.entity.Courses;
import com.example.entity.StudentCourses;
import com.example.entity.Users;
import com.example.mapper.Mapper;

import java.util.List;

public class CoursesService {
    Mapper mapper = new Mapper();
    public List<Courses> selectAll() {
        return mapper.selectCourseAll();

    }

    public int add(Courses course) {
        Courses dbCourse = mapper.selectByCoursename(course.getName());
        if(dbCourse != null) {
            return 0;
        }else{
            mapper.insertCourse(course);
            return 1;
        }
    }

    public void delete(Integer id) {
        mapper.deleteCourseById(id);
    }

    public List<StudentCourses> selectByStudent(Users user) {
        return mapper.selectCourseByStudent(user);
    }

    public List<StudentCourses> selectByCourse(Courses course) {
        return mapper.selectCourseByCourse(course);
    }

    public List<StudentCourses> selectAvailable() {
        return mapper.selectAvailable();
    }

    public int addChoice(StudentCourses studentCourse) {
        Users dbuser = mapper.selectByStudentId(studentCourse.getStudentId());
        if(dbuser.getCount() >= 5)return 0;
        List<StudentCourses> dbstudentcourses = this.selectByStudent(dbuser);
        for (StudentCourses dbstudentCourse : dbstudentcourses) {
            if(dbstudentCourse.getCourseId().equals(studentCourse.getCourseId())) {
                return 0;
            }
        }
        mapper.addCoursesChoice(studentCourse);
        dbuser.setCount(dbuser.getCount() + 1);
        mapper.updateUserById(dbuser);
        return 1;

    }

    public int deleteChoice(StudentCourses studentCourse) {
        Users dbuser = mapper.selectByStudentId(studentCourse.getStudentId());
        if(dbuser.getCount() <= 0)return 0;
        List<StudentCourses> dbstudentcourses = this.selectByStudent(dbuser);
        for (StudentCourses dbstudentCourse : dbstudentcourses) {
            if(dbstudentCourse.getCourseId().equals(studentCourse.getCourseId())) {
                mapper.deleteCourseChoice(studentCourse);
                dbuser.setCount(dbuser.getCount() - 1);
                mapper.updateUserById(dbuser);
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
