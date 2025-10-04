package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import model.Course;
import model.CsvFiles;
import model.Student;

public class CourseGradeView {

    CsvFiles csv = new CsvFiles();

    Map<Course, JComboBox<Integer>> courseGradeMap = new HashMap<>();

    private JFrame frame;
    private JPanel mainPanel;
    private JButton saveButton;
    private JButton returnHomeButton;
    private JButton clearButton;
    private JPanel comboPanel;
    private JLabel studenNameLabel;
    private JComboBox<Student> studentNameListComboBox;
    private JPanel checkBoxPanel;
    private JLabel courseNameLabel;
    private JTextField gradeTextField;
    private JPanel courseAndGradepanel;
    private JComboBox<Integer> gradeComboBox;

    public CourseGradeView(String title) {
        frame = new JFrame(title);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);

        ImageIcon icon = new ImageIcon("book.jpg");
        frame.setIconImage(icon.getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Dimension panelDimension = new Dimension(500, 500);//(400, 300);
        mainPanel.setPreferredSize(panelDimension);
//-----------------------------load student combo box list -----------------------------------
        comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        studenNameLabel = new JLabel("Student :");
        studenNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ArrayList<Student> studentNameList = csv.loadStudentFile();
        studentNameListComboBox = new JComboBox<>();
        studentNameListComboBox.removeAllItems();
        studentNameListComboBox.addItem(null);
        for (int i = 0; i < studentNameList.size(); i++) {
            studentNameListComboBox.addItem(studentNameList.get(i));
        }
        studentNameListComboBox.setRenderer((JList<? extends Student> list, Student student, int index, boolean isSelected, boolean cellHasFocus) -> {
            JLabel label = new JLabel();
            if (student == null) {
                label.setText("--Select--");
            } else {
                label.setText(student.getFirstName() + " "
                        + student.getLastName() + " -- "
                        + student.getPersonNumber());
            }
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            } else {
                label.setBackground(list.getBackground());
                label.setForeground(list.getForeground());
            }
            label.setOpaque(true);
            return label;
        });
        comboPanel.add(studenNameLabel);
        comboPanel.add(studentNameListComboBox);
        mainPanel.add(comboPanel);
//----------------------------------------------------------------------------------------
        checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //---------------------------------load grades in combo box------------------------------       
        courseNameLabel = new JLabel("Select Course :");
        //studenNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        courseAndGradepanel = new JPanel(new GridLayout(0, 2, 1, 1));

        ArrayList<Course> courses = csv.loadCourses();
        Integer[] grades = {0, 1, 2};
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            JLabel coursesLabel = new JLabel(courses.get(i).getCourseName());
            coursesLabel.setFont(new Font("Arial", Font.BOLD, 16));
            coursesLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gradeComboBox = new JComboBox<>(grades);
            gradeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
            gradeComboBox.setPreferredSize(new Dimension(120, 30));
            courseGradeMap.put(course, gradeComboBox);
            courseAndGradepanel.add(coursesLabel);
            courseAndGradepanel.add(gradeComboBox);
        }
        wrapper.add(courseAndGradepanel, BorderLayout.CENTER);
        mainPanel.add(wrapper, BorderLayout.CENTER);
        //----------------------------------------------------------------------------------------

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        saveButton = new JButton("Save");
        returnHomeButton = new JButton("Return");
        clearButton = new JButton("Clear");

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(returnHomeButton);
        mainPanel.add(buttonPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getReturnHomeButton() {
        return returnHomeButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JComboBox<Student> getStudentNameListComboBox() {
        return studentNameListComboBox;
    }

    public Map<Course, Integer> getSelectedGrades() {
        Map<Course, Integer> getSelectedgrades = new HashMap<>();
        List<Course> courses = new ArrayList<>(courseGradeMap.keySet());
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            JComboBox<Integer> gradeFromComboBox = courseGradeMap.get(course);
            int grade = (Integer) gradeFromComboBox.getSelectedItem();
            getSelectedgrades.put(course, grade);
        }
        return getSelectedgrades;

    }

    public void setGradeTextField(JTextField gradeTextField) {
        this.gradeTextField = gradeTextField;
    }

    public JComboBox<Integer> getGradeComboBox() {
        return gradeComboBox;
    }
}
