/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.controllers;

import edu.wctc.wtb.bookwebapp.ejbs.AuthorFacade;
import edu.wctc.wtb.bookwebapp.models.Author;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author kanst_000
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authors"})
public class AuthorController extends HttpServlet {
    private final static String ERR_NO_PARAMETER = "Request cannot be processed. Please go back and try again.";
    private final static String LIST_PAGE = "authors.jsp";
    private final static String EDIT_PAGE = "edit.jsp";
    private final static String VIEW_PAGE = "view.jsp";
    private final static String ADD_PAGE = "create.jsp";
    private final static String TABLE_NAME = "author";
    private final static String AUTHOR_ID = "aid";
    private final static String AUTHOR_ID_COLUMN = "author_id";
    private final static String AUTHOR = "author";
    private final static String AUTHORS = "authors";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    
    @EJB
    private AuthorFacade authorService;
    
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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String resultPage = "index.jsp";
        String action = request.getParameter("action");
        
        if (action!=null) {
            
            int authorId;
            List<Author> authors;
            Author author;
            String firstName, lastName;
            try {
                switch(action){
                    //Delete button pressed
                    case "Delete":
                        authorId = Integer.parseInt(request.getParameter(AUTHOR_ID));
                        authorService.deleteAuthorById(authorId);
                        resultPage = LIST_PAGE;
                        authors = authorService.findAll();
                        request.setAttribute(AUTHORS, authors);
                        break;
                    //Admin link clicked
                    case "list":
                        resultPage = LIST_PAGE;
                        authors = authorService.findAll();
                        request.setAttribute(AUTHORS, authors);
                        break;
                    //View button clicked
                    case "View":
                        resultPage = VIEW_PAGE;
                        authorId = Integer.parseInt(request.getParameter(AUTHOR_ID));
                        author = authorService.find(authorId);
                        request.setAttribute(AUTHOR, author);                    
                        break;
                    case "Edit":
                        resultPage = EDIT_PAGE;
                        authorId = Integer.parseInt(request.getParameter(AUTHOR_ID));
                        author = authorService.find(authorId);
                        request.setAttribute(AUTHOR, author); 
                        break;
                        //On Edit Save:
                    case "Update":
                        System.out.println(request.getParameter(AUTHOR_ID));
                        authorId = Integer.parseInt(request.getParameter(AUTHOR_ID));
                        firstName = request.getParameter(FIRST_NAME);
                        lastName = request.getParameter(LAST_NAME);
                        authorService.updateById(authorId, firstName, lastName);
                        resultPage = LIST_PAGE;
                        authors = authorService.findAll();
                        request.setAttribute(AUTHORS, authors);
                        break;
                    case "Add New":
                        resultPage = ADD_PAGE;
                        break;
                        //On New Record Add:
                    case "Add":
                        firstName = request.getParameter(FIRST_NAME);
                        lastName = request.getParameter(LAST_NAME);
                        authorService.addNew(firstName, lastName);
                        resultPage = LIST_PAGE;
                        authors = authorService.findAll();
                        request.setAttribute(AUTHORS, authors);
                        break;
                    default:
                        request.setAttribute("errMsg", ERR_NO_PARAMETER);
                }
            } catch (Exception e) {
                request.setAttribute("errMsg", e.getMessage());
            }
        } else {
            
        }
        request.setAttribute("bodyPage", resultPage);
        RequestDispatcher view =
                request.getRequestDispatcher("_Layout.jsp");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @Override
    public void init() throws ServletException{
        
    }
}
