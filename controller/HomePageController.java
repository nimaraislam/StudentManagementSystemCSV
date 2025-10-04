package controller;

import view.CourseGradeView;
import view.GetStudentInformationView;
import view.GetTeacherInformationView;
import view.HomePageView;
import view.PersonView;

public class HomePageController {

    private HomePageView homePageView;

    public HomePageController(HomePageView homePageView) {
        this.homePageView = homePageView;
    }

    public void initController() {
        homePageView.getAddStudentOrTeacherButton().addActionListener(e -> addStudentOrTeacher());
        homePageView.getAddStudentCourseandGradeButton().addActionListener(e -> addStudentGradesForCourses());
        homePageView.getStudentInformationButton().addActionListener(e -> getStudentInformation());
        homePageView.getGetTeacherInformationButton().addActionListener(e -> getTeacherInformation());
        homePageView.getExitButton().addActionListener(e -> exit());
    }

    private void addStudentOrTeacher() {
        try {
            PersonView personView = new PersonView("Add Student or Teacher");
            PersonController personController = new PersonController(personView);
            personController.initController();
            personView.getFrame().setVisible(true);
        } catch (Exception e) {
        }
    }

    private void addStudentGradesForCourses() {
        try {
            CourseGradeView gradesForCoursesView = new CourseGradeView("Add grades for courses");
            CourseGradeController gradesForCoursesController = new CourseGradeController(gradesForCoursesView);
            gradesForCoursesController.initController();
            gradesForCoursesView.getFrame().setVisible(true);
        } catch (Exception e) {
        }
    }

    private void getStudentInformation() {
        try {
            GetStudentInformationView getInformationView = new GetStudentInformationView();
            GetStudentInformationController getInformationController = new GetStudentInformationController(getInformationView);
            getInformationController.initController();
            getInformationView.getFrame().setVisible(true);
        } catch (Exception e) {
        }
    }

    private void getTeacherInformation() {
        try {
            GetTeacherInformationView getInformationView = new GetTeacherInformationView();
            GetTeacherInformationController getInformationController = new GetTeacherInformationController(getInformationView);
            getInformationController.initController();
            getInformationView.getFrame().setVisible(true);
        } catch (Exception e) {
        }
    }

    private void exit() {
        System.exit(0);
    }

}
