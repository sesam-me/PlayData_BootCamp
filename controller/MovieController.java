package controller;

import domain.dto.UserDto;
import service.MovieService;
import view.MovieView;

public class MovieController {
    // 객체 싱글톤
    private static MovieController controller;
    private static UserDto userDto;

    public static MovieController getController() {
        if(controller == null) controller = new MovieController();
        return controller;
    }

    public void mainMenu() {
        MovieView.getView().mainMenu();
    }

    public void login(String userId, String userPwd) {

        UserDto user = new MovieService().login(userId, userPwd);

        // 로그인 한 정보 저장해놓음..
        userDto = user;

        // 찾는 회원이 없다면 ..?
        if (user.getUserId() == null) {
            System.out.println("회원 정보가 올바르지 않아요.");
            return ;
        }

        // 회원 id가 admin 이라면 admin 모드로..
        if (user.getUserId().equals("admin") && user.getUserPwd().equals("1234")) {
            MovieView.getView().adminMenu();
            return ;
        }

        // 일반고객 모드..
        MovieView.getView().customerMenu();
    }

    public void signUp(String userId, String userPwd) {
        System.out.println("회원가입 기능");
    }

    public void insertMovieInfo() {
        System.out.println("========== 영화 추가 모드 입니다. ==========");
        System.out.println();
    }
}
