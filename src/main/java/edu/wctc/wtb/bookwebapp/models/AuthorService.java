/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wboyer
 */
public class AuthorService {
    private AuthorDaoInterface dao;

    public AuthorDaoInterface getDao() {
        return dao;
    }

    public void setDao(AuthorDaoInterface dao) {
        this.dao = dao;
    }

    public AuthorService(AuthorDaoInterface dao) {
        this.dao = dao;
    }
    
    public List<Author> getAllAuthors(String tableName, int maxRecords) throws Exception{
        return dao.getAuthorList(tableName, maxRecords);
    }
    public List<Author> getAuthorById(String tableName, int authorId) throws Exception{
        return dao.getAuthorById(tableName, authorId);
    }
    
    
    public static void main(String[] args) throws Exception {
        AuthorService as = new AuthorService(
                new AuthorDao(
                    new MySQLDbAccessor(),
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/book",
                    "root",
                    "admin"
                )
        );
        
        List<Author> authors = as.getAllAuthors("author", 50);
        
        for(Author a: authors){
            System.out.println(a);
        }
    }
}
