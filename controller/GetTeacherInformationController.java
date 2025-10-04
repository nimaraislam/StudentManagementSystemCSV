package controller;

import java.util.ArrayList;

import model.CourseGrade;
import model.CsvFiles;
import model.Student;
import model.Teacher;
import view.GetTeacherInformationView;
import view.HomePageView;

public class GetTeacherInformationController {
     private GetTeacherInformationView view;
    public GetTeacherInformationController(GetTeacherInformationView view){
        this.view=view;
    }
    public void initView() {
       // view.getStudentNameListComboBox().setSelectedIndex(0);
        //view.getShowInfoTextArea().setText("");
    }

    public void initController() {
       view.getTeacherNameListComboBox().addActionListener(e-> getInformation());
        view.getClearButton().addActionListener(e -> initView());
        view.getReturnHomeButton().addActionListener(e -> returnHome());
    }
     public void getInformation() {
        Teacher aTeacher = (Teacher) view.getTeacherNameListComboBox().getSelectedItem();
        String personNumber = aTeacher.getPersonNumber().trim();
        CsvFiles csv = new CsvFiles();
        ArrayList<Teacher> teachers = csv.loadTeacherFile();
        for(int i=0;i<teachers.size();i++){
             Teacher teacherFromSavedFile = teachers.get(i);
             if (teacherFromSavedFile.getPersonNumber().contains(personNumber)) {
                    view.getShowInfoTextArea().setText(aTeacher.getInformation());
             }
        }
    }
      private void returnHome() {
        HomePageView homeView = new HomePageView();
        new HomePageController(homeView).initController();
    }
}
