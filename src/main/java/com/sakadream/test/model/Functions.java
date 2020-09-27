package com.sakadream.test.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpSession;

import com.sakadream.test.bean.Employee;

        public class Functions {
            Connection conn;
            Statement stmt;

            private void connect() throws Exception {

                    String dbUrl = new String();
                    String username = new String();
                    String password = new String();

                //Class.forName("org.postgresql.Driver");

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

            public List<Employee> showAllEmployees() throws Exception {
                List<Employee> list = new ArrayList<>();
                connect();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"EMPLOYEES\" ORDER BY \"ID\" ASC");
                while (rs.next()) {
                    Employee e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                            rs.getInt(6));
                    list.add(e);
                }
                return list;
            }

            public Employee getEmployee(int id) throws Exception {
                connect();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"EMPLOYEES\" WHERE \"ID\" = " + id);
                while(rs.next()) {
                    return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                }
                throw new RuntimeException();
            }

            public void add(Employee e) throws Exception {
                connect();
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO public.\"EMPLOYEES\" (\"FULLNAME\", \"ADDRESS\", \"EMAIL\", \"PHONE\", \"SALARY\") "
                        + "VALUES "
                        + "('" + e.getFullName() + "', '" + e.getAddress() + "', '" + e.getEmail() + "', '" + e.getPhone() + "', '" + e.getSalary() + "')");
                cleanConnection();
            }

            public void edit(int id, Employee e) throws Exception {
                connect();
                stmt = conn.createStatement();
                stmt.executeUpdate("UPDATE \"EMPLOYEES\" "
                        + "SET \"FULLNAME\" = '" + e.getFullName() + "', \"ADDRESS\" = '" + e.getAddress() + "', \"EMAIL\" = '" + e.getEmail() + "', "
                        + "\"PHONE\" = '" + e.getPhone() + "', \"SALARY\" = " + e.getSalary() + " "
                        + "WHERE \"ID\" = " + id);
                cleanConnection();
            }

            public void delete(int id) throws Exception {
                connect();
                stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM public.\"EMPLOYEES\" WHERE \"ID\" = " + id);
                cleanConnection();
            }

            public void cleanConnection() throws Exception {
                conn.close();
                stmt.close();
            }

            public void echoQuery(String query) {
                System.out.println(query);
            }

            public Boolean checkSession(HttpSession session) {
                return (session.getAttribute("usernameId") != null) ? true : false;
            }
        }