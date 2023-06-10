package controller;

import domain.dto.UserDto;
import service.UserService;
import view.MovieView;

public class UserController {
    public static String loginUserId;
    public static int loginUserSeq;
    // 객체 싱글톤
    private static UserController controller;

    public static UserController getController() {
        if(controller == null) controller = new UserController();
        return controller;
    }

    public void mainMenu() {
        MovieView.getView().mainMenu();
    }

    public void login(String userId, String userPwd) {

        UserDto user = new UserService().login(userId, userPwd);

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

        // 로그인 정보 전역에 저장..
        loginUserId = user.getUserId();
        loginUserSeq = user.getUser_seq();

        // 일반고객 모드..
        MovieView.getView().customerMenu();
    }

    public void signUp(UserDto dto) {

        int result = UserService.getService().signUp(dto);

        if (result == 0) {
            System.out.println("회원가입에 성공 하셨습니다.");
        }

    }

}
