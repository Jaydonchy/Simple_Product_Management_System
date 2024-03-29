/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AController;
import AModel.User;
import AModel.DatabaseManager;
import AModel.FolderManager;
import AModel.LoginInfo;
import AModel.MenuModel;
import AView.MenuView;

import java.awt.event.ActionEvent;
import java.nio.file.*;
import java.io.*;


/**
 *
 * @author Jaydon
 */
public class LoginController {
    public AView.LoginView view;
    public FolderManager fMan;
    public DatabaseManager database;
    public boolean isFirst=false;
    
    /**
     * Constructor for Login Controller (Basically the Login window)
     * @param view 
     * @throws java.io.IOException 
     */
    public LoginController() throws IOException {

        view = new AView.LoginView();
        if (view.getLocal().equals("local")){
        try{
            fMan = new AModel.FolderManager(false);
            view.setLocal(fMan.getLocalRoot().toString());
        }
        catch(FileNotFoundException e0){
         // Prompt for new file
            fMan = new AModel.FolderManager(true);
            view.rootPrompt();
            Path p = Paths.get(this.view.getLocal());
            this.fMan.setLocalRoot(p.toString());
            System.out.println(p.toString());
            System.out.println(fMan.writeLocal(p));
            fMan.setLocalRoot(this.view.getLocal());
            this.isFirst=true;
                } 
        catch(IOException e1){
            // Cannot be thrown for some reason
            }
        
        database = new DatabaseManager(fMan);
        if(this.isFirst==true)
            database.databaseAddMasters();
           }
        
        
        /**
         * Event Listener
         */
        
        this.view.addEnterListener(new EnterListener());
        }
    
    
    public class EnterListener implements java.awt.event.ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            User user=null;
                LoginInfo l;
                try{
                l = new LoginInfo(view.getUsername(),view.getPassword());
                user=l.Authenticate(database);
                    System.out.println("test1");
                MenuView mainMenu = new MenuView(new MenuModel(user,new DatabaseManager(new FolderManager(false))));
                view.setVisible(false);
                }   
                catch(AModel.LoginInfo.FailedAuthenticationException e0){
                    view.setWarning("Incorrect Username or Password");
                }
                catch (Exception e1){
                    System.out.println("login fial: "+e1);
                }

            
        }
    
    
    }
}
    
    

