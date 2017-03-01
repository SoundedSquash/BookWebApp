/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author kanst_000
 */
public class ConnPoolAuthorDao implements AuthorDaoInterface {
    private DataSource ds;
    private DbAccessor db;

    public ConnPoolAuthorDao(DataSource ds, DbAccessor db) {
        this.ds = ds;
        this.db = db;
    }
    
    
    @Override
    public void addAuthor(String tableName, String firstName, String lastName) throws Exception{
        db.openConnection(ds);
        db.insertRecord(tableName, Arrays.asList("first_name","last_name","date_added"), Arrays.asList((Object)firstName,lastName,new Date()));
        db.closeConnection();
    }
    
    @Override
    public void updateAuthor(String tableName, String columnNameForId, Object id, String firstName, String lastName) throws Exception{
        db.openConnection(ds);
        db.updateRecordById(tableName, columnNameForId, id, Arrays.asList("first_name","last_name"), Arrays.asList((Object)firstName,lastName));
        db.closeConnection();
    }
    
    @Override
    public int deleteRecordById(String tableName, String columnName, Object id) throws Exception{
        db.openConnection(ds);
        int affected = db.deleteRecordById(tableName, columnName, id);
        db.closeConnection();
        return affected;
    }
    
    @Override
    public List<Author> getAuthorList(String tableName, int maxRecords) throws Exception{
        List<Author> authorList;
        db.openConnection(ds);
        
        List<Map<String, Object>> rawData = db.findRecordsFromTable(tableName, maxRecords);
        db.closeConnection();
        
        authorList = convertRawData(rawData);
        
        return authorList;
    }
    
    @Override
    public List<Author> getAuthorById(String tableName, int authorId) throws Exception{
        List<Author> authorList;
        db.openConnection(ds);
        
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
