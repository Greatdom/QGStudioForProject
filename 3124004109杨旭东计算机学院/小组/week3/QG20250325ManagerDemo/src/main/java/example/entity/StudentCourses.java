package com.example.entity;

public class StudentCourses {
    private Integer courseId;
    private Integer studentId;
    private String courseName;
    private String studentName;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "StudentCourses{" +
                "courseId=" + courseId +
                ", studentId=" + studentId +
                ", courseName='" + courseName + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
