package service;

import controller.UserController;
import domain.dto.*;
import repository.MovieRepository;
import repository.ReviewRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
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

        UserDto userDto = UserRepository.getRepository().findByUserId(UserService.loginUserId);

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

    public int deleteReview(int review_seq){
        return ReviewRepository.getRepository().deleteReviewBySeq(review_seq);
    }

    public String myWatchedMovies() {
        Scanner sc = new Scanner(System.in);

        String result = "";

        // 먼저 내가 본 영화를 조회 한다.
        List<WatchedMovies> watchedList = ReviewRepository.getRepository().myWatchedMovies();

        System.out.println("리뷰할 영화의 번호를 선택 해주세요.");

        // 내가 시청한 영화 리스트 받아오기.. 없다면 끝내자..
        if (watchedList.size() == 0) {
            result = "시청하신 영화가 없어요.";
            return result;
        }

        // 내가 본 영화 보여주기..
        for (WatchedMovies watchedMovies : watchedList) {
            System.out.print(watchedMovies.getMovie_seq() + ". ");
            System.out.print(watchedMovies.getTitle());
            System.out.println();
        }

        System.out.print("입력 : ");
        int movieSelectNum = Integer.parseInt(sc.nextLine());

        // 선택한 영화 시퀀스 들고 리뷰작성 으로..
        if (writeReview(movieSelectNum) == 0) {
            result = "리뷰 등록 에러";
            return result;
        }

        result = "리뷰 등록이 완료 되었습니다.";
        return result;

    }

    public void myReviewList() {

        List<MyReviewDto> myReviewList = ReviewRepository.getRepository().myReviewList();

        if (myReviewList.size() == 0) {
            System.out.println("작성하신 리뷰가 없습니다");
            return ;
        }

        for (MyReviewDto review : myReviewList) {
            System.out.println("*******************");
            System.out.println(review.getTitle() + " 에 대해 " + review.getReviewDate() + " 에 작성하신 리뷰 입니다.");
            System.out.println("평점 : " + review.getRating());
            System.out.println("내용 : " + review.getContents());
            System.out.println("*******************");
        }
    }

    public void movieReview() {
        Scanner sc = new Scanner(System.in);

        // 현재 상영중인 영화 리스트 조회 후 시청
        System.out.println("현재 상영중인 영화 입니다. 어떤 영화의 리뷰를 보시겠어요 ?");

        // 상영중인 영화 리스트
        for (MovieDto movieList : MovieRepository.getRepository().shownMovies()) {
            System.out.print(movieList.getMovie_seq() + ". ");
            System.out.print(movieList.getTitle());
            System.out.println();
        }

        System.out.print("입력 : ");
        int movieSelectNum = Integer.parseInt(sc.nextLine());

        // 해당 영화의 리뷰 리스트
        for (MyReviewDto reviewList : ReviewRepository.getRepository().movieReview(movieSelectNum)) {

            if (reviewList.getContents() == null) {
                System.out.println("선택하신 영화의 리뷰는 아직 등록되지 않았습니다.");
                return ;
            }

            System.out.println("*******************");
            System.out.println(reviewList.getTitle() + " 에 대한 리뷰 입니다.");
            System.out.println("작성 된 날짜 : " + reviewList.getReviewDate());
            System.out.println("평점 : " + reviewList.getRating());
            System.out.println("내용 : " + reviewList.getContents());
            System.out.println("*******************");
        }

    }

    public String findAllReviewList() {
        Scanner sc = new Scanner(System.in);

        String result = "";

        List<ReviewListDto> reviewList = ReviewRepository.getRepository().findAllReviewList();

        // 해당 영화의 리뷰 리스트
        System.out.println("삭제할 리뷰의 번호를 입력 하세요.");

        for (ReviewListDto review : reviewList) {

            System.out.print("리뷰 번호 - " + review.getReview_seq() + ". ");
            System.out.println(review.getReviewDate() + " - " + review.getUserId() + " 님이 " + review.getTitle() + " 에 대해 남긴 리뷰 ");
            System.out.println(review.getRating() + "점 -> 내용 : " + review.getContents());
            System.out.println();
        }

        int selectedReviewNum = Integer.parseInt(sc.nextLine());

        if (ReviewService.getService().deleteReview(selectedReviewNum) == 0) {
            result = "리뷰 삭제 에러!";
            return result;
        }

        result = "선택하신 리뷰가 삭제 되었습니다.";
        return result;


    }
}
