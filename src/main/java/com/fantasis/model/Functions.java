package com.fantasis.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.servlet.http.HttpSession;


        public class Functions {
            Connection conn;
            Statement stmt;


            // connect to database postgresql
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

            // Check if login is valide
            public Boolean checkLogin(String usernameId, String password, HttpSession session) throws Exception {
                connect();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Users\""
                        + " WHERE \"UserID\" LIKE '" + usernameId + "' AND \"PASSWORD\" LIKE '" + password + "'");
                while(rs.next()) {
                    session.setAttribute("usernameId", usernameId);
                    return true;
                }
                return false;
            }

            // Check if user is still in their session
            public Boolean checkSession(HttpSession session) {
                return (session.getAttribute("usernameId") != null) ? true : false;
            }
        }