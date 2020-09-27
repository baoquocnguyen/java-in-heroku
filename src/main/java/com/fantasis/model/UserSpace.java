/*
 * Copyright (c) 2020 Google Inc.
 */

package com.fantasis.model;
import com.fantasis.bean.User;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Properties;


/**
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


    public void cleanConnection() throws Exception {
        conn.close();
        stmt.close();
    }


}
