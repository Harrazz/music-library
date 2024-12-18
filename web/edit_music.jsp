<%-- 
    Document   : edit_music
    Created on : Nov 25, 2024, 5:44:33 PM
    Author     : Harraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Music Details</title>
        <script>
            // Function to parse URL parameters and set form values
            function populateForm() {
                const urlParams = new URLSearchParams(window.location.search);
                document.getElementById('id').value = urlParams.get('id') || '';
                document.getElementById('title').value = urlParams.get('title') || '';
                document.getElementById('artist').value = urlParams.get('artist') || '';
                document.getElementById('genre').value = urlParams.get('genre') || '';
                document.getElementById('yearReleased').value = urlParams.get('yearReleased') || '';
            }

            // Call the function on page load
            window.onload = populateForm;
        </script>
    </head>
    <body>
        <form action="EditMusicServlet1" method="post">
            <h2>Edit Music Details</h2>
            <!-- Make sure the ID is pre-filled and included as a hidden field -->
            <input type="hidden" name="id" id="id">

            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required><br><br>

            <label for="artist">Artist:</label>
            <input type="text" id="artist" name="artist" required><br><br>

            <label for="genre">Genre:</label>
            <input type="text" id="genre" name="genre" required><br><br>

            <label for="yearReleased">Year Released:</label>
            <input type="text" id="yearReleased" name="yearReleased" required pattern="\d{4}"><br><br>

            <input type="submit" value="Edit Music">
        </form>
    </body>
</html>
