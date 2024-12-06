<%-- 
    Document   : result_music
    Created on : Dec 2, 2024, 2:42:12 PM
    Author     : Harraz
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Search Results</h2>
<%--<c:set var="title" value="${param.title}" />
<c:set var="artist" value="${param.artist}" />

<c:choose>

    <c:when test="${title eq 'Cardigan' && artist eq 'Taylor Swift'}">
        <p>Found: Cardigan by Taylor Swift (Indie Folk, 2020)</p>
    </c:when>

    <c:otherwise>
        <c:redirect url='list_music.jsp?forwarded=true' />
    </c:otherwise>

</c:choose>--%>
<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Search Results</h2>

<%
    String title = request.getParameter("title");
    String artist = request.getParameter("artist");
    String genre = request.getParameter("genre");
    String startYear = request.getParameter("startYear");
    String endYear = request.getParameter("endYear");

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");

        // Construct dynamic query based on provided filters
        StringBuilder query = new StringBuilder("SELECT * FROM Music WHERE 1=1");
        if (title != null && !title.trim().isEmpty()) query.append(" AND title LIKE ?");
        if (artist != null && !artist.trim().isEmpty()) query.append(" AND artist LIKE ?");
        if (genre != null && !genre.trim().isEmpty()) query.append(" AND genre = ?");
        if (startYear != null && !startYear.trim().isEmpty()) query.append(" AND yearReleased >= ?");
        if (endYear != null && !endYear.trim().isEmpty()) query.append(" AND yearReleased <= ?");

        stmt = conn.prepareStatement(query.toString());

        // Set dynamic parameters in the prepared statement
        int paramIndex = 1;
        if (title != null && !title.trim().isEmpty()) stmt.setString(paramIndex++, "%" + title + "%");
        if (artist != null && !artist.trim().isEmpty()) stmt.setString(paramIndex++, "%" + artist + "%");
        if (genre != null && !genre.trim().isEmpty()) stmt.setString(paramIndex++, genre);
        if (startYear != null && !startYear.trim().isEmpty()) stmt.setInt(paramIndex++, Integer.parseInt(startYear));
        if (endYear != null && !endYear.trim().isEmpty()) stmt.setInt(paramIndex++, Integer.parseInt(endYear));

        rs = stmt.executeQuery();
%>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Artist</th>
        <th>Genre</th>
        <th>Year Released</th>
    </tr>
    <%
        boolean hasResults = false;
        while (rs.next()) {
            hasResults = true;
    %>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("title") %></td>
        <td><%= rs.getString("artist") %></td>
        <td><%= rs.getString("genre") %></td>
        <td><%= rs.getInt("yearReleased") %></td>
    </tr>
    <%
        }
        if (!hasResults) {
    %>
    <tr>
        <td colspan="5" style="text-align:center;">No records found.</td>
    </tr>
    <%
        }
    } catch (SQLException e) {
        out.println("<p style='color:red;'>Error retrieving results: " + e.getMessage() + "</p>");
    } finally {
        if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
        if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
        if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
    }
    %>
</table>

<a href="search_music.jsp">New Search</a>
