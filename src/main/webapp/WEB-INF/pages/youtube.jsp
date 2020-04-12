<%@ page import = "java.io.*,java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <title>Employees - Java Spring Example</title>
            <link href="/resources/css/bootstrap.min.css" rel="stylesheet" />
            <link href="/resources/css/font-awesome.min.css" rel="stylesheet" />
        </head>

        <body>
            <nav class="navbar navbar-inverse">
                <div class="container">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="">Java Spring Example</a>
                        <a href="/logout.htm" class="btn btn-default">Logout</a>
                    </div>
                </div>
            </nav>

            <div class="container">
                <table class="table table-hover">
                    <thead>
                        <tr><a href="/add.htm" style="margin-bottom: 20px;" class="fa fa-plus btn btn-success">Add New</a></tr>
                        <tr><a href="/youtube.htm" style="margin-bottom: 20px;" class="fa fa-plus btn btn-success">You Tube</a></tr>
                        <tr>
                            <th>ID</th>
                            <th>Like Count</th>
                            <th>Dislike Count</th>
                            <th>View Count</th>
                            <th>Link YouTube</th>
                        </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${listInfor}" var="e">
                            <tr>
                                <td>${e.id}</td>
                                <td>${e.likeCount}</td>
                                <td>${e.dislikeCount}</td>
                                <td>${e.viewCount}</td>
                                <td>
                                    <a href="https://www.youtube.com/watch?v=${e.id}" target="popup"
                                       onclick="window.open('https://www.youtube.com/watch?v=${e.id}','name','width=600,height=400' )">Link Video</a>
                                </td>
                                <td>
                                    <a href="/edit.htm?id=${e.id}" class="fa fa-edit btn btn-default">Edit</a>
                                    <a href="/delete.htm?id=${e.id}" class="fa fa-trash btn btn-danger" onclick="return confirm('Do you want to remove this employee?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>

                        <center>
                            <h2>Auto Refresh Header Example</h2>
                            <%
                                // Set refresh, autoload time as 5 seconds
                                response.setIntHeader("Refresh", 5);

                                // Get current time
                                Calendar calendar = new GregorianCalendar();
                                String am_pm;

                                int hour = calendar.get(Calendar.HOUR);
                                int minute = calendar.get(Calendar.MINUTE);
                                int second = calendar.get(Calendar.SECOND);

                                if(calendar.get(Calendar.AM_PM) == 0)
                                    am_pm = "AM";
                                else
                                    am_pm = "PM";
                                String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
                                System.out.println("Crrent Time: " + CT + "\n");
                            %>
                        </center>
                    </tbody>
                </table>
            </div>

            <div class="container">
                <footer>
                    <p>
                        &copy; <a href="">NGUYEN Quoc</a> 2017
                    </p>
                </footer>
            </div>
        </body>
        <script src="/resources/js/bootstrap.min.js"></script>
        <script src="/resources/js/jquery.min.js"></script>

        </html>