/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class DBO {
    public Connection getConnection() throws SQLException
    {
        Connection cn = null;
        try {
            String db= "Sparepart";
            String sv = "jdbc:mysql://localhost:3307/"+db;
            String driver="com.mysql.jdbc.Driver";
            
            Class.forName(driver);
            cn = DriverManager.getConnection(sv,"root","");
            return cn;
        } catch (Exception e) {
            return null;
        }
        
    }
    
}
