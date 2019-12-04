/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AModel;

import java.io.IOException;

/**
 *
 * @author Jaydon
 */
public class MenuModel {
    public User user;
    public DatabaseManager DB;
    public Log session ;
    
   
    
    public MenuModel(User u, DatabaseManager db) throws IOException{
     this.user = u;
     this.DB =db;  
}
    
    public Identification returnNewID(TableList t,boolean isNotPM){
        Identification i = new Identification(t.toHeader(isNotPM),DB.getNewIdentification(t,true,isNotPM));
        return i;
    }
    
    
    public String toString(){
    return this.user.toString() + "\n" + this.DB.toString();
    }
}
