/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.models;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kanst_000
 */
public interface DbAccessor {

    void closeConnection() throws SQLException;

    List<Map<String, Object>> findRecordsFromTable(String tableName, int maxRecords) throws SQLException;
    public int insertRecord(String tableName, List<String> columnNames, List<Object> columnValues) throws SQLException;
    public List<Map<String,Object>> findRecordsById(String tableName, String columnName, Object id) throws SQLException;
    public int updateRecordById(String tableName, String columnNameForId, Object id, List<String> columns, List<Object> values) throws SQLException;
    public int deleteRecordById(String tableName, String columnName, Object id) throws SQLException;
    

    void openConnection(String driver, String server, String user, String password) throws ClassNotFoundException, SQLException;
    
}
