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
    public Connection getConnection() {
        Connection cn = null;
        try {
            String db = "sparepart";
            String sv = "jdbc:mysql://localhost:3306/" + db;
            String driver = "com.mysql.jdbc.Driver";

            Class.forName(driver); // Gunakan driver MySQL terbaru
            cn = DriverManager.getConnection(sv, "root", "");
        } catch (Exception e) {
            System.err.println("Koneksi database gagal: " + e.getMessage());
        }
        return cn;
    }
}
