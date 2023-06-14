package domain.dto;

import java.util.Date;

public class RatedMovieDto {
    private String title;
    private Date releaseDate;
    private int duration;
    private String description;
    private int rating;
    private String genre;
    private String director;
    private int reviewRating;
    public RatedMovieDto(String title, Date releaseDate, int duration, String description, int rating, String genre, String director, int reviewRating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
        this.director = director;
        this.reviewRating = reviewRating;
    }

    @Override
    public String toString() {
        System.out.println("--------------------------------------------------\n");
        System.out.println("| 제목      | 개봉일      | 상영시간 | 설명           | 나이제한 | 장르    | 감독    | 리뷰평점 |\n");
        System.out.println("--------------------------------------------------\n");

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("| %-10s | %-10s | %-8s | %-15s | %-8s | %-6s | %-6s | %-8s |\n",
                title, releaseDate, duration, description, rating, genre, director, reviewRating));
        sb.append("--------------------------------------------------\n");
        return sb.toString();

    }

    public RatedMovieDto(){};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }
}
