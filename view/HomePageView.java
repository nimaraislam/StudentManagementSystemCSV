package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomePageView {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton addStudentOrTeacherButton;
    private JButton exitButton;
    private JButton addStudentCourseandGradeButton;
    private JButton getStudentInformationButton;
    private JButton getTeacherInformationButton;

    public HomePageView() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);
        ImageIcon icon = new ImageIcon("home.png");//("../Course_Data.csv");

        frame.setIconImage(icon.getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Dimension panelDimension = new Dimension(500, 500);//(400, 300);
        mainPanel.setPreferredSize(panelDimension);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));

        Font font = new Font("Verdana", Font.BOLD, 24);

        JLabel selectOptionLabel = new JLabel("Click on the options below");
        selectOptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectOptionLabel.setFont(font);

        addStudentOrTeacherButton = new JButton("Add Student or Teacher's Information");
        addStudentCourseandGradeButton = new JButton("Add Student's Courses and Grades");
        getStudentInformationButton = new JButton("Get Student Information");
        getTeacherInformationButton = new JButton("Get Teacher Information");

        exitButton = new JButton("Exit");

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.add(selectOptionLabel);
        buttonPanel.add(addStudentOrTeacherButton);
        buttonPanel.add(addStudentCourseandGradeButton);
        buttonPanel.add(getStudentInformationButton);
        buttonPanel.add(getTeacherInformationButton);
        buttonPanel.add(exitButton);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getAddStudentOrTeacherButton() {
        return addStudentOrTeacherButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getAddStudentCourseandGradeButton() {
        return addStudentCourseandGradeButton;
    }

    public JButton getStudentInformationButton() {
        return getStudentInformationButton;
    }

    public JButton getGetTeacherInformationButton() {
        return getTeacherInformationButton;
    }
}
