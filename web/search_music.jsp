<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Music Library</title>
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
            Title: <input type="text" name="title"><br><br>
            Artist: <input type="text" name="artist"><br><br>
            Genre:
            <select name="genre">
                <option value="">--Select Genre--</option>
                <%
                    // Fetch genres from the database
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    try {
                        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");
                        String query = "SELECT DISTINCT genre FROM Music ORDER BY genre";
                        stmt = conn.prepareStatement(query);
                        rs = stmt.executeQuery();
                        while (rs.next()) {
                            String genre = rs.getString("genre");
                %>
                <option value="<%= genre %>"><%= genre %></option>
                <%
                        }
                    } catch (SQLException e) {
                        out.println("<p style='color:red;'>Error loading genres: " + e.getMessage() + "</p>");
                    } finally {
                        if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
                        if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
                        if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
                    }
                %>
            </select><br><br>
            Year Range: <input type="text" name="startYear" placeholder="Start Year"> 
                        - <input type="text" name="endYear" placeholder="End Year"><br><br>
            <input type="submit" value="Search Music">
        </form>
    </body>
</html>
