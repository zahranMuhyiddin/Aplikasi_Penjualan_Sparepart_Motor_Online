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
import com.mycompany.projectakhir.coba;
import myEntity.TbLogin;

/**
 *
 * @author LENOVO
 */
public class SysLogin {
    public boolean coba;
    static DBO db=new DBO();
    int i;
    
    
    
    public int Regis(TbLogin reg){
        try {
            String sql = "insert into tblogin values('"+reg.getName()+"','"+reg.getUsername()+"','"
                    +reg.getPass();
            
            i = DML.EQuery(sql);
            
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
            
        
    
}
