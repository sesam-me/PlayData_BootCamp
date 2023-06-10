package controller;

import domain.dto.MovieDto;
import domain.dto.WatchedMovies;
import repository.ReviewRepository;
import service.MovieService;

import java.util.List;
import java.util.Scanner;

public class MovieController {
    private static MovieController controller;

    public static MovieController getController() {
        if(controller == null) controller = new MovieController();
        return controller;
    }

    public void watchMovie() {
        Scanner sc = new Scanner(System.in);

        // 현재 상영중인 영화 리스트 조회 후 시청
        System.out.println("현재 상영중인 영화 입니다. 시청 할 영화의 번호를 선택해주세요.");


        for (MovieDto movieList : MovieService.getService().shownMovies()) {
            System.out.print(movieList.getMovie_seq() + ". ");
            System.out.print(movieList.getTitle());
            System.out.println();
        }

        System.out.print("입력 : ");
        int movieSelectNum = Integer.parseInt(sc.nextLine());

        if (MovieService.getService().watchMovie(movieSelectNum) == 1) {
            System.out.println("영화 시청이 완료 되었습니다.");
            return ;
        }

        System.out.println("영화 시청에 문제가 생겼습니다.");
    }
}
