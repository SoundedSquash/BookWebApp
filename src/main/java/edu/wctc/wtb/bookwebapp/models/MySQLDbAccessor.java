/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.wtb.bookwebapp.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kanst_000
 */
public class MySQLDbAccessor {
    private Connection connection;
    
    public void openConnection(String driver, String server, String user, String password) throws ClassNotFoundException, SQLException{
            Class.forName (driver);
            this.connection = DriverManager.getConnection(server, user, password);
    }
    
    public ResultSet executeQuery(String tableName, int maxRecords) throws SQLException{
        String sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
    
    public void closeConnection() throws SQLException{
        if (connection != null) {
        connection.close();
        connection = null;
        }
    }
}
