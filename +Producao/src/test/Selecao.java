/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dataCon.DataBase;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brawan
 */
public class Selecao {
    
       
    public Selecao(int ano , int mes) throws IOException, SQLException {
        
        int ans[] = new int[14];
        int i = 0;
        String[] columnNames = {"PRODUCAO","TOTAL","Ano","Mes"}; 
        Object[][] data = new Object[24][4];
        
        ResultSet rs;
        String sql ="";
        DataBase dbPrincipal = DataBase.getDbL("preferences.txt");  

      //  dbPrincipal.connect();
    
        sql = "select nome from tipoproducao ";                   
        rs  = dbPrincipal.selecting(sql);
        
        data[0][2] = ano;
        data[0][3] = mes;
        
        while(rs.next()){
               data[i][0] = rs.getString("nome");
               i++;
        }
        
        
      for(i = 25 ; i< 49;i++ ){
      
        if(mes == 0 ){
             sql = "select count(keypesquisador) from producao where keytipoproducao ="+i+" and year(date) ="+ano;                   
        }
        else{
             sql = "select count(keypesquisador) from producao where keytipoproducao ="+i+" and year(date) = "+ano+" and month(date)= "+mes;
        }
        rs  = dbPrincipal.selecting(sql);
        
        while(rs.next())
           data[i-25][1] = rs.getInt("count(keypesquisador)");
     }
     
     Tables table = new Tables(columnNames,data);
   }
    
}
