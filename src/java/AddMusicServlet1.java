import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/AddMusicServlet1"})
public class AddMusicServlet1 extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        if (session ==null || session.getAttribute("username") == null) {
            
            response.sendRedirect("login.html");
            return;
        }
        
        String title = request.getParameter("title");
        String artist = request.getParameter("artist"); // Fixed typo here
        String genre = request.getParameter("genre");
        String yearReleased = request.getParameter("yearReleased");

        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");
            String query = "INSERT INTO Music (title, artist, genre, yearReleased) VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, artist);
            stmt.setString(3, genre);
            stmt.setInt(4, Integer.parseInt(yearReleased));
            stmt.executeUpdate();

            // Redirect to the ListMusicServlet upon successful insertion
            response.sendRedirect("list_music.jsp");
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Show an error message or redirect to an error page
            response.getWriter().println("Error adding music: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
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
        return "Servlet for adding music to the MusicLibraryDB";
    }
}
