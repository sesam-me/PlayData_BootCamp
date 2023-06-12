package repository;

import config.JdbcConnection;
import controller.UserController;
import domain.dto.*;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private static ReviewRepository repository;

    public static ReviewRepository getRepository() {
        if(repository == null) repository = new ReviewRepository();
        return repository;
    }

    public int insertReview(ReviewDto dto){

        Connection conn = new JdbcConnection().getJdbc();

        int result = 0;

        String sql = "insert into review (rating, review_date, contents, user_seq, movie_seq) values(?, ?, ?, ?, ?)";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,dto.getRating());
            psmt.setDate(2,dto.getDate());
            psmt.setString(3,dto.getContents());
            psmt.setInt(4,dto.getUser_seq());
            psmt.setInt(5,dto.getMovie_seq());

            int rowsAffected = psmt.executeUpdate();

            if (rowsAffected > 0) {
                result = 1; // 요청이 성공한 경우 result 값을 1.
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return result;
    }

    public int deleteReviewBySeq(int review_seq){
        Connection conn = new JdbcConnection().getJdbc();

        int result = 0;

        String sql ="delete from review where review_seq=?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,review_seq);
            psmt.executeUpdate();

            if(psmt.executeUpdate() == 0){
                result = 1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<WatchedMovies> myWatchedMovies() {
        Connection conn = new JdbcConnection().getJdbc();

        List<WatchedMovies> watchedMovieList = new ArrayList<>();

        String sql = "SELECT m.movie_seq, m.title, u.user_seq, u.user_id FROM movie m JOIN watched_movie wm ON m.movie_seq = wm.movie_seq JOIN user u ON wm.user_seq = u.user_seq where u.user_id = ?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, UserService.loginUserId);

            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                WatchedMovies watchedMovies = new WatchedMovies(); // 새로운 객체 생성

                watchedMovies.setMovie_seq(resultSet.getInt("movie_seq"));
                watchedMovies.setUser_seq(resultSet.getInt("user_seq"));
                watchedMovies.setTitle(resultSet.getString("title"));
                watchedMovies.setUserId(resultSet.getString("user_id"));

                watchedMovieList.add(watchedMovies);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return watchedMovieList;
    }

    public List<MyReviewDto> myReviewList() {
        Connection conn = new JdbcConnection().getJdbc();

        List<MyReviewDto> myReviewList = new ArrayList<>();

        String sql = "select m.title, r.rating, r.contents, r.review_date  from movie m join review r on m.movie_seq = r.movie_seq left join user u on r.user_seq = u.user_seq where u.user_id = ?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, UserService.loginUserId);

            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                // 새로운 객체 생성
                MyReviewDto myReviewDto = new MyReviewDto();

                myReviewDto.setTitle(resultSet.getString("title"));
                myReviewDto.setRating(resultSet.getInt("rating"));
                myReviewDto.setContents(resultSet.getString("contents"));
                myReviewDto.setReviewDate(resultSet.getDate("review_date"));

                myReviewList.add(myReviewDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return myReviewList;

    }

    public List<MyReviewDto> movieReview(int moive_seq) {
        Connection conn = new JdbcConnection().getJdbc();

        List<MyReviewDto> reviewList = new ArrayList<>();

        String sql = "select m.title, r.rating, r.contents, r.review_date from movie m left join review r on m.movie_seq = r.movie_seq where m.movie_seq = ?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, moive_seq);

            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                // 새로운 객체 생성
                MyReviewDto myReviewDto = new MyReviewDto();

                myReviewDto.setTitle(resultSet.getString("title"));
                myReviewDto.setRating(resultSet.getInt("rating"));
                myReviewDto.setContents(resultSet.getString("contents"));
                myReviewDto.setReviewDate(resultSet.getDate("review_date"));

                reviewList.add(myReviewDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviewList;

    }

    public List<ReviewListDto> findAllReviewList() {
        Connection conn = new JdbcConnection().getJdbc();

        List<ReviewListDto> reviewList = new ArrayList<>();

        String sql = "select u.user_id, r.review_date, r.contents,r.rating, m.title, r.review_seq  from user u join review r on u.user_seq = r.user_seq join movie m on r.movie_seq = m.movie_seq;";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                // 새로운 객체 생성
                ReviewListDto reviewListDto = new ReviewListDto();

                reviewListDto.setUserId(resultSet.getString("user_id"));
                reviewListDto.setReviewDate(resultSet.getDate("review_date"));
                reviewListDto.setContents(resultSet.getString("contents"));
                reviewListDto.setRating(resultSet.getInt("rating"));
                reviewListDto.setTitle(resultSet.getString("title"));
                reviewListDto.setReview_seq(resultSet.getInt("review_seq"));

                reviewList.add(reviewListDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviewList;
    }

    public List<RatedMovieDto> showRatedMovies(String sortOrder) {
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select m.title, m.release_date, m.duration, m.description, m.rating, m.genre, m.director, r.rating as reviewRating from movie m join review r on m.movie_seq = r.movie_seq order by r.rating " + sortOrder + " limit 5";

        List<RatedMovieDto> reviewList =  new ArrayList<>();



        try{
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()){
                RatedMovieDto reviewdto = new RatedMovieDto();
                reviewdto.setTitle(resultSet.getString("title"));
                reviewdto.setReleaseDate(resultSet.getDate("release_date"));
                reviewdto.setDuration(resultSet.getInt("duration"));
                reviewdto.setDescription(resultSet.getString("description"));
                reviewdto.setRating(resultSet.getInt("rating"));
                reviewdto.setGenre(resultSet.getString("genre"));
                reviewdto.setDirector(resultSet.getString("director"));
                reviewdto.setReviewRating(resultSet.getInt("reviewRating"));
                reviewList.add(reviewdto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviewList;
    }

}
