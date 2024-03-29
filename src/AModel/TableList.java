/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AModel;

import java.util.ArrayList;

/**
 *
 * @author Jaydon
 */
public class TableList extends ArrayList{
      public static final long serialVersionUID=2L;
    /*
    Properties
    */

    private DatabaseManager.Tables tableType;
    
    /*
    Getter and Setter
    */
    public DatabaseManager.Tables getType(){
    return this.tableType;
    }
    

    
    public String getTableName(){
        String output=null;
        switch (this.tableType){
            case PRODUCT:
                output= "Product database";
                break;
            case SUPPLIER:
                output="Supplier database";
                break;
            case USER:
                output="User database";
                break;
            case CATALOGUE:
                output="Catalogue database";
                break;
            case LOG:
                output="Log database";
                break;
    }
     return output;
    }
    
    public String getTableFileName(){
        return this.getTableName().replace(" database",".txt");
    }
    

    /*
    Constructor
    */
    public TableList(){};
    
    public TableList(DatabaseManager.Tables tableType){
        this.tableType = tableType;
    }
    
        public Identification.headerGroup toHeader(boolean isNotPM){
        Identification.headerGroup H=null;
        switch (this.tableType){
            case PRODUCT:
                H = Identification.headerGroup.PM;
                break;
            case SUPPLIER:
                H = Identification.headerGroup.SP;
                break;
            case USER:
                if (isNotPM){
                H = Identification.headerGroup.AD;
                }
                else if(!isNotPM){
                H = Identification.headerGroup.PM;
                }
                break;
            }
        return H;   
        }

    
    /*
    Methods
    */
    @Override
    public String  toString(){
        return ("Table: \n"+this.getTableName()+"\n"+"Table Content: "+ super.toString());
    }
    
}
