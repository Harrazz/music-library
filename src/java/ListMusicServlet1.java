import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ListMusicServlet1"})
public class ListMusicServlet1 extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Music Library</title></head>");
        out.println("<body>");
        out.println("<h1>Music Library</h1>");
        out.println("<table border='1' cellpadding='5'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Title</th>");
        out.println("<th>Artist</th>");
        out.println("<th>Genre</th>");
        out.println("<th>Year Released</th>");
        out.println("<th>Actions</th>");
        out.println("</tr>");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Music";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                int yearReleased = rs.getInt("yearReleased");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + title + "</td>");
                out.println("<td>" + artist + "</td>");
                out.println("<td>" + genre + "</td>");
                out.println("<td>" + yearReleased + "</td>");
                out.println("<td><a href='edit_music.html?id=" + id + "&title=" + 
                            title + "&artist=" + artist + "&genre=" + genre + 
                            "&yearReleased=" + yearReleased + "'>Edit</a></td>");
                out.println("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>Error retrieving music data: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        out.println("</table>");
        out.println("<br>");
        out.println("<a href='add_music.html'>Add New Music</a>");
        out.println("</body></html>");
        
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for displaying music library records with an edit option";
    }
}
