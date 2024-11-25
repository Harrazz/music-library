/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.music.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import com.music.model.MusicItem;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Harraz
 */
public class ListLibraryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List musics = new ArrayList();
        musics.add(new MusicItem("Cardigan", "2020", "Indie Folk", "Taylor Swift"));
        musics.add(new MusicItem("Someone like you", "2011", "Pop/Soul", "Adele"));
        musics.add(new MusicItem("Fight Song", "2015", "Pop", "Rachel Platten"));
        musics.add(new MusicItem("Wawasan Setia Warga UiTM", "1980s", "Patriotic", "UiTM"));
        
        // Get sorting parameters
        String sortBy = request.getParameter("sort");
        String sortOrder = request.getParameter("sortOrder");

        // Sort the list if a sorting parameter is provided
        if ("year".equalsIgnoreCase(sortBy)) {
            if ("asc".equalsIgnoreCase(sortOrder)) {
                Collections.sort(musics, Comparator.comparing(MusicItem::getYear));
                sortOrder = "desc";  // Toggle sort order for next click
            } else {
                Collections.sort(musics, Comparator.comparing(MusicItem::getYear).reversed());
                sortOrder = "asc";
            }
        } else if ("artist".equalsIgnoreCase(sortBy)) {
            if ("asc".equalsIgnoreCase(sortOrder)) {
                Collections.sort(musics, Comparator.comparing(MusicItem::getArtist));
                sortOrder = "desc";
            } else {
                Collections.sort(musics, Comparator.comparing(MusicItem::getArtist).reversed());
                sortOrder = "asc";
            }
        } else {
            sortOrder = "asc"; // Default to ascending for next click
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Music Library List</title>");
            
            out.println("<style>");
            out.println("body { display: flex; justify-content: center; align-items: center;}");
            out.println(".container { text-align: center; }");
            out.println("table { width: 50%; border-collapse: collapse; margin-top: 20px; margin-left: auto; margin-right: auto;}");
            out.println("th, td { border: 1px solid #000; padding: 8px; text-align: center;}");
            out.println("th { background-color: #4CAF50; }");
            out.println("</style>");
            
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("You currently have <b>" + musics.size() + "</b> Music in your collection: <br>");
            
            // Add sorting options with toggle functionality
            out.println("<p>Sort by: ");
            out.println("<a href='?sort=year&sortOrder=" + sortOrder + "'>Year</a> | ");
            out.println("<a href='?sort=artist&sortOrder=" + sortOrder + "'>Artist</a> | ");
            out.println("<a href='?sort=default'>Default</a> | ");
            out.println("</p>");
            
            out.println("<table border='0' cellspacing='0' cellpadding='5'>");
            out.println("<tr>");
            out.println("<th>TITLE</th>");
            out.println("<th>GENRE</th>");
            out.println("<th>YEAR</th>");
            out.println("<th>ARTIST</th>");
            out.println("</tr>");

            Iterator it = musics.iterator();
            while (it.hasNext()) {
                MusicItem item = (MusicItem) it.next();
                out.println("<tr>");
                out.println("<td>" + item.getTitle() + "</td>");
                out.println("<td>" + item.getGenre() + "</td>");
                out.println("<td>" + item.getYear() + "</td>");
                out.println("<td>" + item.getArtist() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            
            //home button          
            out.println("<br><a href='index.html'><button>Return Home</button></a>");
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
