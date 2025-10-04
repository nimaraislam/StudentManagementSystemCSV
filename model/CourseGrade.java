package model;

import java.util.HashMap;
import java.util.Map;

public class CourseGrade {

    private Student student;
    Map<Course, Integer> courseGradeMap = new HashMap<>();

    public CourseGrade() {

    }

    public CourseGrade(Student student, Map<Course, Integer> courseGradeMap) {
        this.student = student;
        this.courseGradeMap = new HashMap<>(courseGradeMap);

    }

    public Map<Course, Integer> getCourseGradeMap() {
        
        return courseGradeMap;
    }

    public void setCourseGradeMap(Map<Course, Integer> courseGradeMap) {
        this.courseGradeMap = courseGradeMap;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
