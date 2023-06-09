package domain.dto;

import java.time.LocalDate;
import java.util.Date;

public class MovieDto {
    private String title;
    private LocalDate releaseDate;
    private int duration;
    private String description;
    private String rating;
    private String genre;
    private String director;

    public MovieDto() {};

    public MovieDto(String title, LocalDate releaseDate, int duration, String description, String rating, String genre, String director) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
        this.director = director;
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

    @Override
    public String toString() {
        return "MovieDto{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
