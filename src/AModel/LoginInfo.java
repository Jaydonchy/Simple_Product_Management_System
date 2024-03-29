/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AModel;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


/**
 *
 * @author Jaydon
 */
public class LoginInfo implements Serializable{
    /**
     * Variable Declaration
     */
    private String username;
    private char[] password;
    private String hashedPW;
    public static final long serialVersionUID=2L; 
    
    

    /**
     * Constructor, Getter and Setter
     * @return 
     */
    
    public LoginInfo(String username, String password) 
                throws NoSuchAlgorithmException, InvalidKeySpecException{
        this.username = username;
        this.password = password.toCharArray();
        this.hashedPW = toHashString(username,password.toCharArray());
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
   
    public User Authenticate(DatabaseManager dm) throws FailedAuthenticationException{
        TableList userTable = dm.getTable(DatabaseManager.Tables.USER);
        User output=null;
        User user;
        for (Object obj : userTable){
            System.out.println("=====");
            user = (User)obj;
            if(this.hashedPW.equals(user.getLogin().hashedPW)){
                System.out.println("true");
               output=user;
                    
            }
        }
        if (output==null)
        {
            throw new FailedAuthenticationException();
        }
        return output;
        }
    
    public void isLoginUnique (DatabaseManager dm) throws CredentialsAlreadyExistException{
        TableList userTable = dm.getTable(DatabaseManager.Tables.USER);
        boolean output = true;
        User user;
        for (Object obj : userTable){
            user = (User)obj;
            if(this.hashedPW.equals(user.getLogin().hashedPW)){
                output = false;
            }
        }
        if (output==false){
            throw new CredentialsAlreadyExistException();
        }
    }
        
    public static String toHashString(String username, char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        final int iteration =1000;
        final int keyLength = 128;
        byte[] salt = username.getBytes();
        KeySpec spec = new PBEKeySpec(password,salt,iteration,keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Arrays.toString(hash);
           
    }  
    
    
    
    public class FailedAuthenticationException extends Exception{};
    public class CredentialsAlreadyExistException extends Exception{};
    
    /**
     * Password Hashing
     * @return 
     */

    @Override
    public String toString(){
    return this.username;
    
    }
    
}
