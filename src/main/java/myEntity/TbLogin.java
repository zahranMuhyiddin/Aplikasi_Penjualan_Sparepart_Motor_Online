/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myEntity;

import com.google.gson.Gson;

/**
 *
 * @author LENOVO
 */
public class TbLogin {

    String name, username, pass, status;

    public TbLogin() {
    }

    public TbLogin(String name, String username, String pass, String status) {
        this.name = name;
        this.username = username;
        this.pass = pass;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return String.format("[{name:%s,username:%s,pass:%s,status:%s}]", name, username, pass, status);
    }

    public void toObject(String string) {
        name = string.substring(string.indexOf("name") + 5, string.indexOf("username") - 1);
        username = string.substring(string.indexOf("username") + 9, string.indexOf("pass") - 1);
        pass = string.substring(string.indexOf("pass") + 5, string.indexOf("status") - 1);
        status = string.substring(string.indexOf("status") + 7, string.indexOf("]") - 1);
    }

}
