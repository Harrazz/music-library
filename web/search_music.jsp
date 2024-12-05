<%-- 
    Document   : search_music
    Created on : Dec 2, 2024, 2:22:40 PM
    Author     : Harraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search Music Library</h1>
        <%
            String error = request.getParameter("error");
            if (error != null) {
                out.println("<p style='color:red;'>" + error + "</p>");
            }
        %>
        
        <form action="result_music.jsp" method="post">
            Title : <input type="text" name="title"><br><br>
            Artist : <input type="text" name="artist"><br><br>
            <input type="submit" value="Search Music">
        </form>
    </body>
</html>
