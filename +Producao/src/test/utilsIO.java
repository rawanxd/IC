/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class utilsIO {
    
    public utilsIO(){
    }
    
    public static void write(String file , String str){
      try {
        FileWriter fstream = null;
        fstream = new FileWriter(file); //WRITE
        BufferedWriter out = new BufferedWriter(fstream); 

        out.write(str);
        out.newLine();

        out.close();
      }catch (IOException ex) {
          Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    public static void writeLines(String file , Vector<String> str){
      try {
        FileWriter fstream = null;
        fstream = new FileWriter(file); //WRITE
        BufferedWriter out = new BufferedWriter(fstream); 
        
        for(int i = 0; i < str.size(); i++){
            out.write(str.get(i));
            out.newLine();
        }
        
        out.close();
      
      }catch (IOException ex) {
          Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    public static String read(String file) throws FileNotFoundException, IOException{
        String result = "";
        BufferedReader br = null;                   // READ
        String sCurrentLine;

        br = new BufferedReader(new FileReader(new File(file)));            //// start copy first part of Index
        while ((sCurrentLine = br.readLine()) != null) {
              result += sCurrentLine;
        }   
        return result;
    }
    
    public static Vector<String> readLines(String file) throws FileNotFoundException, IOException{
        Vector<String> vet = new Vector();
        BufferedReader br = null;                   // READ
        String sCurrentLine;

        br = new BufferedReader(new FileReader(new File(file)));            //// start copy first part of Index
        while ((sCurrentLine = br.readLine()) != null) {
              vet.add(sCurrentLine);  
        }   
        return vet;
    }

    public static Vector<String> getLanguage(Vector<String> names) throws IOException{
        Vector <String> result =  new Vector();
        Locale currentLocale;
        ResourceBundle messages;
        String local = "";

        local = read("locale.txt");

        if(local == "en_US")
            currentLocale = new Locale("pt","BR");
        else
            currentLocale = new Locale("en","US");

        messages = ResourceBundle.getBundle("locales.MessagesBundle",currentLocale);
        
        for(int i = 0; i < names.size(); i++){
            result.add(messages.getString(names.get(i)));
        }
        
        return result;
    }
    
    public static Vector<String> getTranslate(String names) throws IOException{
        String[] splited = names.split("\\s+");
        
        Vector <String> result =  new Vector();
        Locale currentLocale;
        ResourceBundle messages;
        String local = "";
 
        local = read("locale.txt");

        if(local.equals("en_US"))
            currentLocale = new Locale("en","US");
        else
            currentLocale = new Locale("pt","BR");

        messages = ResourceBundle.getBundle("locales.MessagesBundle",currentLocale);
        
        for(int i = 0; i < splited.length; i++){
  //          System.out.println(splited[i]);
            result.add(messages.getString(splited[i]));
        }
        
        return result;
    }
    
}

