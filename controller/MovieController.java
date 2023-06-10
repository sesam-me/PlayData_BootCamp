package controller;

import domain.dto.MovieDto;
import domain.dto.WatchedMovies;
import repository.ReviewRepository;
import service.MovieService;

import java.awt.*;
import java.net.URI;
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

        // 현재 상영중인 영화 리스트 (개봉시간이 현재시간 이전 인 것들)
        List<MovieDto> movieList = MovieService.getService().shownMovies();

        // 메뉴로 보여주기
        for (int i = 1; i <= movieList.size(); i++) {
            System.out.print(i + ". ");
            System.out.print(movieList.get((i-1)).getTitle());
            System.out.println();
        }

        // 인덱스 입력받아서 원하는 데이터 저장..
        System.out.print("입력 : ");
        int selectNum = Integer.parseInt(sc.nextLine()) - 1;
        int movieSelectNum = movieList.get(selectNum).getMovie_seq();
        String link = movieList.get(selectNum).getLink();

        // 내가 본 영화로 저장이 정상적으로 완료가 되었다면 ?
        if (MovieService.getService().watchMovie(movieSelectNum) == 1) {

            try {
                // 영화 링크 열어주기
                URI uri = new URI(link);
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("영화 시청이 완료 되었습니다.");
            return ;
        }

        System.out.println("영화 시청에 문제가 생겼습니다.");
    }
}
