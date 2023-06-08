package view;

import controller.MovieController;

import java.util.Scanner;

public class MovieView {
    private static MovieView view;
    public static MovieView getView() {
        if (view == null) {
            view = new MovieView();
        }
        return view;
    }

    public void mainMenu() {
        // mainMenu..
        Scanner sc = new Scanner(System.in);
        MovieController controller = MovieController.getController();

        do {
            System.out.println("========== O Movie System 0.1v ==========");
            System.out.println("========== 메뉴를 선택 해주세요 ==========");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("0. 종료");
            System.out.print("입력 : ");

            int menu = Integer.parseInt(sc.nextLine());

            switch(menu) {
                case 1 :
                    // 로그인
                    loginMenu();
                    break;

                case 2 :
                    // 회원가입
                    signUpMode();
                    break;

                case 0 :
                    // 종료
                    System.out.println("O Movie 시스템을 종료 합니다.");
                    return;

                default :
                    System.out.println("잘못된 메뉴를 선택 하셨습니다. 다시 선택 해 주세요.");
                    break;
            }
        } while(true);
    }

    public void loginMenu() {
        // adminMenu..
        Scanner sc = new Scanner(System.in);
        MovieController controller = MovieController.getController();

        System.out.println("========== (LOGIN MODE)  ==========");

        System.out.println("아이디를 입력 해주세요.");
        String userId = sc.nextLine();

        System.out.println("비밀번호를 입력 해주세요.");
        String userPwd = sc.nextLine();

        controller.login(userId, userPwd);
    }

    public void signUpMode() {
        Scanner sc = new Scanner(System.in);
        MovieController controller = MovieController.getController();

        System.out.println("========== (SignUp MODE)  ==========");

        System.out.println("아이디를 입력 해주세요.");
        String userId = sc.nextLine();

        System.out.println("비밀번호를 입력 해주세요.");
        String userPwd = sc.nextLine();

        controller.signUp(userId, userPwd);
    }
}
