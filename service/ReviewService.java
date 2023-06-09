package service;

import controller.UserController;
import domain.dto.ReviewDto;
import domain.dto.UserDto;
import domain.dto.WatchedMovies;
import repository.ReviewRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class ReviewService {

    private static ReviewService service;
    private ReviewRepository reviewRepository;
    private Scanner sc;

    public static ReviewService getService() {
        if(service == null) service = new ReviewService();
        return service;
    }

    public int writeReview(int selectMovieNum){
        Scanner sc = new Scanner(System.in);

        UserDto userDto = UserRepository.getRepository().findByUserId(UserController.loginUserId);

        // 현재 로그인한 user_seq
        int user_seq = userDto.getUser_seq();

        // 내가 본 영화중 고른 movie_seq
        int movie_seq = selectMovieNum;


        System.out.println("영화의 평점을 입력하세요.");
        int rating = Integer.parseInt(sc.nextLine());

        System.out.println("영화의 리뷰 내용을 입력하세요");
        String contents = sc.nextLine();

        LocalDate now = LocalDate.now();
        java.sql.Date nowDate = java.sql.Date.valueOf(now);

        ReviewDto dto= new ReviewDto(rating,nowDate,contents,user_seq,movie_seq);

        return ReviewRepository.getRepository().insertReview(dto);
    }

    public void deleteReview(){

        System.out.println("삭제할 리뷰의 번호를 입력하세요");
        int review_seq = Integer.parseInt(sc.nextLine());

        reviewRepository.deleteReviewBySeq(review_seq);
    }

    public void myWatchedMovies() {
        Scanner sc = new Scanner(System.in);

        System.out.println("리뷰할 영화의 번호를 선택 해주세요.");

        for (WatchedMovies watchedMovies : ReviewRepository.getRepository().myWatchedMovies()) {
            System.out.print(watchedMovies.getMovie_seq() + ". ");
            System.out.print(watchedMovies.getTitle());
            System.out.println();
        }

        System.out.print("입력 : ");
        int movieSelectNum = Integer.parseInt(sc.nextLine());

        if (writeReview(movieSelectNum) == 1) {
            System.out.println("리뷰 등록이 완료 되었습니다.");
            return ;
        }

        System.out.println("리뷰 등록 에러");
    }
}
