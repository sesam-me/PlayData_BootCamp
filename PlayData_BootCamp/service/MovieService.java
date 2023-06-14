package service;

import controller.MovieController;
import domain.dto.MovieDto;
import repository.MovieRepository;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

public class MovieService {
    private static MovieService service;

    public static MovieService getService() {
        if(service == null) service = new MovieService();
        return service;
    }

    public String shownMovies() {
        Scanner sc = new Scanner(System.in);

        String result = "";

        // 현재 상영중인 영화 리스트 조회 후 시청
        System.out.println("현재 상영중인 영화 입니다. 시청 할 영화의 번호를 선택해주세요.");

        List<MovieDto> movieList = MovieRepository.getRepository().shownMovies();

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


        if (MovieService.getService().watchMovie(movieSelectNum) == 0) {
            result = "영화 시청에 문제가 생겼습니다.";
            return result;
        }

        // 내가 본 영화로 저장이 정상적으로 완료가 되었다면 ?
        try {
            // 영화 링크 열어주기
            URI uri = new URI(link);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = "영화 시청이 완료 되었습니다.";
        return result;


    }

    public int watchMovie(int movie_seq) {
        return MovieRepository.getRepository().watchMovie(movie_seq);
    }


    public void searchDirector(){
        Scanner sc = new Scanner(System.in);

        System.out.println(MovieRepository.getRepository().getAllDirector());

        System.out.println("감독이름을 입력하세요.");
        String directorName = sc.nextLine();

        List<MovieDto> movieByDirector = MovieRepository.getRepository().getMovieByDirector(directorName);

        for (MovieDto movieDto : movieByDirector) {
            System.out.println(movieDto);
        }
    }

}
