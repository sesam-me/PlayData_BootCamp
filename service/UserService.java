package service;

import domain.dto.UserDto;
import repository.UserRepository;
import view.CommonView;

import java.util.List;
import java.util.Scanner;

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

    public UserDto findByUserId() {
        Scanner sc = new Scanner(System.in);

        System.out.println("회원 아이디를 입력하세요");

        String user_id = sc.nextLine();

        return UserRepository.getRepository().findByUserId(user_id);
    }

    public void findByUserList() {
        List<UserDto> userList = UserRepository.getRepository().findByUserList();

        for (UserDto user : userList) {
            System.out.println(user);
        }

    }

    public void deleteUser() {
        Scanner sc = new Scanner(System.in);

        System.out.println("사용자 ID 리스트입니다. 삭제할 사용자 넘버를 입력하세요.");

        List<UserDto> userList = UserRepository.getRepository().userList();
        for (UserDto user : userList) {
            System.out.print(user.getUser_seq() + ". ");
            System.out.print(user.getUserId());
            System.out.println();
        }

        System.out.println("사용자 ID 입력 : ");
        int userseq = Integer.parseInt(sc.nextLine());

        int resultNumber = UserRepository.getRepository().deleteUser(userseq);

        if (resultNumber == 1){
            System.out.println("회원 삭제가 완료되었습니다.");
        } else {
            System.out.println("회원 삭제 에러");
        }
    }


}
