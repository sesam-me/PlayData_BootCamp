package domain.dto;

public class UserDto {
    private String userId;
    private String userEmail;
    private String userPwd;

    public UserDto() {};

    public UserDto(String userId, String userEmail, String userPwd) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return userId + " 님 안녕하세요 ? ";
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
}
