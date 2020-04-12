/*
 * Copyright (c) 2020 Google Inc.
 */

package com.sakadream.test.model;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.common.collect.Lists;
import com.sakadream.test.Auth;
import com.sakadream.test.bean.YoutubeEntity;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.google.api.client.util.Data.isNull;

/**
 * This sample creates and manages caption tracks by:
 *
 * 1. Get all information for a video on youtube
 *
 * @author NGUYEN Bao Quoc
 */
public class Video {

    /**
     * Define a global instance of a YouTube object, which will be used to make
     * YouTube Data API requests.
     */
    private static YouTube youtube;

    /**
     * Define a global variable that specifies the MIME type of the caption
     * being uploaded.
     */
    private static final String CAPTION_FILE_FORMAT = "*/*";

    /**
     * Define a global variable that specifies the caption download format.
     */
    private static final String SRT = "srt";

    /**
     * Get all information for a video on youtube
     *
     */
    public static void main(String[] args) throws Exception {
//
////        // This OAuth 2.0 access scope allows for full read/write access to the
////        // authenticated user's account and requires requests to use an SSL connection.
////        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");
////
////        try {
////            // Authorize the request.
////            Credential credential = Auth.authorize(scopes, "Video");
////
////            // This object is used to make YouTube Data API requests.
////            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
////                    .setApplicationName("youtube-video-sample").build();
////
////
////            System.out.println("\n================== video Information ==================\n");
////
////            YouTube.Videos.List request = youtube.videos()
////                    .list("snippet,contentDetails,statistics");
////
////            VideoListResponse response = request.setKey("UC6un658Fh6xKIi7DwJmO7iA")
////                    .setId("Us8Pa6WdHeE")
////                    .execute();
////
////            System.out.println("Number of view for this video :"+response.getItems().get(0).getStatistics().getViewCount());
////            System.out.println("Number of like for this video :"+response.getItems().get(0).getStatistics().getLikeCount());
////
////
////        } catch (GoogleJsonResponseException e) {
////            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode()
////                    + " : " + e.getDetails().getMessage());
////            e.printStackTrace();
////
////        } catch (IOException e) {
////            System.err.println("IOException: " + e.getMessage());
////            e.printStackTrace();
////        } catch (Throwable t) {
////            System.err.println("Throwable: " + t.getMessage());
////            t.printStackTrace();
////        }
//
        List<YoutubeEntity> listYoutube = getYoutubeInfor("xxxx");

    }



    public static List<YoutubeEntity> getYoutubeInfor(String id) throws Exception {

        // initiation YoutubeEntity

        List<YoutubeEntity> listYoutubeEntity = new ArrayList<>();

        YoutubeEntity youtubeEntity = new YoutubeEntity("initationID", BigInteger.ZERO,BigInteger.ZERO,BigInteger.ZERO);

        System.out.println("\n================== debug ==================\n");

        listYoutubeEntity.add(youtubeEntity);

        youtubeEntity = new YoutubeEntity("initationID", BigInteger.ZERO,BigInteger.ZERO,BigInteger.ZERO);
        // This OAuth 2.0 access scope allows for full read/write access to the
        // authenticated user's account and requires requests to use an SSL connection.
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        try {
            // Authorize the request.
            Credential credential = Auth.authorize(scopes, "Video");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                    .setApplicationName("AIzaSyCW1borbhDIIXAtsuw7mYFVix_3dUVq06c").build();


            System.out.println("\n================== video Information ==================\n");

            YouTube.Videos.List request = youtube.videos()
                    .list("snippet,contentDetails,statistics");

            VideoListResponse response = request.setKey("UC6un658Fh6xKIi7DwJmO7iA")
                    .setId("Us8Pa6WdHeE")
                    .execute();

            System.out.println("Video ID:"+response.getItems().get(0).getId());
            System.out.println("Number of view for this video :"+response.getItems().get(0).getStatistics().getViewCount());
            System.out.println("Number of like for this video :"+response.getItems().get(0).getStatistics().getLikeCount());

            if (!(isNull(response.getItems().get(0).getId()))){
                youtubeEntity.setId(response.getItems().get(0).getId());
            }

            if (!(isNull(response.getItems().get(0).getStatistics().getViewCount()))){
                youtubeEntity.setViewCount(response.getItems().get(0).getStatistics().getViewCount());
            }

            if (!(isNull(response.getItems().get(0).getStatistics().getLikeCount()))){
                youtubeEntity.setLikeCount(response.getItems().get(0).getStatistics().getLikeCount());
            }

            if (!(isNull(response.getItems().get(0).getStatistics().getDislikeCount()))){
                youtubeEntity.setDislikeCount(response.getItems().get(0).getStatistics().getDislikeCount());
            }
            listYoutubeEntity.add(youtubeEntity);
            return listYoutubeEntity;

        } catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode()
                    + " : " + e.getDetails().getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
        return listYoutubeEntity;
    }


}
