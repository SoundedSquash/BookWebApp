/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.models;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kanst_000
 */
public interface AuthorDaoInterface {

    public void addAuthor(String tableName, String firstName, String lastName) throws Exception;
    
    public void updateAuthor(String tableName, String columnNameForId, Object id, String firstName, String lastName) throws Exception;
    
    public int deleteRecordById(String tableName, String columnName, Object id) throws Exception;
    
    List<Author> getAuthorList(String tableName, int maxRecords) throws Exception;
    
    public List<Author> getAuthorById(String tableName, int authorId) throws Exception;

    DbAccessor getDb();

    String getDriver();

    String getServer();

    String getUser();

    void setDb(DbAccessor db);

    void setDriver(String driver);

    void setPassword(String password);

    void setServer(String server);

    void setUser(String user);
    
}
