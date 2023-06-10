package repository;

import config.JdbcConnection;
import controller.UserController;
import domain.dto.MovieDto;
import domain.dto.UserDto;
import domain.dto.WatchedMovies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private static MovieRepository repository;
    public static MovieRepository getRepository() {
        if(repository == null) repository = new MovieRepository();
        return repository;
    }

    public List<MovieDto> shownMovies() {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "SELECT * FROM movie WHERE release_date < CURDATE()";

        List<MovieDto> shownMoviesList = new ArrayList<MovieDto>();


        try {
            PreparedStatement psmt = conn.prepareStatement(sql);

            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                // 새로운 객체 생성
                MovieDto movieDto = new MovieDto();

                movieDto.setMovie_seq(resultSet.getInt("movie_seq"));
                movieDto.setTitle(resultSet.getString("title"));

                java.sql.Date sqlDate = resultSet.getDate("release_date");
                LocalDate releaseDate = sqlDate.toLocalDate();
                movieDto.setReleaseDate(releaseDate);

                movieDto.setDuration(resultSet.getInt("duration"));
                movieDto.setDescription(resultSet.getString("description"));
                movieDto.setRating(resultSet.getString("rating"));
                movieDto.setGenre(resultSet.getString("genre"));
                movieDto.setDirector(resultSet.getString("director"));
                movieDto.setLink(resultSet.getString("link"));

                shownMoviesList.add(movieDto);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return shownMoviesList;
    }

    public int watchMovie(int movie_seq) {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "insert into watched_movie(movie_seq, user_seq) values(?, ?)";

        int result = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, movie_seq);
            psmt.setInt(2, UserController.loginUserSeq);

            if(psmt.executeUpdate() == 1){
                result = 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
