package service;

import controller.MovieController;
import domain.dto.ActorDto;
import domain.dto.MovieDto;
import repository.MovieRepository;

import java.awt.*;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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

    public void insertActorInfo() {
        Scanner sc = new Scanner(System.in);
        ActorDto actorDto = new ActorDto();

        System.out.println("========== 배우 추가 모드 입니다. ==========");

        System.out.println("배우의 이름을 입력 해주세요.");
        actorDto.setName(sc.nextLine());

        System.out.println("배우의 생년월일을 입력 해주세요. (yyyy-MM-dd)");
        LocalDate date = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        actorDto.setBirthDate(date);

        System.out.println("배우의 국적을 입력해주세요.");
        actorDto.setNation(sc.nextLine());

        System.out.println("배우의 성별을 입력 해주세요.");
        actorDto.setGender(sc.nextLine());

        if (MovieRepository.getRepository().insertMovieActor(actorDto) == 1) {
            System.out.println("배우 추가 에러 !");
        }

        System.out.println("배우 추가가 완료 되었습니다.");

    }

    public void movieInsertActor() {

        Scanner sc = new Scanner(System.in);

        List<MovieDto> movieDtoList = MovieRepository.getRepository().movieList();


        System.out.println("배우를 추가하실 영화를 선택 해주세요.");

        for (MovieDto movie : movieDtoList) {
            System.out.print(movie.getMovie_seq() + ". ");
            System.out.print(movie.getTitle());
            System.out.println();
        }

        int movieSeq = Integer.parseInt(sc.nextLine());

        List<ActorDto> actorDtoList = MovieRepository.getRepository().actorList();

        System.out.println("영화에 출연시킬 배우를 선택 해주세요.");

        for (ActorDto actor : actorDtoList) {
            System.out.print(actor.getActor_seq() + ". ");
            System.out.print(actor.getName());
            System.out.println();
        }

        int actorSeq = Integer.parseInt(sc.nextLine());


        if (MovieRepository.getRepository().movieInsertActor(movieSeq, actorSeq) == 0) {
            System.out.println("리뷰 등록 에러 !");
            return ;
        }

        System.out.println("등록이 완료되었습니다");
    }

    public void movieByGenre() {
        Scanner sc =new Scanner(System.in);

        // 장르로 검색할 영화명을 입력하기
        Map<Integer, MovieDto> genreMap = MovieRepository.getRepository().genreMap();

        for (Map.Entry<Integer, MovieDto> entry : genreMap.entrySet()) {
            System.out.print("[" + entry.getValue().getGenre() + "]");
        }

        System.out.println("");
        System.out.println("검색을 원하는 장르를 입력시 영화리스트가 나옵니다.");


        String genre = sc.nextLine() ;


        List<MovieDto> movieList = MovieRepository.getRepository().movieByGenre(genre);

        if(movieList.size() == 0) {
            System.out.println("해당하는 장르의 영화는 없습니다.");
            return;
        }

        for(MovieDto movieDto : movieList) {
            System.out.println(movieDto.toString());
        }
    }

    public void movieSelectActor() {
        Scanner sc = new Scanner(System.in);

        //영화 보여주기
        List<MovieDto> movieSelectList = MovieRepository.getRepository().shownMovies();

        for (MovieDto movie : movieSelectList) {
            System.out.print(movie.getMovie_seq() + ". ");
            System.out.print(movie.getTitle());
            System.out.println();
        }

        //출연진 보여주기
        System.out.println("영화의 번호를 입력해주세요.");
        int movieSeqByDelete = Integer.parseInt(sc.nextLine());
        List<ActorDto> actorDtoList = MovieRepository.getRepository().movieSelectActor(movieSeqByDelete);

        if (actorDtoList.size() == 0) {
            System.out.println("아직 등록된 출연진이 없어요");
            return ;
        }

        for (ActorDto acotr : actorDtoList) {
            System.out.print(acotr.getActor_seq() + ". ");
            System.out.print(acotr.getName());
            System.out.println();
        }

        //출연진 삭제하기
        System.out.println("삭제할 배우의 번호 입력해주세요.");

        int actorSeqByDelete = Integer.parseInt(sc.nextLine());

        MovieRepository.getRepository().movieDeleteActor(movieSeqByDelete, actorSeqByDelete);

        System.out.println("삭제가 완료되었습니다");
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

    public void movieByActor() {
        Scanner sc = new Scanner(System.in);

        List<ActorDto> actorDtoList = MovieRepository.getRepository().actorList();
        System.out.println("특정 배우가 출연한 영화를 확인하려면 배우의 번호를 입력해주세요.");

        for (ActorDto actor : actorDtoList) {
            System.out.print(actor.getActor_seq() + ". ");
            System.out.print(actor.getName());
            System.out.println();
        }

        int actor =Integer.parseInt(sc.nextLine());

        List<MovieDto> movieByActorList = MovieRepository.getRepository().movieByActor(actor);

        if(movieByActorList.size() == 0) {
            System.out.println("해당하는 배우가 출연한 영화는 없습니다");
            return;
        }

        for(MovieDto movieDto : movieByActorList) {
            System.out.println(movieDto.toString());
        }
    }
}
