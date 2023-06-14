package domain.dto;

public class WatchedMovies {
    private int movie_seq;

    private String title;

    private int user_seq;

    private String userId;

    public WatchedMovies() {}
    public WatchedMovies(int movie_seq, String title, int user_seq, String userId) {
        this.movie_seq = movie_seq;
        this.title = title;
        this.user_seq = user_seq;
        this.userId = userId;
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

    public int getUser_seq() {
        return user_seq;
    }

    public void setUser_seq(int user_seq) {
        this.user_seq = user_seq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
