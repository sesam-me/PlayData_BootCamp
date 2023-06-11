package domain.dto;

public class UserDto {

    private int user_seq;
    private String userId;
    private String userEmail;
    private String userPwd;

    public UserDto() {};

    public UserDto(int user_seq, String userId, String userEmail, String userPwd) {
        this.user_seq = user_seq;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "회원번호 : " + user_seq + " 번, " +  " ID : " + userId + ", Email : " + userEmail ;
    }
}
