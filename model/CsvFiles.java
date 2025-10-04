package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvFiles {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CsvFiles() {
    }

    public ArrayList<Course> loadCourses() {

        String path = "Course_Data.csv";
        String line;
        ArrayList<Course> courses = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            //skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] values = line.split(",");
                String courseId = values[0].trim();
                String courseName = values[1].trim();
                Course course = new Course(courseId, courseName);
                courses.add(course);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public void saveStudentToCsvFile(ArrayList<Student> students) {

        File file = new File("Student_Data.csv");
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            if (!fileExists) {
                writer.append("firstName,lastName,personNumber,dateOfBirth,mobileNumber,email\n");
            }
            for (int i = 0; i < students.size(); i++) {
                Student aStudent = students.get(i);

                writer.append(aStudent.getFirstName())
                        .append(",")
                        .append(aStudent.getLastName())
                        .append(",")
                        .append(aStudent.getPersonNumber())
                        .append(",")
                        .append(aStudent.getDateOfBirth().format(formatter))
                        .append(",")
                        .append(aStudent.getMobileNumber())
                        .append(",")
                        .append(aStudent.getEmail())
                        .append("\n");
            }
            writer.close();
            System.out.println("CSV file created!");
        } catch (Exception e) {
            System.out.println("Unimplemented function!");
        }
    }

    public ArrayList<Student> loadStudentFile() {
        // String path = "Student_data.csv";
        File file = new File("Student_data.csv");
        if (file.length() == 0) {
            return new ArrayList<>();
        }
        String line;
        ArrayList<Student> students = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] values = line.split(",");
                String firstName = values[0];
                String lastName = values[1];
                String personNumber = values[2];
                String rawDate = values[3].replace("\"", "").trim();
                LocalDate dateOfBirth = LocalDate.parse(rawDate, formatter);
                String mobileNumber = values[4];
                String email = values[5];

                Student aStudent = new Student(firstName, lastName, personNumber, dateOfBirth, mobileNumber, email);
                students.add(aStudent);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (students.get(j).getFirstName().compareToIgnoreCase(students.get(j + 1).getFirstName()) > 0) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        return students;
    }

    public ArrayList<Student> loadStudentNameWithPersonNum() {

        ArrayList<Student> students = loadStudentFile();
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (students.get(j).getFirstName().compareToIgnoreCase(students.get(j + 1).getFirstName()) > 0) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        //System.out.println("----------------------");
        //for (int i = 0; i < n; i++) {
        //    System.out.println(students.get(i).getFirstName());
        //}
        return students;

    }

    public void saveTeacherToCsvFile(ArrayList<Teacher> teachers) {

        File file = new File("Teacher_Data.csv");
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            if (!fileExists) {
                writer.append("firstName,lastName,personNumber,dateOfBirth,mobileNumber,email,salary,course\n");
            }
            for (int i = 0; i < teachers.size(); i++) {
                Teacher aTeacher = teachers.get(i);

                writer.append(aTeacher.getFirstName())
                        .append(",")
                        .append(aTeacher.getLastName())
                        .append(",")
                        .append(aTeacher.getPersonNumber())
                        .append(",")
                        .append(aTeacher.getDateOfBirth().format(formatter))
                        .append(",")
                        .append(aTeacher.getMobileNumber())
                        .append(",")
                        .append(aTeacher.getEmail())
                        .append(",")
                        .append(Double.toString(aTeacher.getSalary()))
                        .append(",")
                        .append(aTeacher.getCourse().getCourseName())
                        .append("\n");
            }
            writer.close();
            System.out.println("CSV file created!");
        } catch (Exception e) {
            System.out.println("Unimplemented function!");
        }
    }

    public ArrayList<Teacher> loadTeacherFile() {
        //String path = "Teacher_data.csv";
        File file = new File("Teacher_data.csv");
        if (file.length() == 0) {
            return new ArrayList<>();
        }
        String line;
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] values = line.split(",");
                String firstName = values[0];
                String lastName = values[1];
                String personNumber = values[2];
                String rawDate = values[3].replace("\"", "").trim();
                LocalDate dateOfBirth = LocalDate.parse(rawDate, formatter);
                String mobileNumber = values[4];
                String email = values[5];
                String salaryText = values[6];
                double salary = Double.parseDouble(salaryText);
                String courseName = values[7];
                Course course = new Course(null, courseName);

                Teacher aTeacher = new Teacher(firstName, lastName, personNumber, dateOfBirth, mobileNumber, email, salary, course);
                teachers.add(aTeacher);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public void saveCourseGradeFile(ArrayList<CourseGrade> coursesAndGrades) {

        File file = new File("CourseGrade_Data.csv");
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            if (!fileExists) {
                writer.append("firstName,lastName,personNumber,course,grade\n");
            }
            for (int i = 0; i < coursesAndGrades.size(); i++) {
                CourseGrade courseGrade = coursesAndGrades.get(i);
                List<Course> courses = new ArrayList<>(courseGrade.getCourseGradeMap().keySet());
                for (int j = 0; j < courses.size(); j++) {
                    Course course = courses.get(j);
                    writer.append(courseGrade.getStudent().getFirstName())
                            .append(",")
                            .append(courseGrade.getStudent().getLastName())
                            .append(",")
                            .append(courseGrade.getStudent().getPersonNumber())
                            .append(",")
                            .append(courses.get(j).getCourseName())
                            .append(",")
                            .append(Integer.toString(courseGrade.getCourseGradeMap().get(course)))
                            .append("\n");
                }
            }

            writer.close();
            System.out.println("CSV file created!");
        } catch (Exception e) {
            System.out.println("Unimplemented function!");
        }
    }

    public ArrayList<CourseGrade> loadCourseGradeFile() {
        File file = new File("CourseGrade_data.csv");
        if (file.length() == 0) {
            return new ArrayList<>();
        }
        String line;
        ArrayList<CourseGrade> courseGrades = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                String[] values = line.split(",");
                String firstName = values[0];
                String lastName = values[1];
                String personNumber = values[2];
                Student aStudent = new Student(firstName, lastName, personNumber);
                String courseName = values[3];
                String gradeText = values[4];
                int grade = Integer.parseInt(gradeText);
                Course course = new Course(null, courseName);
                Map<Course, Integer> courseGradeMap = new HashMap<>();
                courseGradeMap.put(course, grade);
                CourseGrade courseGrade = new CourseGrade(aStudent, courseGradeMap);
                courseGrades.add(courseGrade);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseGrades;
    }

}
