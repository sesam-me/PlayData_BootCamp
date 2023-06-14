package domain.dto;

import java.util.Date;

public class MyReviewDto {
    private String title;

    private int rating;

    private String contents;

    public Date reviewDate;

    public MyReviewDto(String title, int rating, String contents, Date reviewDate) {
        this.title = title;
        this.rating = rating;
        this.contents = contents;
        this.reviewDate = reviewDate;
    }

    public MyReviewDto() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
