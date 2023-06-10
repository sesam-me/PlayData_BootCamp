package service;

import domain.dto.UserDto;
import repository.UserRepository;
import view.CommonView;

public class UserService {
    public static String loginUserId;
    public static int loginUserSeq;
    private static UserService service;

    public static UserService getService() {
        if(service == null) service = new UserService();
        return service;
    }

    public void login(String userId, String userPwd) {

        UserDto user = UserRepository.getRepository().login(userId, userPwd);


        // 찾는 회원이 없다면 ..?
        if (user.getUserId() == null) {
            System.out.println("회원 정보가 올바르지 않아요.");
            return ;
        }

        // 회원 id가 admin 이라면 admin 모드로..
        if (user.getUserId().equals("admin") && user.getUserPwd().equals("1234")) {
            CommonView.getView().adminMenu();
            return ;
        }

        // 로그인 정보 전역에 저장..
        loginUserId = user.getUserId();
        loginUserSeq = user.getUser_seq();

        // 일반고객 모드..
        CommonView.getView().customerMenu();
    }

    public String signUp(UserDto dto) {

        int result = UserRepository.getRepository().signUp(dto);

        String resultMsg = "";

        if (result == 1) {
            resultMsg = "회원 가입 실패";
            return resultMsg;
        }

        resultMsg = "회원가입에 성공 하셨습니다.";
        return resultMsg;

    }


}
