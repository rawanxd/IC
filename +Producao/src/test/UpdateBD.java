/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 
 
 */ 
package test;

import java.io.*;
import java.sql.*; 
import dataCon.DataBase;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import starport.xmlImport.ParserXml;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import starport.xmlImport.StoreCurriculum;

/**
 *
 * @author Brawan
 */

public class UpdateBD{
    String str;
    DataBase dbPrincipal;
    boolean bd = false;
    
    class TaskBar extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            ProgressBar bar = new ProgressBar();
            bar.createAndShowGUI();
            return null;
        }
    }   
    class TaskDB extends SwingWorker<Void, Void> {
    /*
     * Main task. Executed in background thread.
     */

        @Override
        public Void doInBackground() throws Exception {
            ParserXml parse;
            try{
            if(bd)
                parse = new ParserXml(str);
            else
                parse = new ParserXml();
          }
          catch (Exception e){
           System.out.print(e.toString());
          };
          return null;
        }
    }
    /**
     * @param args the command line arguments
     */
    
    public UpdateBD(String dir,boolean local) throws IOException, SQLException, InterruptedException, Exception{     
      str = dir;
      bd = local;
  //    FileWriter fstream = new FileWriter("prodNumber.txt");         //WRITE
  //    BufferedWriter out = new BufferedWriter(fstream);       
      
 //     ParserXml parse = new ParserXml(dbPrincipal,dir);
      
      TaskBar task1 = new TaskBar();
      TaskDB task2 = new TaskDB();
      task1.execute();
      task2.execute();
      StoreCurriculum.progress = 0;
    }
}
 /*     rs  = dbPrincipal.selecting("select count(nome) as \"count(nome)\" from pesquisador;");

      if(rs.next()){
        max = rs.getInt("count(nome)");               // GET MAX number of Researchers
      }
      out.write(max);*/

       /*  DataBase dbPrincipal;
           dbPrincipal = new DataBase();                               // BD
           dbPrincipal.connectDB();       
           //new Thread(new UpdateBD.thread1()).start();          
           
          if(update){        
               try{
             //  ParserXml parserXml = new ParserXml(dbPrincipal,"C:\\Users\\Brawan\\Desktop\\VLattes\\data\\xml");  //PARSER
                 ParserXml parserXml = new ParserXml(dbPrincipal,dir);  //PARSER
               }catch (Exception e){e.printStackTrace();}  
               
          }
          dbPrincipal.disconnect();*/
 
 
