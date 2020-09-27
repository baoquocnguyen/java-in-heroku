package com.fantasis.bean;

import java.math.BigInteger;

public class YoutubeEntity {
    public String id;
    public BigInteger likeCount;
    public BigInteger dislikeCount;
    public BigInteger viewCount;


    public YoutubeEntity(String id, BigInteger likeCount, BigInteger dislikeCount, BigInteger viewCount) {
        this.id = id;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.viewCount = viewCount;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }

    public BigInteger getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(BigInteger likeCount) {
        this.likeCount = likeCount;
    }

    public BigInteger getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(BigInteger dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public BigInteger getViewCount() {
        return viewCount;
    }

    public void setViewCount(BigInteger viewCount) {
        this.viewCount = viewCount;
    }

}