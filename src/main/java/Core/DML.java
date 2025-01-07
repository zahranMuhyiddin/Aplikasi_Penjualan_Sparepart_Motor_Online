/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import java.sql.Statement;
import java.sql.Connection;
import myConnection.DBO;

/**
 *
 * @author LENOVO
 */
public class DML {
    static DBO db = new DBO();

    // Eksekusi Perintah INSERT, UPDATE, DELETE
    public static int EQuery(String sql) {
        try (Connection cnn = db.getConnection();
             Statement st = cnn.createStatement()) {
            return st.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println("Eksekusi query gagal: " + e.getMessage());
            return 0;
        }
    }
}
