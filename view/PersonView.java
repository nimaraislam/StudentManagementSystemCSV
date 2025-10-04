package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.Course;
import model.CsvFiles;

public class PersonView {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel panel;
    private JPanel comboPanel;

    private JLabel studentOrTeacherJLabel;
    private JRadioButton studentRadioButton;
    private JRadioButton teacherRadioButton;
    ButtonGroup buttonGroup;

    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel personNumberLabel;
    private JLabel dateOfBirthLabel;
    private JLabel mobileNumberLabel;
    private JLabel emailLabel;
    private JLabel courseListLabel;
    private JLabel showCourseLabel;
    private JLabel showCourseValue;
    private JLabel showGradeLabel;
    private JLabel showGradeValue;

    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField personNumberTextField;
    private JTextField dateOfBirthTextField;
    private JTextField mobileNumberTextField;
    private JTextField emailTextField;

    private JComboBox<String> courseListComboBox;
    private JComboBox<Integer> gradeListComboBox;

    private JTable table;

    private JButton addCourseButton;
    private JButton saveInformationButton;
    private JButton hello;
    private JButton returnHomeButton;
    private JLabel gradeListLabel;
    private DefaultTableModel tableModel;
    private JTable courseTable;
    private final JButton clearButton;
    private JLabel salaryLabel;
    private final JTextField salaryTextField;

    public PersonView(String title) {
        //------------------------------Frame---------------------------------
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);

        ImageIcon icon = new ImageIcon("person.png");
        frame.setIconImage(icon.getImage());

        //------------------------------Main panel---------------------------------
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Dimension panelDimension = new Dimension(500, 500);//(400, 300);
        mainPanel.setPreferredSize(panelDimension);

        //------------------------------Radio Panel---------------------------------
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        studentOrTeacherJLabel = new JLabel("Select:");
        studentOrTeacherJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        studentRadioButton = new JRadioButton("Student");
        teacherRadioButton = new JRadioButton("Teacher");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(studentRadioButton);
        buttonGroup.add(teacherRadioButton);
        radioPanel.add(studentOrTeacherJLabel);
        radioPanel.add(studentRadioButton);
        radioPanel.add(teacherRadioButton);
        mainPanel.add(radioPanel);
        //------------------------------Form Panel---------------------------------
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        firstNameLabel = new JLabel("First Name :");
        firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstNameTextField = new JTextField();

        lastNameLabel = new JLabel("Last Name :");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastNameTextField = new JTextField();

        personNumberLabel = new JLabel("Person Number(yyyymmddxxxx):");
        personNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        personNumberTextField = new JTextField();

        dateOfBirthLabel = new JLabel("Date of Birth(dd/mm/yyyy):");
        dateOfBirthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateOfBirthTextField = new JTextField();

        mobileNumberLabel = new JLabel("Mobile Number(07XXXXXXXX):");
        mobileNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        mobileNumberTextField = new JTextField();

        emailLabel = new JLabel("Email :");
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        emailTextField = new JTextField();

        salaryLabel = new JLabel("Salary :");
        salaryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        salaryLabel.setVisible(false);
        salaryTextField = new JTextField();
        salaryTextField.setVisible(false);

        courseListLabel = new JLabel("Courses :");
        courseListLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        courseListLabel.setVisible(false);

        CsvFiles csv = new CsvFiles();
        ArrayList<Course> courseList = csv.loadCourses();
        courseListComboBox = new JComboBox<>();
        courseListComboBox.removeAllItems();
        String courseName;
        courseListComboBox.addItem("--Select--");
        for (int i = 0; i < courseList.size(); i++) {
            courseName = courseList.get(i).getCourseName();
            courseListComboBox.addItem(courseName);
        }
        courseListComboBox.setVisible(false);

