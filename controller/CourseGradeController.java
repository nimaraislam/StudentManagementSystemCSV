package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Course;
import model.CourseGrade;
import model.CsvFiles;
import model.Student;
import model.Validation;
import view.CourseGradeView;
import view.HomePageView;

public class CourseGradeController {

    private CourseGradeView view;

    public CourseGradeController(CourseGradeView view) {
        this.view = view;
    }

    public void initView() {
        view.getStudentNameListComboBox().setSelectedIndex(0);
        view.getGradeComboBox().setSelectedIndex(0);
    }

    public void initController() {
        view.getSaveButton().addActionListener(e -> save());
        view.getReturnHomeButton().addActionListener(e -> returnHome());
    }

    private void save() {
        ArrayList<CourseGrade> courseGradeList = new ArrayList<>();
        String message = null;
        Validation validation = new Validation();
        Student student = (Student) view.getStudentNameListComboBox().getSelectedItem();
        if (student == null) {
            message = validation.checkNameIsSelected(student);
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
            view.getStudentNameListComboBox().requestFocus();
            return;
        }
        String firstName = student.getFirstName().trim();
        String lastName = student.getLastName().trim();
        String personNumber = student.getPersonNumber().trim();
        message = validation.checkIsStudentExistForGrade(personNumber);
        if (message != null) {
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Map<Course, Integer> courseGradeMap = new HashMap<>();
        courseGradeMap = view.getSelectedGrades();
        CourseGrade courseGrade = new CourseGrade();
        courseGrade.setStudent(new Student(firstName, lastName, personNumber));
        courseGrade.setCourseGradeMap(courseGradeMap);
        courseGradeList.add(courseGrade);
        CsvFiles csv = new CsvFiles();
        csv.saveCourseGradeFile(courseGradeList);
        JOptionPane.showMessageDialog(null, "Information has been saved successfully ", "Info", JOptionPane.INFORMATION_MESSAGE);
        initView();
        /*  for (int i = 0; i < courseGradeList.size(); i++) {
            courseGrade = courseGradeList.get(i);
            String name = courseGrade.getStudent().getFirstName();
            List<Course> courses = new ArrayList<>(courseGradeMap.keySet());
            for (int J = 0; J < courses.size(); J++) {
                Course course = courses.get(J);
                String courseName = courses.get(J).getCourseName();
                int grade = courseGradeMap.get(course);
                System.out.println(name + " " + courseName + " " + grade);

            }
        }*/
    }

    private void returnHome() {
        HomePageView homeView = new HomePageView();
        view.getFrame().dispose();
        new HomePageController(homeView).initController();
    }

}
