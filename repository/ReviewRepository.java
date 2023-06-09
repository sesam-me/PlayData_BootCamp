package repository;

import config.JdbcConnection;
import domain.dto.ReviewDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewRepository {
    private static ReviewRepository repository;

    public static ReviewRepository getRepository() {
        if(repository == null) repository = new ReviewRepository();
        return repository;
    }

    public void insertReview(ReviewDto dto){

        Connection conn = new JdbcConnection().getJdbc();

        String sql ="insert into review(rating,date,contents,user_seq,movie_seq)" +
                "values (?,?,?,?,?)";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,dto.getRating());
            psmt.setDate(2,dto.getDate());
            psmt.setString(3,dto.getContents());
            psmt.setInt(4,dto.getUser_seq());
            psmt.setInt(5,dto.getMovie_seq());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteReviewBySeq(int review_seq){
        Connection conn = new JdbcConnection().getJdbc();

        String sql ="delete from review where review_seq=?";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,review_seq);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
