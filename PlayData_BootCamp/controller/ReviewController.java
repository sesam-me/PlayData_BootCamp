package controller;

import service.ReviewService;

public class ReviewController {
    private static ReviewController controller;

    public static ReviewController getController() {
        if(controller == null) controller = new ReviewController();
        return controller;
    }
    public void insertReview() {
        System.out.println(ReviewService.getService().myWatchedMovies());
    }

    public void myReviewList() {
        ReviewService.getService().myReviewList();
    }

    public void movieReview() {
        ReviewService.getService().movieReview();
    }

    public void deleteReview() {
        System.out.println(ReviewService.getService().findAllReviewList());
    }

    public void showRatedMovies() {ReviewService.getService().sortOrder();}

}
