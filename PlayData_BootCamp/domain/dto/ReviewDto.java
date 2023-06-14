package domain.dto;

import java.sql.Date;


public class ReviewDto {


    private int rating ;

    private Date date;

    private String contents;

    private int user_seq;

    private int movie_seq;

    public ReviewDto(int rating, Date date, String contents, int user_seq, int movie_seq) {
        this.rating = rating;
        this.date = date;
        this.contents = contents;
        this.user_seq = user_seq;
        this.movie_seq = movie_seq;
    }

    public ReviewDto() {}

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getUser_seq() {
        return user_seq;
    }

    public void setUser_seq(int user_seq) {
        this.user_seq = user_seq;
    }

    public int getMovie_seq() {
        return movie_seq;
    }

    public void setMovie_seq(int movie_seq) {
        this.movie_seq = movie_seq;
    }
}
