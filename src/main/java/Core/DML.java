/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import myConnection.DBO;

/**
 *
 * @author LENOVO
 */
public class DML {
    static Connection Cnn;
    static DBO db=new DBO();
    static Statement st;
    static int i;
    
    //Eksekusi Perintah INSERT, UPDATE, DELETE
    public static int EQuery(String sql) throws SQLException {
        try {
            Cnn = db.getConnection();
            st = Cnn.createStatement();
            i = st.executeUpdate(sql);
            return i;
        } catch (Exception e) {
            return 0;
        }
        finally{
            Cnn.close();
        }
    }
    
    
}
