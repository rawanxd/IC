/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dataCon.DataBase;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Brawan
 */
public class Busca {
    
    public Busca(){
    
    }
    
    public Busca(String pesquisador ,String producao ,String area ,String subArea ,int anoL ,int anoH) throws SQLException, IOException{                          
        int i = 0;                  //Control Variables
        int rowcount = 0;
        Map< String , Integer> myMap = new HashMap();
        String[] columnNames = {"PESQUISADOR","PRODUCAO","TIPO PRODUCAO","AREA","SUBAREA"};     // Table Column Names
        ResultSet rs;                
        
        DataBase dbPrincipal = DataBase.getDbL("preferences.txt");                              // Connect to BD
        //dbPrincipal.connect();

        //WHERE MATCH (description) AGAINST('keyword1 keyword2')    
        String sql ="select p.nome as \"p.nome\" ,j.nome as \"j.nome\" ,t.nome as \"t.nome\" ,p.area as \"p.area\" ,p.subarea as \"p.subarea\" "
             +" from pesquisador as p ,producao as j,tipoproducao"        // Sql Query
             + "   as t where j.keypesquisador = p.idpesquisador and j.keytipoproducao = t.idtipoproducao"
             +pesquisador+producao+area+subArea+" and year(j.date) > "+anoL+" and year(j.date) < "+anoH;
        
        rs  = dbPrincipal.selecting(sql);       //Make the search at BD
       
       if(rs.last()) {
            rowcount = rs.getRow();         // Discover de amount of result found
            rs.beforeFirst(); 
        }
      
       Object[][] data = new Object[rowcount][5];  //Create object to create de table
       
        while(rs.next()){

            data[i][0] = rs.getString("p.nome");//p.
            data[i][1] = rs.getString("j.nome");//j.        // FIll the object with the data received from the BD selection
            data[i][2] = rs.getString("t.nome");
            data[i][3] = rs.getString("p.area");
            data[i][4] = rs.getString("p.subarea");
            i++;
            if(myMap.get(rs.getString("p.nome"))!= null)
              myMap.put(rs.getString("p.nome") ,myMap.get(rs.getString("p.nome"))+1);
            else
              myMap.put(rs.getString("p.nome") ,1);
        }
        
       Tables table = new Tables(columnNames,data);     // Call method to display table
       hits(myMap);
       
    }
    
    public void hits(Map< String , Integer> myMap){
      String[] columnNames = {"PESQUISADOR","HITS"};
      Object[][] data = new Object[myMap.size()][2];
      int i = 0;
      for (Map.Entry<String, Integer> entry : myMap.entrySet())
      {
        data[i][0] = entry.getKey();
        data[i][1] = entry.getValue();
        i++;
      }
       Tables table = new Tables(columnNames,data);     // Call method to display table
    }
}
