package controller;

import view.HomePageView;

public class Program {

    public Program() {
    }

    public void run() {
        HomePageView homeView = new HomePageView();
        new HomePageController(homeView).initController();
    }
}
