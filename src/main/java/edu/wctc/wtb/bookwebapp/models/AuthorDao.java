/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kanst_000
 */
public class AuthorDao implements AuthorDaoInterface {
    private DbAccessor db;
    private String driver, server, user, password;

    public AuthorDao(DbAccessor db, String driver, String server, String user, String password) {
        this.db = db;
        this.driver = driver;
        this.server = server;
        this.user = user;
        this.password = password;
    }
    
    @Override
    public List<Author> getAuthorList(String tableName, int maxRecords) throws Exception{
        List<Author> authorList;
        db.openConnection(driver, server, user, password);
        
        List<Map<String, Object>> rawData = db.findRecordsFromTable(tableName, maxRecords);
        db.closeConnection();
        
        authorList = convertRawData(rawData);
        
        return authorList;
    }
    
    @Override
    public List<Author> getAuthorById(String tableName, int authorId) throws Exception{
        List<Author> authorList;
        db.openConnection(driver, server, user, password);
        
        List<Map<String, Object>> rawData = db.findRecordsById(tableName, "author_id", authorId);
        db.closeConnection();
        
        authorList = convertRawData(rawData);
        
        return authorList;
    }
    
    private List<Author> convertRawData(List<Map<String, Object>> rawData){
        List<Author> authorList = new ArrayList<>();
        
        for(Map<String, Object> recData : rawData){
        Author author = new Author();
        Object data;
        
        //Author ID
        data = recData.get("author_id");
        int id = (Integer)data;
        
        //Author Name
        data = (recData.get("first_name") != null ? 
                recData.get("first_name").toString() + " " : 
                "") + 
                (recData.get("last_name") != null ? 
                recData.get("last_name").toString() : 
                null);
        String name = (String) data;
        
        data = recData.get("date_added") != null ? recData.get("date_added") : null;
        Date date = (Date)data;
        
        author.setAuthorId(id);
        author.setAuthorName(name);
        author.setDateAdded(date);
        authorList.add(author);
    }
        
        return authorList;
    }

    @Override
    public DbAccessor getDb() {
        return db;
    }

    @Override
    public void setDb(DbAccessor db) {
        this.db = db;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getServer() {
        return server;
    }

    @Override
    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    
    public static void main(String[] args) throws Exception {
        AuthorDaoInterface dao = new AuthorDao(new MySQLDbAccessor(),
        "com.mysql.jdbc.Driver",
        "jdbc:mysql://localhost:3306/book",
        "root",
        "admin");
        
        List<Author> authors = dao.getAuthorList("author", 50);
        
        for(Author a: authors) {
            System.out.println(a);
        }
    }
}
