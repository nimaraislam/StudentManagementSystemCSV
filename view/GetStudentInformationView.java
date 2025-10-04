package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.CsvFiles;
import model.Student;

public class GetStudentInformationView {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel comboPanel;
    private JLabel studenNameLabel;
    private JComboBox<Student> studentNameListComboBox;
    private JPanel showInfoPanel;
    private TextArea showInfoTextArea;
    private JButton returnHomeButton;
    private JButton clearButton;

    public GetStudentInformationView() {
        CsvFiles csv = new CsvFiles();
        frame = new JFrame("Get Information");
        frame.setSize(1200, 1000);

        ImageIcon icon = new ImageIcon("info.png");
        frame.setIconImage(icon.getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Dimension panelDimension = new Dimension(500, 500);//(400, 300);
        mainPanel.setPreferredSize(panelDimension);

        comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        studenNameLabel = new JLabel("Select Student :");
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

        showInfoPanel = new JPanel();
        showInfoPanel.setLayout(new GridLayout(0, 1, 5, 5));
        showInfoTextArea = new TextArea();
        showInfoTextArea.setFont(new Font("Arial", Font.BOLD, 16));
        showInfoTextArea.setForeground(Color.WHITE);
        showInfoTextArea.setBackground(Color.BLACK);
        showInfoTextArea.setEditable(false);
        showInfoPanel.add(showInfoTextArea);
        mainPanel.add(showInfoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        returnHomeButton = new JButton("Return");
        clearButton = new JButton("Clear");
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

    public JComboBox<Student> getStudentNameListComboBox() {
        return studentNameListComboBox;
    }

    public JPanel getShowInfoPanel() {
        return showInfoPanel;
    }

    public TextArea getShowInfoTextArea() {
        return showInfoTextArea;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getReturnHomeButton() {
        return returnHomeButton;
    }

}
