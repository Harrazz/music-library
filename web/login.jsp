<%-- 
    Document   : login
    Created on : Nov 25, 2024, 4:15:31 PM
    Author     : Harraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*, java.io.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login.jsp" method="post">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Login">
        </form>
        <%
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if ("admin".equals(username) && "password".equals(password)) {
                    session.setAttribute("username", username);
                    response.sendRedirect("list_music.jsp");
                } else {
                    out.println("Invalid login. Try again.");
                }
            }
        %>
    </body>
</html>
