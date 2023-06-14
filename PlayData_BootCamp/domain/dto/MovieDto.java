package domain.dto;

import java.time.LocalDate;
import java.util.Date;

public class MovieDto {

    @Override
    public String toString() {
        return
                "No. " + movie_seq +
                "  | 영화제목" + title + '\'' +
                "  | 상영일 : " + releaseDate +
                "  | 상영시간 : " + duration +
                "  | 설명 : " + description + '\'' +
                "  | 나이제한 : " + rating + '\'' +
                        "  | 감독 : " + director + '\'' +
                "  | 장르 : " + genre + '\'' +
                "  | 링크 : '" + link + '\'' +
                '}';
    }

    private int movie_seq;
    private String title;
    private LocalDate releaseDate;
    private int duration;
    private String description;
    private String rating;
    private String genre;
    private String director;
    private String link;
    public MovieDto() {};

    public MovieDto(int movie_seq, String title, LocalDate releaseDate, int duration, String description, String rating, String genre, String director, String link) {
        this.movie_seq = movie_seq;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
        this.director = director;
        this.link = link;
    }

    public int getMovie_seq() {
        return movie_seq;
    }

    public void setMovie_seq(int movie_seq) {
        this.movie_seq = movie_seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
