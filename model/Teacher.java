package model;

import java.time.LocalDate;

public class Teacher extends Person {

    private double salary;
    private Course course;

    public Teacher() {

    }

    public Teacher(String firstName, String lastName, String personNumber, LocalDate dateOfBirth, String mobileNumber, String email, double salary, Course course) {
        super(firstName, lastName, personNumber, dateOfBirth, mobileNumber, email);
        this.salary = salary;
        this.course = course;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String getInformation() {
        return super.getInformation() + "\n"
                + "Salary: " + salary + "\n"
                + "Course: " + course.getCourseName() + "\n"
                + "Role: Teacher";
    }
}
