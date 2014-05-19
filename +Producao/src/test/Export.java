/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Brawan
 */
public class Export {
    
    public Export() throws IOException{
            
    }
    
    public void exportOS(Object[] data,String nome,boolean flag) throws IOException{
            int i =0;
        FileWriter fstream = new FileWriter(nome);         //WRITE
        
        BufferedWriter out = new BufferedWriter(fstream); 
        
        for(i= 0;i<data.length;i++){
            if(data[i] != null){
                out.write((String)data[i]);
                out.newLine();
            }
        }
        out.close();
    }
    
    public void exportOM(Object[][] data,String nome,boolean flag) throws IOException{
         int i =0;
        int j =0;
        int max1 = data.length;
        int max2 = data[0].length;
        
        FileWriter fstream = new FileWriter(nome,true);         //WRITE
        BufferedWriter out = new BufferedWriter(fstream); 
  //      System.out.println(data[0][13].toString());
        
        out.newLine(); // Visualization get better
        
        for(i= 0;i<max1;i++){
           for(j=0;j<max2;j++){
                 
               if(data[i][j] != null){
                    out.write(data[i][j].toString()+";");
               }         
           }
           out.newLine();
        }
        
        out.close();
    }
    
    public void exportStr(String[] data,String nome,boolean flag) throws IOException{
        int i =0;
        FileWriter fstream = new FileWriter(nome);         //WRITE
        BufferedWriter out = new BufferedWriter(fstream); 
        
        for(i =0 ;i <data.length ;i++){
            out.write(data[i]+";");
        }
        out.newLine();
        out.close();        
    }
    
}
