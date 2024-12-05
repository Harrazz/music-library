<%-- 
    Document   : logout
    Created on : Dec 2, 2024, 3:42:24 PM
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
        <% 
            session.invalidate();
            response.sendRedirect("index.html");
        %>
    </body>
</html>
