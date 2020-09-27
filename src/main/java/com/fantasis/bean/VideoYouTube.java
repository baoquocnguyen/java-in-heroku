package com.fantasis.bean;

public class VideoYouTube {
    public String videoId;
    public String userId;
    public int coinValue;
    public int dailyClick;
    public int totalClick;

    public int likeCount;
    public int dislikeCount;
    public int viewCount;
    public Boolean active;


    public VideoYouTube( String videoId, String userId, int coinValue, int dailyClick, int totalClick, int viewCount, int  likeCount, int dislikeCount,
                        Boolean active) {
        this.videoId = videoId;
        this.userId = userId;
        this.coinValue = coinValue;
        this.dailyClick = dailyClick;
        this.totalClick = totalClick;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.active = active;
    }

    public VideoYouTube(String videoId, String userId, int coinValue, int dailyClick, int totalClick, Boolean active) {
        this.videoId = videoId;
        this.userId = userId;
        this.coinValue = coinValue;
        this.dailyClick = dailyClick;
        this.totalClick = totalClick;
        this.active = active;
    }


    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }

    public int getDailyClick() {
        return dailyClick;
    }

    public void setDailyClick(int dailyClick) {
        this.dailyClick = dailyClick;
    }

    public int getTotalClick() {
        return totalClick;
    }

    public void setTotalClick(int totalClick) {
        this.totalClick = totalClick;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}