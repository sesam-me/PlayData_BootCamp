package repository;

import config.JdbcConnection;
import domain.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static UserRepository repository;
    public static UserRepository getRepository() {
        if(repository == null) repository = new UserRepository();
        return repository;
    }

    public UserDto login(String userId, String userPwd) {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "select * from user where user_id = ? and user_pwd = ?";

        UserDto user = new UserDto();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1, userId);
            psmt.setString(2, userPwd);

            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                user.setUser_seq(resultSet.getInt("user_seq"));
                user.setUserId(resultSet.getString("user_id"));
                user.setUserEmail(resultSet.getString("user_email"));
                user.setUserPwd(resultSet.getString("user_pwd"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public int signUp(UserDto dto){
        Connection conn = new JdbcConnection().getJdbc();

        int result = 0;

        String sql = "insert into User (user_id, user_email, user_pwd)\n" +
                "value (?, ?, ?);";

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getUserId());
            psmt.setString(2, dto.getUserEmail());
            psmt.setString(3, dto.getUserPwd());
            if(psmt.executeUpdate() == 0){
                result = 1;
            } else {
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public UserDto findByUserId(String userId) {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "select * from user where user_id = ?";

        UserDto user = new UserDto();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1, userId);

            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                user.setUser_seq(resultSet.getInt("user_seq"));
                user.setUserId(resultSet.getString("user_id"));
                user.setUserEmail(resultSet.getString("user_email"));
                user.setUserPwd(resultSet.getString("user_pwd"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public List<UserDto> findByUserList() {
        Connection conn = new JdbcConnection().getJdbc();

        List<UserDto> userList = new ArrayList<>();

        String sql = "select * from user";


        try {
            PreparedStatement psmt = conn.prepareStatement(sql);

            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()) {
                UserDto user = new UserDto();

                user.setUser_seq(resultSet.getInt("user_seq"));
                user.setUserId(resultSet.getString("user_id"));
                user.setUserEmail(resultSet.getString("user_email"));
                user.setUserPwd(resultSet.getString("user_pwd"));

                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public List<UserDto> userList(){
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "select * from user;";

        List<UserDto> userList = new ArrayList<>();

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet resultSet = psmt.executeQuery();

            while (resultSet.next()){
                UserDto userDto = new UserDto();
                userDto.setUser_seq(resultSet.getInt("user_seq"));
                userDto.setUserId(resultSet.getString("user_id"));

                userList.add(userDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;

    }

    //    회원 삭제
    public int deleteUser(int userSeq) {
        Connection conn = new JdbcConnection().getJdbc();

        String sql = "delete from user where user_seq = ?";

        int result = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, userSeq);

            int rowsAffected = psmt.executeUpdate();

            if (rowsAffected > 0) {
                result = 1; // 요청이 성공한 경우 result 값을 1.
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


}
