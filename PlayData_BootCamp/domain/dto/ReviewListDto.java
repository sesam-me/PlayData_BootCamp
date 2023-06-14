package domain.dto;

import java.util.Date;

public class ReviewListDto {
    private String userId;
    private Date reviewDate;
    private String contents;
    private int rating;
    private String title;

    private int review_seq;

    public ReviewListDto() {}

    public ReviewListDto(String userId, Date reviewDate, String contents, int rating, String title, int review_seq) {
        this.userId = userId;
        this.reviewDate = reviewDate;
        this.contents = contents;
        this.rating = rating;
        this.title = title;
        this.review_seq = review_seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReview_seq() {
        return review_seq;
    }

    public void setReview_seq(int review_seq) {
        this.review_seq = review_seq;
    }
}
