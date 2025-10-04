package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Course;
import model.CourseGrade;
import model.CsvFiles;
import model.Student;
import model.Teacher;
import model.Validation;
import view.HomePageView;
import view.PersonView;

public class PersonController {

    private PersonView view;
    ArrayList<Student> students;
    ArrayList<Teacher> teachers;
    Validation validation = new Validation();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ArrayList<CourseGrade> courseGrades = new ArrayList<>();
    String inputCourseName;
    //CourseGrade courseGrade;

    public PersonController() {
    }

    public PersonController(PersonView view) {
        this.view = view;
    }

    public void initView() {
        view.getButtonGroup().clearSelection();
        view.getSalaryTextField().setVisible(false);
        view.getSalaryLabel().setVisible(false);
        view.getCourseListLabel().setVisible(false);
        view.getCourseListComboBox().setVisible(false);
        view.getFirstNameTextField().setText(" ");
        view.getLastNameTextField().setText(" ");
        view.getPersonNumberTextField().setText(" ");
        view.getDateOfBirthTextField().setText("");
        view.getDateOfBirthTextField().setEnabled(true);
        view.getMobileNumberTextField().setText("");
        view.getEmailTextField().setText("");
        view.getSalaryTextField().setText("");
        view.getCourseListComboBox().setSelectedIndex(0);
    }

    public void initController() {
        view.getStudentRadioButton().addActionListener(e -> selectStudent());
        view.getTeacherRadioButton().addActionListener(e -> selectTeacher());
        view.getPersonNumberTextField().addActionListener(e -> setBirthDate());
        view.getsaveInformationButton().addActionListener(e -> saveInformation());
        view.getClearButton().addActionListener(e -> initView());
        view.getReturnHomeButton().addActionListener(e -> returnHome());
    }

