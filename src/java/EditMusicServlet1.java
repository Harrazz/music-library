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

@WebServlet(urlPatterns = {"/EditMusicServlet1"})
public class EditMusicServlet1 extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String genre = request.getParameter("genre");
        String yearReleased = request.getParameter("yearReleased");

        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MusicLibraryDB", "app", "app");
             PreparedStatement stmt = conn.prepareStatement("UPDATE Music SET title=?, artist=?, genre=?, yearReleased=? WHERE id=?")) {
            
            stmt.setString(1, title);
            stmt.setString(2, artist);
            stmt.setString(3, genre);
            stmt.setInt(4, Integer.parseInt(yearReleased));
            stmt.setInt(5, Integer.parseInt(id));

            int rowsUpdated = stmt.executeUpdate();
            response.getWriter().println(rowsUpdated > 0 ? "Record updated successfully!" : "No record updated.");

            response.sendRedirect("list_music.jsp");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Error updating record: " + e.getMessage());
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
        return "Servlet for editing music records in the MusicLibraryDB";
    }
}
