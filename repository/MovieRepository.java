package repository;

import config.JdbcConnection;
import domain.dto.ActorDto;
import domain.dto.MovieDto;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

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

        try {
            conn.close();
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
            psmt.setInt(2, UserService.loginUserSeq);

            if(psmt.executeUpdate() == 1){
                result = 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int insertMovieInfo(MovieDto movieDto) {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "insert into Movie (title, release_date, duration, description, rating, genre, director, link) \n" +
                "values (? ,?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, movieDto.getTitle());
            psmt.setObject(2, movieDto.getReleaseDate());
            psmt.setInt(3, movieDto.getDuration());
            psmt.setString(4, movieDto.getDescription());
            psmt.setString(5, movieDto.getRating());
            psmt.setString(6, movieDto.getGenre());
            psmt.setString(7, movieDto.getDirector());
            psmt.setString(8, movieDto.getLink());

            if (psmt.executeUpdate() == 0) {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;

    }

    public int insertMovieActor(ActorDto actorDto) {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "INSERT INTO Actor (name, birth_date, nation, gender)\n" +
                "values (?, ?, ?, ?);";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, actorDto.getName());
            psmt.setObject(2, actorDto.getBirthDate());
            psmt.setString(3, actorDto.getNation());
            psmt.setString(4, actorDto.getGender());

            if (psmt.executeUpdate() == 0) {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;

    }

    public int movieInsertActor(int movieSeq, int actorSeq) {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into movie_actor (movie_seq, actor_seq) values(?, ?);";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, movieSeq);
            psmt.setInt(2, actorSeq);

            if (psmt.executeUpdate() == 0) {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }

    public List<MovieDto> movieList() {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select movie_seq,title from Movie";

        List<MovieDto> movieDtoList = new ArrayList<>();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {

                MovieDto movieDto = new MovieDto();
                movieDto.setMovie_seq(resultSet.getInt("movie_seq"));
                movieDto.setTitle(resultSet.getString("title"));

                movieDtoList.add(movieDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movieDtoList;

    }

    public List<ActorDto> actorList() {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select actor_seq,name from Actor;";
        List<ActorDto> actorDtoList = new ArrayList<>();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {

                ActorDto actorDto = new ActorDto();
                actorDto.setActor_seq(resultSet.getInt("actor_seq"));
                actorDto.setName(resultSet.getString("name"));

                actorDtoList.add(actorDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return actorDtoList;
    }

    public Map<Integer, MovieDto> genreMap() {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "SELECT genre, movie_seq from movie";
        Map<Integer, MovieDto> movieGenreMap = new HashMap<>();

        Set<String> uniqueGenres = new HashSet<>();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {

                String genre = resultSet.getString("genre");

                if (!uniqueGenres.contains(genre)) { // 중복된 장르인지 체크
                    MovieDto genreMap = new MovieDto();

                    genreMap.setMovie_seq(resultSet.getInt("movie_seq"));
                    genreMap.setGenre(genre);

                    movieGenreMap.put(genreMap.getMovie_seq(), genreMap);
                    uniqueGenres.add(genre); // 중복된 장르로 체크
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movieGenreMap;
    }

    public List<MovieDto> movieByGenre(String genre) {

        Connection conn = new JdbcConnection().getJdbc();

        String sql = "SELECT * FROM movie WHERE genre = ?";
        List<MovieDto> movieByGenreList = new ArrayList<>();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, genre);

            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                MovieDto movieByGenre = new MovieDto();
                movieByGenre.setMovie_seq(resultSet.getInt("movie_seq"));
                movieByGenre.setTitle(resultSet.getString("title"));
                movieByGenre.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
                movieByGenre.setDuration(resultSet.getInt("duration"));
                movieByGenre.setDescription(resultSet.getString("description"));
                movieByGenre.setRating(resultSet.getString("rating"));
                movieByGenre.setGenre(resultSet.getString("genre"));
                movieByGenre.setDirector(resultSet.getString("director"));
                movieByGenre.setLink(resultSet.getString("link"));
                movieByGenreList.add(movieByGenre);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movieByGenreList;
    }

    public List<ActorDto> movieSelectActor(int movieSeqByDelete) {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "SELECT a.actor_seq, a.name\n" +
                "FROM movie AS m\n" +
                "INNER JOIN movie_actor AS ma ON m.movie_seq = ma.movie_seq\n" +
                "INNER JOIN actor AS a ON ma.actor_seq = a.actor_seq\n" +
                "WHERE m.movie_seq = ?;";

        List<ActorDto> actorDtoList = new ArrayList<>();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, movieSeqByDelete);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                int actorSeq = resultSet.getInt("actor_seq");
                String actorName = resultSet.getString("name");

                ActorDto actorDto = new ActorDto();
                actorDto.setActor_seq(actorSeq);
                actorDto.setName(actorName);

                actorDtoList.add(actorDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actorDtoList;
    }

    public List<ActorDto> movieDeleteActor(int movieSeqByDelete, int actorSeqByDelete) {
        Connection conn = new JdbcConnection().getJdbc();
        List<ActorDto> actorDtoList = new ArrayList<>();

        String sql = "delete from movie_actor where movie_seq = ? and actor_seq = ? ;";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, movieSeqByDelete);
            psmt.setInt(2, actorSeqByDelete);

            if (psmt.executeUpdate() == 0) {
                System.out.println("출연진 삭제 에러..!!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return actorDtoList;
    }

    public List<MovieDto> getMovieByDirector(String director) {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "select * from movie where director = ?";

        List<MovieDto> movieDirectorList = new ArrayList<>();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql); // sql을 MySQL과 연결준비
            psmt.setString(1, director); // sql의 ?를 채우면서 sql완성

            ResultSet resultSet = psmt.executeQuery(); // 쿼리를 실행한 걸 resultSet에 담아!

            while (resultSet.next()){ // 하나씩 next하면서 resultSet의 행이 있으면 ture, 없을 때까지 돌림(false)
                MovieDto movieDto = new MovieDto();
                movieDto.setMovie_seq(resultSet.getInt("movie_seq"));
                movieDto.setTitle(resultSet.getString("title")); // resultSet의 title을 setTitle하겠다.
                java.sql.Date sqlDate = resultSet.getDate("release_date");
                LocalDate releaseDate = sqlDate.toLocalDate();
                movieDto.setReleaseDate(releaseDate);

                movieDto.setDuration(resultSet.getInt("duration"));
                movieDto.setDescription(resultSet.getString("description"));
                movieDto.setRating(resultSet.getString("rating"));
                movieDto.setGenre(resultSet.getString("genre"));
                movieDto.setDirector(resultSet.getString("director"));
                movieDto.setLink(resultSet.getString("link"));

                movieDirectorList.add(movieDto);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movieDirectorList;
    }

    public List<MovieDto> movieByActor(int actor) {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select m.movie_seq, m.title, m.release_date, m.duration, m.description, m.rating, m.genre, m.director" +
                ", m.link " +
                "from movie_actor as ma join movie as m on ma.movie_seq = m.movie_seq " +
                "where m.movie_seq = ?;";

        List<MovieDto> movieDtoList = new ArrayList<>();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, actor);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {

                MovieDto movieDto = new MovieDto();
                movieDto.setMovie_seq(resultSet.getInt("movie_seq"));
                movieDto.setTitle(resultSet.getString("title"));
                movieDto.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
                movieDto.setDuration(resultSet.getInt("duration"));
                movieDto.setDescription(resultSet.getString("description"));
                movieDto.setRating(resultSet.getString("rating"));
                movieDto.setGenre(resultSet.getString("genre"));
                movieDto.setDirector(resultSet.getString("director"));
                movieDto.setLink(resultSet.getString("link"));
                movieDtoList.add(movieDto);
            }

        } catch (SQLException e) {
            throw   new RuntimeException(e);
        }
        return movieDtoList;
    }

}

