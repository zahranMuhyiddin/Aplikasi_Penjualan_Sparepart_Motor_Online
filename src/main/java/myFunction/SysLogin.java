/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myFunction;

import Core.DML;
import java.sql.Connection;
import myConnection.DBO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import myEntity.TbLogin;

/**
 *
 * @author LENOVO
 */
public class SysLogin {
    static DBO db=new DBO();
    
    
    
    public int Regis(TbLogin reg) {
        String sql = "INSERT INTO tblogin (name, username, password) VALUES (?, ?, ?)";
        try ( Connection cnn = db.getConnection();  PreparedStatement pst = cnn.prepareStatement(sql)) {

            // Set parameter query
            pst.setString(1, reg.getName());
            pst.setString(2, reg.getUsername());
            pst.setString(3, reg.getPass());

            // Eksekusi query
            return pst.executeUpdate();
        } catch (Exception e) {
            System.err.println("Registrasi gagal: " + e.getMessage());
            return 0;
        }
    }
            
        
    
}
