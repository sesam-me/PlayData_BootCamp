package domain.dto;

import java.sql.Date;

public class UserDto {

    private int user_seq;
    private String userId;
    private String userEmail;

    @Override
    public String toString() {
        return "UserDto{" +
                "user_seq=" + user_seq +
                ", userId='" + userId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", bookingSeq=" + bookingSeq +
                ", userSeq=" + userSeq +
                ", seatNumber=" + seatNumber +
                ", bookingTime=" + bookingTime +
                '}';
    }

    private String userPwd;

    public int getBookingSeq() {
        return bookingSeq;
    }

    public void setBookingSeq(int bookingSeq) {
        this.bookingSeq = bookingSeq;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    private int bookingSeq;
    private int userSeq;
    private int seatNumber;
    private Date bookingTime;
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

}
