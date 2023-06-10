package controller;

import domain.dto.MyReviewDto;
import domain.dto.ReviewDto;
import domain.dto.ReviewListDto;
import repository.ReviewRepository;
import service.ReviewService;

import java.util.List;
import java.util.Scanner;

public class ReviewController {
    private static ReviewController controller;

    public static ReviewController getController() {
        if(controller == null) controller = new ReviewController();
        return controller;
    }
    public void insertReview() {
        // 먼저 내가 본 영화를 조회 한다.
        ReviewService.getService().myWatchedMovies();
    }

    public void myReviewList() {
        ReviewService.getService().myReviewList();
    }

    public void movieReview() {
        ReviewService.getService().movieReview();
    }

    public void deleteReview() {
        Scanner sc = new Scanner(System.in);
        List<ReviewListDto> reviewList = ReviewService.getService().findAllReviewList();

        // 해당 영화의 리뷰 리스트
        System.out.println("삭제할 리뷰의 번호를 입력 하세요.");
        for (ReviewListDto review : reviewList) {

            System.out.print("리뷰 번호 - " + review.getReview_seq() + ". ");
            System.out.println(review.getReviewDate() + " - " + review.getUserId() + " 님이 " + review.getTitle() + " 에 대해 남긴 리뷰 ");
            System.out.println(review.getRating() + "점 -> 내용 : " + review.getContents());
            System.out.println();
        }

        int selectedReviewNum = Integer.parseInt(sc.nextLine());

        if (ReviewService.getService().deleteReview(selectedReviewNum) == 1) {
            System.out.println("선택하신 리뷰가 삭제 되었습니다.");
            return ;
        }

        System.out.println("리뷰 삭제 에러!");

    }


}