    private void saveInformation() {
        String studentOrTeacher = null;
        if (view.getStudentRadioButton().isSelected()) {
            studentOrTeacher = "student";
        }
        if (view.getTeacherRadioButton().isSelected()) {
            studentOrTeacher = "teacher";
        }
        if (studentOrTeacher == null) {

            JOptionPane.showMessageDialog(null, "Select the student or teacher option", "Info", JOptionPane.ERROR_MESSAGE);
            view.getStudentRadioButton().requestFocus();
            return;
        }
        String firstName = view.getFirstNameTextField().getText().trim();
        String lastName = view.getLastNameTextField().getText().trim();
        String personNumber = view.getPersonNumberTextField().getText().trim();
        String mobileNumber = view.getMobileNumberTextField().getText().trim();
        String email = view.getEmailTextField().getText().trim();

        String message;

        message = validation.checkFirstName(firstName);
        if (message != null) {
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
            view.getFirstNameTextField().requestFocus();
            return;
        }
        message = validation.checkLastName(lastName);
        if (message != null) {
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
            view.getLastNameTextField().requestFocus();
            return;
        }
        message = validation.checkPersonNumber(personNumber, studentOrTeacher);
        if (message != null) {
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
            view.getPersonNumberTextField().requestFocus();
            return;
        }
        String birthday = view.getDateOfBirthTextField().getText().trim();
        message = validation.checkDateOfBirth(birthday);
        if (message != null) {
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
            view.getDateOfBirthTextField().requestFocus();
            return;
        }
        LocalDate dateofBirth = LocalDate.parse(birthday, formatter);

        message = validation.checkMobileNumber(mobileNumber);
        if (message != null) {
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
            view.getMobileNumberTextField().requestFocus();
            return;
        }
        message = validation.checkEmail(email);
        if (message != null) {
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
            view.getEmailTextField().requestFocus();
            return;
        }
        if ((studentOrTeacher.trim()).equals("student")) {
            Student aStudent = new Student();
            aStudent.setFirstName(firstName);
            aStudent.setLastName(lastName);
            aStudent.setPersonNumber(personNumber);
            aStudent.setDateOfBirth(dateofBirth);
            aStudent.setMobileNumber(mobileNumber);
            aStudent.setEmail(email);
            students = new ArrayList<>();
            students.add(aStudent);
            CsvFiles csv = new CsvFiles();
            csv.saveStudentToCsvFile(students);
            JOptionPane.showMessageDialog(null, "Information has been saved successfully ", "Info", JOptionPane.INFORMATION_MESSAGE);
            initView();
        }
        if ((studentOrTeacher.trim()).equals("teacher")) {
            String salaryText = view.getSalaryTextField().getText().trim();
            message = validation.checkSalary(salaryText);
            if (message != null) {
                JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
                view.getSalaryTextField().requestFocus();
                return;
            }
            double salary = Double.parseDouble(salaryText);
            String courseName = (String) view.getCourseListComboBox().getSelectedItem();
            message = validation.checkCourse(courseName);
            if (message != null) {
                JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.ERROR_MESSAGE);
                view.getCourseListComboBox().requestFocus();
                return;
            }
            Teacher aTeacher = new Teacher();
            aTeacher.setFirstName(firstName);
            aTeacher.setLastName(lastName);
            aTeacher.setPersonNumber(personNumber);
            aTeacher.setDateOfBirth(dateofBirth);
            aTeacher.setMobileNumber(mobileNumber);
            aTeacher.setEmail(email);
            aTeacher.setSalary(salary);

            aTeacher.setCourse(new Course(null, courseName));
            teachers = new ArrayList<>();
            teachers.add(aTeacher);
            CsvFiles csv = new CsvFiles();
            csv.saveTeacherToCsvFile(teachers);
            JOptionPane.showMessageDialog(null, "Information has been saved successfully ", "Info", JOptionPane.INFORMATION_MESSAGE);
            initView();
        }

    }

    private void returnHome() {
        HomePageView homeView = new HomePageView();
        new HomePageController(homeView).initController();
        view.getFrame().dispose();
    }

    /* private void addCourses() {
        courseGrades = new ArrayList<>();
        inputCourseName = (String) view.getCourseListComboBox().getSelectedItem();
        // view.getShowCourseValue().setText(courseName);
        int inputGrade = (int) view.getGradeListComboBox().getSelectedItem();
        //view.getShowGradeValue().setText(String.valueOf(grade));
        CourseGrade courseGrade = new CourseGrade(inputCourseName, inputGrade);
        boolean exist = false;
        for (int i = 0; i < courseGrades.size(); i++) {

            CourseGrade cg = courseGrades.get(i);
            if (cg.getCourseName().trim().equals(inputCourseName.trim())) {
                exist = true;
            }
        }
        if (exist) {
            JOptionPane.showMessageDialog(null, "This course already has been added: ", "Info", JOptionPane.INFORMATION_MESSAGE);

        }
        if (!exist) {
            courseGrades.add(courseGrade);
            view.getTableModel().addRow(new Object[]{inputCourseName, inputGrade});
        }

        // view.getTable().setModel((TableModel) courseGrades);
    } */
    private void selectStudent() {
        if (view.getStudentRadioButton().isSelected()) {
            view.getSalaryLabel().setVisible(false);
            view.getSalaryTextField().setVisible(false);
            view.getCourseListLabel().setVisible(false);
            view.getCourseListComboBox().setVisible(false);
        }
    }

    private void selectTeacher() {
        if (view.getTeacherRadioButton().isSelected()) {
            view.getSalaryLabel().setVisible(true);
            view.getSalaryTextField().setVisible(true);
            view.getCourseListLabel().setVisible(true);
            view.getCourseListComboBox().setVisible(true);
        }
    }

    private void setBirthDate() {
        String birthdateString = view.getPersonNumberTextField().getText().substring(0, 8).trim();
        String birthdayFormat = birthdateString.substring(6).trim() + "/"
                + birthdateString.substring(4, 6).trim() + "/"
                + birthdateString.substring(0, 4).trim();
        view.getDateOfBirthTextField().setText(birthdayFormat);
        view.getDateOfBirthTextField().setEnabled(false);
        view.getMobileNumberTextField().requestFocus();
    }
}
