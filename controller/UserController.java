package controller;

import domain.dto.UserDto;
import service.UserService;
import view.CommonView;

public class UserController {

    // 객체 싱글톤
    private static UserController controller;

    public static UserController getController() {
        if(controller == null) controller = new UserController();
        return controller;
    }

    public void mainMenu() {
        CommonView.getView().mainMenu();
    }

    public void login(String userId, String userPwd) {
        UserService.getService().login(userId, userPwd);
    }

    public void signUp(UserDto dto) {
        System.out.println(UserService.getService().signUp(dto));
    }

}
