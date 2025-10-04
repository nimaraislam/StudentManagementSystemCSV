package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Validation {

    CsvFiles csv = new CsvFiles();

    public Validation() {
    }

    public String checkFirstName(String firstName) {
        if (firstName.trim().isEmpty()) {
            return "You must enter the first name";
        }
        return null;
    }

    public String checkLastName(String lastName) {
        if (lastName.trim().isEmpty()) {
            return "You must enter the last name";
        }
        return null;
    }

    public String checkPersonNumber(String personNumber, String studentOrTeacher) {
        if (personNumber.trim().isEmpty()) {
            return "You must enter person number!";
        }
        if (personNumber.trim().length() != 12) {
            return "Person number length is " + personNumber.trim().length() + " but the length should be 12!";
        }
        if (!personNumber.trim().matches("\\d+")) {
            return "Invalid person number";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String birthdateString = personNumber.substring(0, 8).trim();
        String year = birthdateString.substring(0, 4).trim();
        String month = birthdateString.substring(4, 6).trim();
        String day = birthdateString.substring(6).trim();
        String birthdayFormat = year + "/" + month + "/" + day;
        try {
            LocalDate.parse(birthdayFormat, formatter);
        } catch (Exception e) {
            return "Wrong Format! First 8 digit should be DOB(yyyyMMdd)";
        }

        if ((studentOrTeacher.trim().toLowerCase()).equals("student")) {
            ArrayList<Student> students = csv.loadStudentFile();
            for (int i = 0; i < students.size(); i++) {
                Student aStudent = students.get(i);
                if (aStudent.getPersonNumber().trim().contains(personNumber)) {
                    return "Sorry!This person number already exists";
                }
            }
        }
        if ((studentOrTeacher.trim().toLowerCase()).equals("teacher")) {
            ArrayList<Teacher> teachers = csv.loadTeacherFile();
            for (int i = 0; i < teachers.size(); i++) {
                Teacher aTeacher = teachers.get(i);
                if (aTeacher.getPersonNumber().trim().contains(personNumber)) {
                    return "Sorry!This person number already exists";
                }
            }
        }
        return null;
    }

    public String checkDateOfBirth(String dateOfBirth) {
        if (dateOfBirth.trim() == null || dateOfBirth.trim().isEmpty()) {
            return "You must enter date of birth!";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dateOfBirth, formatter);
        } catch (Exception e) {
            return "Invalid date format!Please input DD/MM/YYYY.";
        }
        return null;
    }

    public String checkMobileNumber(String mobileNumber) {
        if (mobileNumber.trim().isEmpty()) {
            return "You must enter mobile number!";
        }
        if (!mobileNumber.matches("^07\\d{8}$")) {
            return "Mobile number must start with 07 and be 10 digits long!";
        }
        return null;
    }

    public String checkEmail(String email) {
        if (email.trim().isEmpty()) {
            return "You must enter the email!";
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if (!email.matches(emailRegex)) {
            return "Invalid email address";
        }
        return null;
    }
//--------------------------------------Teacher----------------------------------------

    public String checkSalary(String salaryText) {
        if (salaryText.trim().isEmpty()) {
            return "You must enter the salary!";
        }
        return null;
    }

    public String checkCourse(String courseName) {
        if (courseName.trim().equals("--Select--")) {
            return "You must select the course!";
        }
        return null;
    }
//--------------------------------------Grade----------------------------------------

    public String checkNameIsSelected(Student student) {
        if (student == null) {
            return "You must select the student name!";
        }
        return null;
    }

    public String checkIsStudentExistForGrade(String personNumber) {
        ArrayList<CourseGrade> courseAndGradesList = csv.loadCourseGradeFile();
        for (int i = 0; i < courseAndGradesList.size(); i++) {
            CourseGrade courseGrade = courseAndGradesList.get(i);
            if (courseGrade.getStudent().getPersonNumber().trim().contains(personNumber)) {
                return "Sorry!This students grade has been already added.";
            }
        }
        return null;

    }

}
