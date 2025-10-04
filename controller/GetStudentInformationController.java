package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Course;
import model.CourseGrade;
import model.CsvFiles;
import model.Student;
import view.GetStudentInformationView;
import view.HomePageView;

public class GetStudentInformationController {

    private GetStudentInformationView view;

    public GetStudentInformationController(GetStudentInformationView view) {
        this.view = view;
    }

    public void initView() {
        view.getStudentNameListComboBox().setSelectedIndex(0);
        view.getShowInfoTextArea().setText("");
    }

    public void initController() {
        view.getStudentNameListComboBox().addActionListener(e -> getInformation());
        view.getClearButton().addActionListener(e -> initView());
        view.getReturnHomeButton().addActionListener(e -> returnHome());
    }

    public void getInformation() {
        Student aStudent = (Student) view.getStudentNameListComboBox().getSelectedItem();
        String personNumber = aStudent.getPersonNumber().trim();
        CsvFiles csv = new CsvFiles();
        view.getShowInfoTextArea().setText("");
        ArrayList<Student> students = csv.loadStudentFile();
        for (int i = 0; i < students.size(); i++) {
            Student studentFromSavedFile = students.get(i);
            if (studentFromSavedFile.getPersonNumber().contains(personNumber)) {
                view.getShowInfoTextArea().append(aStudent.getInformation() + "\n\n");
            }
        }
        boolean found = false;
        double totalGrade = 0.00;
        int courseCount = 0;
        ArrayList<CourseGrade> courseGradesFromFile = csv.loadCourseGradeFile();
        for (int i = 0; i < courseGradesFromFile.size(); i++) {
            CourseGrade courseGrade = courseGradesFromFile.get(i);
            if (courseGrade.getStudent().getPersonNumber().trim().contains(personNumber)) {
                Map<Course, Integer> courseGradesMap = courseGrade.getCourseGradeMap();
                found = true;
                if (found) {
                    List<Course> courseFromFile = new ArrayList<>(courseGradesMap.keySet());
                    for (int j = 0; j < courseFromFile.size(); j++) {
                        Course course = courseFromFile.get(j);
                        String courseName = course.getCourseName();
                        int grade = courseGradesMap.get(course);
                        totalGrade += grade;
                        courseCount++;
                        view.getShowInfoTextArea().append("Course:" + courseName + " Grade: " + grade + "\n");
                        //System.out.println("Course:" + courseGrade.getCourseGradeMap());
                    }
                }

            }
        }
        if (found) {
            view.getShowInfoTextArea().append("\n");
            Double average = totalGrade / courseCount;
            String averageText = average.toString();
            view.getShowInfoTextArea().append("Average: " + averageText);
        }

        if (!found) {
            view.getShowInfoTextArea().append("The result has not been finalized yet.");

        }
    }

    private void returnHome() {
        HomePageView homeView = new HomePageView();
        view.getFrame().dispose();
        new HomePageController(homeView).initController();
    }
}
