package domain.dto;

import java.sql.Date;


public class ReviewDto {
    @Override
    public String toString() {
        return "ReviewDto{" +
                "rating=" + rating +
                ", date=" + date +
                ", contents='" + contents + '\'' +
                ", user_seq=" + user_seq +
                ", movie_seq=" + movie_seq +
                '}';
    }

    public int getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }

    public String getContents() {
        return contents;
    }

    public int getUser_seq() {
        return user_seq;
    }

    public int getMovie_seq() {
        return movie_seq;
    }

    public ReviewDto(int rating, Date date, String contents, int user_seq, int movie_seq) {
        this.rating = rating;
        this.date = date;
        this.contents = contents;
        this.user_seq = user_seq;
        this.movie_seq = movie_seq;
    }

    private int rating ;

    private Date date;

    private String contents;

    private int user_seq;

    private int movie_seq;

}
