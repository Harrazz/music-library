<%-- 
    Document   : add_music
    Created on : Nov 25, 2024, 4:16:41 PM
    Author     : Harraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>add music</title>
    </head>
    <body>
        <form action="AddMusicServlet1" method="post">
            Title: <input type="text" name="title"><br>
            Artist: <input type="text" name="artist"><br>
            Genre: <input type="text" name="genre"><br>
            Year Released: <input type="text" name="yearReleased"><br>
            <input type="submit" value="Add Music">
        </form>
    </body>
</html>
