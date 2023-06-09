package service;

import domain.dto.ReviewDto;
import repository.ReviewRepository;

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

    public void writeReview(){

        System.out.println("리뷰 작성할 유저 번호를 입력하세요");
        int user_seq = Integer.parseInt(sc.nextLine());

        System.out.println("리뷰 작성할 영화 번호를 입력하세요");
        int movie_seq = Integer.parseInt(sc.nextLine());

        System.out.println("영화의 평점을 입력하세요");
        int rating = Integer.parseInt(sc.nextLine());

        System.out.println("영화의 리뷰 내용을 입력하세요");
        String contents = sc.nextLine();

        LocalDate now = LocalDate.now();
        java.sql.Date nowDate = java.sql.Date.valueOf(now);

        ReviewDto dto= new ReviewDto(rating,nowDate,contents,user_seq,movie_seq);

        reviewRepository.insertReview(dto);
    }

    public void deleteReview(){

        System.out.println("삭제할 리뷰의 번호를 입력하세요");
        int review_seq = Integer.parseInt(sc.nextLine());

        reviewRepository.deleteReviewBySeq(review_seq);
    }
}
