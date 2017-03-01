/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import javax.sql.DataSource;

/**
 *
 * @author kanst_000
 */
public class MySQLDbAccessor implements DbAccessor {
    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    
    @Override
    public void openConnection(String driver, String server, String user, String password) throws ClassNotFoundException, SQLException{
            Class.forName (driver);
            this.connection = DriverManager.getConnection(server, user, password);
    }
    
    /**
     * Open a connection using a connection pool configured on server.
     *
     * @param ds - a reference to a connection pool via a JNDI name, producing
     * this object. Typically done in a servlet using InitalContext object.
     * @throws SQLException - if ds cannot be established
     */
    @Override
    public final void openConnection(DataSource ds) throws SQLException {
        connection = ds.getConnection();
    }
    
    @Override
    public List<Map<String,Object>> findRecordsFromTable(String tableName, int maxRecords) throws SQLException{
        String sql = "SELECT * FROM " + tableName + (maxRecords > 0 ? " LIMIT " + maxRecords : "");
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        
        List<Map<String,Object>> results = new ArrayList<>();
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String,Object> record;
        
        while(rs.next()){
            record = new LinkedHashMap<>();
            for(int colNo = 1; colNo <= colCount; colNo++){
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, rs.getObject(colNo));
            }
            results.add(record);
        }
        
        return results;
    }
    
    @Override
    public List<Map<String,Object>> findRecordsById(String tableName, String columnName, Object id) throws SQLException{
        String sql = "SELECT * FROM " + tableName + " WHERE " + columnName + " = " + id;
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);
        
        List<Map<String,Object>> results = new ArrayList<>();
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String,Object> record;
        
        while(rs.next()){
            record = new LinkedHashMap<>();
            for(int colNo = 1; colNo <= colCount; colNo++){
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, rs.getObject(colNo));
            }
            results.add(record);
        }
        
        return results;
    }
    
    @Override
    public int deleteRecordById(String tableName, String columnName, Object id) throws SQLException{
        String sId = null;
        Integer intId = null;
        int affected;
        
        if (id instanceof String) {
            sId = id.toString();
        } else if (id instanceof Integer) {
            intId = (Integer)id;
        }
        
        String sql = "DELETE FROM " + tableName + " WHERE " + columnName +" = " + id;
        statement = connection.createStatement();
        affected = statement.executeUpdate(sql);
        
        return affected;
    }
    
    @Override
    public int insertRecord(String tableName, List<String> columnNames, List<Object> columnValues) throws SQLException{
        int affected;
        
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(",", "(", ")");
        
                for(String name: columnNames){
                    sj.add(name);
                }
                sql += sj + " VALUES ";
                
                sj = new StringJoiner(",", "(", ")");
                for(Object value: columnValues){
                    sj.add("?");
                }
                sql += sj;
                
                PreparedStatement ps = connection.prepareCall(sql);
                for(int i = 0; i < columnValues.size(); i++){
                    ps.setObject(i+1, columnValues.get(i));
                }
                
        affected = ps.executeUpdate();
        
        return affected;
    }
    
    @Override
    public int updateRecordById(String tableName, String columnNameForId, Object id, List<String> columns, List<Object> values) throws SQLException{
        String sId = null;
        Integer intId = null;
        int affected;
        
//        if (id instanceof String) {
//            sId = id.toString();
//        } else if (id instanceof Integer) {
//            intId = (Integer)id;
//        }
        
        String sql = "UPDATE " + tableName + " SET ";
        
        StringJoiner sj = new StringJoiner(", ");
        for(String column: columns){
            sj.add(column + " = ?");
        }
        
        sql += sj.toString();
        
        sql += " WHERE " + columnNameForId + " = " + id;
                
        PreparedStatement ps = connection.prepareCall(sql);
        for(int i = 0; i < values.size(); i++){
            ps.setObject(i+1, values.get(i));
        }
                
        affected = ps.executeUpdate();
        
        return affected;
    }
    
    @Override
    public void closeConnection() throws SQLException{
        if (connection != null) {
        connection.close();
        connection = null;
        }
    }
    
    public static void main(String[] args) throws Exception{
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/book";
	String userName = "root";
	String password = "admin";
        
        DbAccessor db = new MySQLDbAccessor();
        db.openConnection(driverClassName, url, userName, password);
        
        db.updateRecordById("author", "author_id", 3, Arrays.asList("first_name", "last_name"), Arrays.asList((Object)"Joshua", "Strait"));
        //db.insertRecord("author", Arrays.asList("first_name", "last_name", "date_added"), Arrays.asList((Object)"Charles", "Bob","2017-02-18"));
        List<Map<String,Object>> records = db.findRecordsFromTable("author", 50);
        db.closeConnection();
        
        for(Map<String,Object> record: records){
            System.out.println(record);
        }
    }
}
