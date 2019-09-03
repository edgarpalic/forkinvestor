package com.example.forkinvestor;

public class UserScore {

    private String userName;
    private String userScore;

    public UserScore(String userName, String userScore) {
        this.userName = userName;
        this.userScore = userScore;
    }

    //getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore){
        this.userScore = userScore;
    }
}
