package controller;

import view.MovieView;

public class MovieController {
    // 객체 싱글톤
    private static MovieController controller;
    public static MovieController getController() {
        if(controller == null) controller = new MovieController();
        return controller;
    }


    public void mainMenu() {
        MovieView.getView().mainMenu();
    }

    public void login(String userId, String userPwd) {
        System.out.println("로그인 기능");
    }

    public void signUp(String userId, String userPwd) {
        System.out.println("회원가입 기능");
    }
}
