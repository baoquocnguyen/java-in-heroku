<%@ page import = "java.io.*,java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <title>E-Social Network</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial;
            padding: 10px;
            background: #f1f1f1;
        }

        /* Header/Blog Title */
        .header {
            padding: 30px;
            text-align: center;
            background: white;
        }

        .header h1 {
            font-size: 50px;
        }

        /* Style the top navigation bar */
        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        /* Style the topnav links */
        .topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        /* Change color on hover */
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Create two unequal columns that floats next to each other */
        /* Left column */
        .leftcolumn {
            float: left;
            width: 25%;
        }

        /* Right column */
        .rightcolumn {
            float: left;
            width: 75%;
            background-color: #f1f1f1;
            padding-left: 20px;
        }

        /* Create container */
        /* center column */
        .container {
            float: center;
            margin: auto;
            width: 80%;
        }

        /* Fake image */
        .fakeimg {
            background-color: #aaa;
            width: 100%;
            padding: 20px;
        }

        /* Add a card effect for articles */
        .card {
            background-color: white;
            padding: 20px;
            margin-top: 20px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* Footer */
        .footer {
            padding: 20px;
            text-align: center;
            background: #ddd;
            margin-top: 20px;
        }

        /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 800px) {
            .leftcolumn, .rightcolumn {
                width: 100%;
                padding: 0;
            }
        }

        /* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
        @media screen and (max-width: 400px) {
            .topnav a {
                float: none;
                width: 100%;
            }
        }
    </style>
</head>

<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">E-Social Network</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a class="nav-link" href="#">Charge Money</a></li>
            <li><a class="nav-link" href="#">Charge VIP</a></li>

            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Others<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Page 1-1</a></li>
                    <li><a href="#">Page 1-2</a></li>
                    <li><a href="#">Page 1-3</a></li>
                </ul>
            </li>
        </ul>

        <form class="navbar-form navbar-left" action="/action_page.php">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row">

        <div class="leftcolumn">
            <div class="card">
                <h2>About Me</h2>
                <table class="table table-hover">


                </table>

                <center>
                    <%
                        // Set refresh, autoload time as 5 seconds
                        response.setIntHeader("Refresh", 5);
                    %>
                </center>
            </div>

            <div class="list-group">
                <h3>My Social</h3>
                <a href="/addvideoyoutube.htm" style="margin-bottom: 20px;" class="fa fa-plus btn btn-success">Add My Link</a>
                <a href="/videoyoutube.htm" style="margin-bottom: 20px;" class="fa fa-plus btn btn-success">My Link</a>
            </div>


            <div class="list-group">
                <h3>Earn Money</h3>
                <button type="button" class="list-group-item list-group-item-action active">
                    YouTube
                </button>
                <a href="/videoyoutube-view.htm" type="button" class="list-group-item list-group-item-action">YouTube Subcribe</a>
                <a href="/videoyoutube-view.htm" type="button" class="list-group-item list-group-item-action">YouTube View</a>
                <a href="/videoyoutube-view.htm" type="button" class="list-group-item list-group-item-action">YouTube Like</a>
            </div>

            <div class="card">
                <h3>Follow Me</h3>
                <p>Some text..</p>
            </div>
        </div>

        <div class="rightcolumn">
            <div class="card">

                <table class="table table-hover">
                    <thead>
                    <button type="button" class="list-group-item list-group-item-action active">
                        My Link YouTube
                    </button>
                    <tr>

                        <th>VIDEO ID</th>
                        <th>USER ID</th>
                        <th>COIN VALUE</th>
                        <th>DAILY CLICK</th>
                        <th>TOTAL CLICK</th>
                        <th>View Count</th>

                        <th>Like Count</th>
                        <th>Dislike Count</th>
                        <th>ACTIVE</th>
                        <th>Link YouTube</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${listVideoYouTube}" var="e">
                        <tr>

                            <td>${e.videoId}</td>
                            <td>${e.userId}</td>
                            <td>${e.coinValue}</td>
                            <td>${e.dailyClick}</td>
                            <td>${e.totalClick}</td>
                            <td>${e.viewCount}</td>

                            <td>${e.likeCount}</td>
                            <td>${e.dislikeCount}</td>
                            <td>${e.active}</td>
                            <td>
                                <a href="https://www.youtube.com/watch?v=${e.videoId}" target="popup"
                                   onclick="window.open('https://www.youtube.com/watch?v=${e.videoId}','name','width=600,height=400' )">Link Video</a>
                            </td>
                        </tr>
                    </c:forEach>


                    </tbody>
                </table>
            </div>

        </div>

    </div>

    <div class="footer">
        <h2>Footer</h2>
    </div>
</div>

</html>