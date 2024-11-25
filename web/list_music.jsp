<%-- 
    Document   : list_music
    Created on : Nov 25, 2024, 4:06:12 PM
    Author     : Harraz
--%>

<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Music Library</title>
    </head>
    <body>
        <h1>Music Library</h1>
        <% 
            // Check if the user is logged in by verifying the session
            if (session == null || session.getAttribute("username") == null) {
                // No session, redirect to login
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <!-- Display a message if there was any (e.g., after adding new music) -->
        <% 
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <div style="color: green;"><%= message %></div>
        <% 
            } 
        %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Artist</th>
                <th>Genre</th>
                <th>Year Released</th>
                <th>Actions</th>
            </tr>
            <% 
                // Establish database connection
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");
                    String query = "SELECT * FROM Music";
                    stmt = conn.prepareStatement(query);
                    rs = stmt.executeQuery();
                    // Iterate over the result set and display each record
                    while (rs.next()) {
            %>
                <tr>
                    <td><%= rs.getInt("id") %></td>
                    <td><%= rs.getString("title") %></td>
                    <td><%= rs.getString("artist") %></td>
                    <td><%= rs.getString("genre") %></td>
                    <td><%= rs.getInt("yearReleased") %></td>
                    <td>
                        <a href="edit_music.jsp?id=<%= rs.getInt("id") %>&title=<%= rs.getString("title") %>&artist=<%= rs.getString("artist") %>&genre=<%= rs.getString("genre") %>&yearReleased=<%= rs.getInt("yearReleased") %>">Edit</a>
                    </td>
                </tr>
            <% 
                    }
                } catch (SQLException e) {
                    out.println("Error retrieving music records: " + e.getMessage());
                } finally {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                }
            %>
        </table>
        <a href="add_music.jsp">Add New Music</a>
    </body>
</html>
