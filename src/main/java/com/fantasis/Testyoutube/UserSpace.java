/*
 * Copyright (c) 2020 Google Inc.
 */

package com.fantasis.Testyoutube;

import com.fantasis.Auth;
import com.fantasis.bean.User;
import com.fantasis.bean.VideoYouTube;
import com.fantasis.bean.YoutubeEntity;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class UserSpace {

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

    Connection conn;
    Statement stmt;

    public UserSpace() {
    }

    private void connect() throws Exception {


        Class.forName("org.postgresql.Driver");
        //try {
        URI dbUri = new URI("postgres://rumyltvuvhqebi:6c99249bc431d975975d446d025af21c90aa43d52400683667af35020f18cea6@ec2-52-87-58-157.compute-1.amazonaws.com:5432/d55rq9a6uq81fb");
        //String username = dbUri.getUserInfo().split(":")[0];
        //String password = dbUri.getUserInfo().split(":")[1];
        //String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        String username = "rumyltvuvhqebi";
        String password = "6c99249bc431d975975d446d025af21c90aa43d52400683667af35020f18cea6";
        String dbUrl = "jdbc:postgresql://ec2-52-87-58-157.compute-1.amazonaws.com:5432/d55rq9a6uq81fb?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

        conn = DriverManager.getConnection(dbUrl, username, password);

//                //} catch(Exception e) {
//                    URI dbUri = new URI(System.getenv("DATABASE_URL"));
//                    String username = dbUri.getUserInfo().split(":")[0];
//                    String password = dbUri.getUserInfo().split(":")[1];
//                    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//
//                    conn = DriverManager.getConnection(dbUrl, username, password);
//                }
    }
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
        UserSpace videoObject = new UserSpace();
        //videoObject.addVideoYouTube(new VideoYouTube("rcxpVWR5JYA",1,2,12,1,true));
        List<VideoYouTube> listYoutube = videoObject.showAllVideoYouTubeByUserId("quoc123");
        System.out.println(listYoutube.size());

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

    public VideoYouTube getYoutubeVideo(String id) throws Exception {
        connect();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"VideoYoutubes\" WHERE \"ID\" = " + id);
        while(rs.next()) {
            cleanConnection();
            return new VideoYouTube(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9));
        }
        throw new RuntimeException();
    }

    public  List<VideoYouTube> showAllVideoYouTubeByUserId(String UserId) throws Exception {


        List<VideoYouTube> list = new ArrayList<>();
        connect();

        stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"VideoYouTubes\" WHERE \"UserID\" = "+"'"+UserId+"'");
        while (rs.next()) {
            VideoYouTube video = new VideoYouTube(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9));
            list.add(video);
        }

        cleanConnection();
        return list;
    }


    public User getUser(String UserId) throws Exception {
        connect();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\" WHERE \"UserID\" = " + "'"+UserId+"'");
        while(rs.next()) {

            User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            cleanConnection();
            return user;
        }
        throw new RuntimeException();
    }


    public void addVideoYouTube(VideoYouTube video) throws Exception {
        connect();
        stmt = conn.createStatement();

        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");
        BigInteger viewCount = null;
        BigInteger likeCount = null;
        BigInteger dislikeCount = null;
        try {
            // Authorize the request.
            Credential credential = Auth.authorize(scopes, "Video");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                    .setApplicationName("AIzaSyCW1borbhDIIXAtsuw7mYFVix_3dUVq06c").build();

            YouTube.Videos.List request = youtube.videos()
                    .list("snippet,contentDetails,statistics");

            VideoListResponse response = request.setKey("UC6un658Fh6xKIi7DwJmO7iA")
                    .setId(video.videoId)
                    .execute();

            viewCount = response.getItems().get(0).getStatistics().getViewCount();
            likeCount = response.getItems().get(0).getStatistics().getLikeCount();
            dislikeCount = response.getItems().get(0).getStatistics().getDislikeCount();
        } catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode()
                    + " : " + e.getDetails().getMessage());
            e.printStackTrace();
        }

        stmt.executeUpdate("INSERT INTO public.\"VideoYouTubes\" (\"VideoID\", \"UserID\", \"CoinValue\", \"DailyClick\", \"TotalClick\", \"ViewCount\", \"LikeCount\", \"DisLikeCount\", \"Active\") "
                        + "VALUES "
                        + "('" + video.videoId + "', '" + video.getUserId() + "', '" + video.getCoinValue() + "', '" + video.getDailyClick() + "', '" + video.getTotalClick()
                        + "', '" + viewCount + "', '" + likeCount +"', '"+ dislikeCount+"', '"+video.active + "')");

        cleanConnection();
    }

    public void deleteVideoYouTube(int id) throws Exception {
        connect();
        stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM public.\"VideoYouTubes\" WHERE \"ID\" = " + id);
        cleanConnection();
    }

    public void cleanConnection() throws Exception {
        conn.close();
        stmt.close();
    }


}
