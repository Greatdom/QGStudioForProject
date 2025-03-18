package com.example.entity;

public class StudentCourses {
    private Integer courseId;
    private Integer studentId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "StudentCourses{" +
                "courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }
}
