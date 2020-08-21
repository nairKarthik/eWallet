package com.example.services_wallet.Model;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private int userCell;
    private boolean isKycDone;

    public User() {
    }

    public User(int userId, String userName, String userEmail, int userCell, boolean isKycDone) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userCell = userCell;
        this.isKycDone = isKycDone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserCell() {
        return userCell;
    }

    public void setUserCell(int userCell) {
        this.userCell = userCell;
    }

    public boolean isKycDone() {
        return isKycDone;
    }

    public void setKycDone(boolean kycDone) {
        isKycDone = kycDone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userCell=" + userCell +
                ", isKycDone=" + isKycDone +
                '}';
    }
}