        //comboPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        /*  comboPanel.setVisible(false);
        courseListLabel = new JLabel("Courses :");
        courseListLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        courseListLabel.setVisible(false);
        CsvFiles csv = new CsvFiles();
        ArrayList<Course> courseList = csv.loadCourses();
        courseListComboBox = new JComboBox<>();
        courseListComboBox.removeAllItems();
        String courseId;
        String courseName;
        courseListComboBox.addItem("--Select--");
        for (int i = 0; i < courseList.size(); i++) {
            courseName = courseList.get(i).getCourseName();
            courseListComboBox.addItem(courseName);
            }
        courseListComboBox.setVisible(false);*/
        //------------------------------combo panel---------------------------------
        /*comboPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        comboPanel.setVisible(false);
        courseListLabel = new JLabel("Courses :");
        courseListLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        courseListLabel.setVisible(false);
        CsvFiles csv = new CsvFiles();
        ArrayList<Course> courseList = csv.loadCourses();
        courseListComboBox = new JComboBox<>();
        courseListComboBox.removeAllItems();
        String courseId;
        String courseName;
        courseListComboBox.addItem("--Select--");
        for (int i = 0; i < courseList.size(); i++) {
            courseName = courseList.get(i).getCourseName();
            courseListComboBox.addItem(courseName);
            }
            courseListComboBox.setVisible(false);*/
        gradeListLabel = new JLabel("Grades :");
        gradeListLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        Integer[] grades = {0, 1, 2};
        gradeListComboBox = new JComboBox<>(grades);
        addCourseButton = new JButton("Add");
        showCourseLabel = new JLabel("Course:");
        showCourseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        //showCourseValue = new JLabel();
        //showGradeLabel = new JLabel("Grade:");
        //showGradeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        //showGradeValue = new JLabel();

        //------------------------------table panel---------------------------------
        JPanel tablePanel = new JPanel(new GridLayout(5, 2, 5, 5));
        String[] columnNames = {"Course", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        courseTable = new JTable(tableModel);
        //add(new JScrollPane(courseTable), BorderLayout.CENTER); 
        //------------------------------button panel---------------------------------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        saveInformationButton = new JButton("Save");
        returnHomeButton = new JButton("Return");
        clearButton = new JButton("Clear");
        //------------------------------adding panels---------------------------------
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameTextField);

        formPanel.add(lastNameLabel);
        formPanel.add(lastNameTextField);

        formPanel.add(personNumberLabel);
        formPanel.add(personNumberTextField);

        formPanel.add(dateOfBirthLabel);
        formPanel.add(dateOfBirthTextField);

        formPanel.add(mobileNumberLabel);
        formPanel.add(mobileNumberTextField);

        formPanel.add(emailLabel);
        formPanel.add(emailTextField);

        formPanel.add(salaryLabel);
        formPanel.add(salaryTextField);

        formPanel.add(courseListLabel);
        formPanel.add(courseListComboBox);

        mainPanel.add(formPanel);

        // comboPanel.add(gradeListLabel);
        //comboPanel.add(gradeListComboBox);
        //comboPanel.add(addCourseButton);
        //comboPanel.add(showCourseLabel);
        //comboPanel.add(showCourseValue);
        //comboPanel.add(showGradeLabel);
        //comboPanel.add(showGradeValue);
        //  mainPanel.add(comboPanel);
        //tablePanel.add(courseTable);
        //mainPanel.add(tablePanel);
        buttonPanel.add(saveInformationButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(returnHomeButton);
        mainPanel.add(buttonPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        studentRadioButton.requestFocus();
    }

    public JPanel getPanel() {
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getStudentOrTeacherJLabel() {
        return studentOrTeacherJLabel;
    }

    public JRadioButton getStudentRadioButton() {

        return studentRadioButton;
    }

    public JRadioButton getTeacherRadioButton() {
        return teacherRadioButton;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public JLabel getFirstNameLabel() {
        return firstNameLabel;
    }

    public JTextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public JTextField getLastNameTextField() {
        return lastNameTextField;
    }

    public JLabel getPersonNumberLabel() {
        return personNumberLabel;
    }

    public JTextField getPersonNumberTextField() {
        return personNumberTextField;
    }

    public JLabel getDateOfBirthLabel() {
        return dateOfBirthLabel;
    }

    public JTextField getDateOfBirthTextField() {
        return dateOfBirthTextField;
    }

    public JLabel getMobileNumberLabel() {
        return mobileNumberLabel;
    }

    public JTextField getMobileNumberTextField() {
        return mobileNumberTextField;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JLabel getSalaryLabel() {
        return salaryLabel;
    }

    public JTextField getSalaryTextField() {
        return salaryTextField;
    }

    public JPanel getComboPanel() {
        return comboPanel;
    }

    public JButton getsaveInformationButton() {
        return saveInformationButton;
    }

    public JButton getReturnHomeButton() {
        return returnHomeButton;
    }

    public JLabel getCourseListLabel() {
        return courseListLabel;
    }

    public JComboBox<String> getCourseListComboBox() {
        return courseListComboBox;
    }

    public JComboBox<Integer> getGradeListComboBox() {
        return gradeListComboBox;
    }

    public JLabel getShowCourseValue() {
        return showCourseValue;
    }

    public JLabel getShowGradeValue() {
        return showGradeValue;
    }

    public JButton getAddCourseButton() {
        return addCourseButton;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getClearButton() {
        return clearButton;
    }

}
