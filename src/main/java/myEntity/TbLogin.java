/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myEntity;

/**
 *
 * @author LENOVO
 */
public class TbLogin {

    String name, username, pass;

    public TbLogin() {
    }
    
    public TbLogin(String name, String username, String pass) {
        this.name = name;
        this.username = username;
        this.pass = pass;
    }

    public TbLogin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return String.format("[{name:%s,username:%s,pass:%s}]", name, username, pass);
    }
    
    public void toObject(String string) {
        name = string.substring(string.indexOf("nim") + 5, string.indexOf("nama") - 1);
        username = string.substring(string.indexOf("nama") + 9, string.indexOf("prodi") - 1);
        pass = string.substring(string.indexOf("prodi") + 5, string.indexOf("konsentrasi") - 1);
    }

}
