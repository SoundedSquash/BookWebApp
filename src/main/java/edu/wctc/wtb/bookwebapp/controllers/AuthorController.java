/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.controllers;

import edu.wctc.wtb.bookwebapp.models.Author;
import edu.wctc.wtb.bookwebapp.models.AuthorService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kanst_000
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authors"})
public class AuthorController extends HttpServlet {
    private final static String ERR_NO_PARAMETER = "Request cannot be processed. Please go back and try again.";

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
        response.setContentType("text/html;charset=UTF-8");
        String resultPage = "index.jsp";
        String action = request.getParameter("action");
        
        AuthorService authorService = new AuthorService();
        try {
            switch(action){
                case "list":
                    resultPage = "authors.jsp";
                    List<Author> authors = authorService.getAllAuthors();
                    request.setAttribute("authors", authors);
                    break;
                default:
                    request.setAttribute("errMsg", ERR_NO_PARAMETER);
            }
        } catch (Exception e) {
            request.setAttribute("errMsg", e.getMessage());
        }
        
        RequestDispatcher view =
                request.getRequestDispatcher(resultPage);
        view.forward(request, response);
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
