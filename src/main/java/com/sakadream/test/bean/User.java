package com.sakadream.test.bean;

public class User {
    private String userId;
    private String passWord;
    private String userName;
    private int coin;

    public User(String userId, String passWord, String userName, int coin) {
        this.userId = userId;
        this.passWord = passWord;
        this.userName = userName;
        this.coin = coin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}