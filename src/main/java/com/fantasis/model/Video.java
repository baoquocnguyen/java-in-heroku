/*
 * Copyright (c) 2020 Google Inc.
 */

package com.fantasis.model;

import com.fantasis.bean.VideoYouTube;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.common.collect.Lists;
import com.fantasis.Auth;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * T
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

    Connection conn;
    Statement stmt;

    public Video() {
    }

    private void connect() throws Exception {

        String dbUrl = new String();
        String username = new String();
        String password = new String();

        // read file config
        try (InputStream input = Functions.class.getClassLoader().getResourceAsStream("youtube.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            dbUrl = prop.getProperty("com.fantasis.url");
            username = prop.getProperty("com.fantasis.username");
            password = prop.getProperty("com.fantasis.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        conn = DriverManager.getConnection(dbUrl, username, password);

    }

    // Show all videos in table VideoYoutube by VideoID
    public  List<VideoYouTube> showAllVideoYouTube() throws Exception {

        List<VideoYouTube> list = new ArrayList<>();
        connect();

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"VideoYouTubes\" ORDER BY \"VideoID\" ASC");
        while (rs.next()) {
            VideoYouTube video = new VideoYouTube(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9));
            list.add(video);
        }

        cleanConnection();
        return list;
    }

    // Show all videos of others users in order to earn money by doing like, view exchange
    public  List<VideoYouTube> viewOthersVideoYouTube(String userId) throws Exception {


        List<VideoYouTube> list = new ArrayList<>();
        connect();

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"VideoYouTubes\" WHERE \"UserID\" != "+"'"+userId+"'" + "ORDER BY \"VideoID\" ASC");

        while (rs.next()) {
            VideoYouTube video = new VideoYouTube(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9));
            list.add(video);
        }

        cleanConnection();
        return list;
    }

    // Show all my videos
    public  List<VideoYouTube> myAllVideoYouTube(String userId) throws Exception {


        List<VideoYouTube> list = new ArrayList<>();
        connect();

        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"VideoYouTubes\" WHERE \"UserID\" = "+"'"+userId+"'" + "ORDER BY \"VideoID\" ASC");

        while (rs.next()) {
            VideoYouTube video = new VideoYouTube(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getBoolean(9));
            list.add(video);
        }

        cleanConnection();
        return list;
    }

    // Add my video to increase like view traffic
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

        stmt.executeUpdate("INSERT INTO public.\"VideoYouTubes\" (\"VideoID\", \"UserID\", \"CoinValue\", \"DailyClick\", \"TotalClick\", \"ViewCount\", \"LikeCount\", \"DislikeCount\", \"Active\") "
                        + "VALUES "
                        + "('" + video.videoId + "', '" + video.getUserId() + "', '" + video.getCoinValue() + "', '" + video.getDailyClick() + "', '" + video.getTotalClick()
                        + "', '" + viewCount + "', '" + likeCount +"', '"+ dislikeCount+"', '"+video.active + "')");

        cleanConnection();
    }


    // delete one video in my list
    public void deleteVideoYouTube(int id) throws Exception {
        connect();
        stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM public.\"VideoYouTubes\" WHERE \"ID\" = " + id);
        cleanConnection();
    }

    // clean data connection
    public void cleanConnection() throws Exception {
        conn.close();
        stmt.close();
    }


}
