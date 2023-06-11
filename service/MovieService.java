package service;

import controller.MovieController;
import domain.dto.MovieDto;
import repository.MovieRepository;

import java.awt.*;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void insertMovieInfo() {
        Scanner sc = new Scanner(System.in);

        MovieDto movieDto = new MovieDto();

        // 정보를 전부 다 받자..
        System.out.println("영화의 제목을 입력 해주세요.");
        movieDto.setTitle(sc.nextLine());

        System.out.println("영화의 개봉일을 입력 해주세요. (yyyy-MM-dd)");
        LocalDate date = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        movieDto.setReleaseDate(date);

        System.out.println("영화의 상영시간을 숫자로 입력 해주세요.");
        movieDto.setDuration(Integer.parseInt(sc.nextLine()));

        System.out.println("영화의 설명을 입력 해주세요.");
        movieDto.setDescription(sc.nextLine());

        System.out.println("영화의 상영 등급을 입력 해주세요. (나이제한)");
        movieDto.setRating(sc.nextLine());

        System.out.println("영화의 장르를 입력 해주세요.");
        movieDto.setGenre(sc.nextLine());

        System.out.println("영화의 감독을 입력 해주세요.");
        movieDto.setDirector(sc.nextLine());

        System.out.println("영화의 링크를 입력 해주세요.");
        movieDto.setLink(sc.nextLine());

        if (MovieRepository.getRepository().insertMovieInfo(movieDto) == 1) {
            System.out.println("영화 추가 에러 !");
            return ;
        }

        System.out.println("영화 추가 완료 !");
    }
}
