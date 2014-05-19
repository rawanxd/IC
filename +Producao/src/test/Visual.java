/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Random;
import dataCon.DataBase;
import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Brawan
 */
public class Visual {
  DataBase dbPrincipal;
     
      public Visual() throws IOException, SQLException{
        dbPrincipal = DataBase.getDbL("preferences.txt"); 
        
        theVisual();
        groupsColors();
        
        if (!Desktop.isDesktopSupported()) {  
            System.err.println("Desktop not supported");  
            System.exit(1);  
        }  

        File file = new File("parallel/parallel.html");  

        Desktop desktop = Desktop.getDesktop();  
        try {  
            desktop.browse(file.toURI());  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }
    } 
    
    public void newVisual() throws SQLException, IOException{
        int max = 0,count  = 0 ;
        
        Object[][] prod = new Object[100000][34];
        String name = "";
        ResultSet rs = null;
        String sql ="";              // BD

        FileWriter fstream = new FileWriter("parallel/data/data.js");         //WRITE
        BufferedWriter out = new BufferedWriter(fstream); 
        
        rs  = dbPrincipal.selecting("select count(nome) as \"count(nome)\" from pesquisador;");

        if(rs.next()){
          max = rs.getInt("count(nome)");               // GET MAX number of Researchers
        }
      
        out.write("var datas = [ ");
        
        for(int i = 1 ; i <= max ; i++){
           rs = dbPrincipal.selecting("select p.nome as Nome,i.nome as try from pesquisador as p ,unidadeacademica as i where p.idpesquisador = "+i+" and idunidade = KEYUNIDADE ");
            
            while(rs.next()){
               prod[i][1] = rs.getString("Nome"); 
               prod[i][3] = rs.getString("try");
            }
            
            prod[i][0] = "name";
            prod[i][2] = "group";
//select p.nome ,i.nome from pesquisador as p ,unidadeacademica as i where p.idpesquisador = "+i+" and idunidade = KEYUNIDADE       
        }
         
        for(int i = 1 ; i <= max ; i++){
            for(int j = 2 ; j <= 16 ; j++){
                sql = "select count(p.nome) as \"count(p.nome)\" from producao as p , pesquisador as j , tipoproducao as t  "
                                       +"where j.idpesquisador = "+i+" and t.idtipoproducao = "+(j+23)
                                       +" and j.idpesquisador = p.keypesquisador and t.idtipoproducao = p.keytipoproducao";
                rs =  dbPrincipal.selecting(sql);// t.nome              
                if(rs.next()){                   
                    prod[i][(j*2)+1] = rs.getObject("count(p.nome)");
                }
                rs =  dbPrincipal.selecting("select nome from tipoproducao where idtipoproducao = "+(j+23));
                if(rs.next())
                    prod[i][j*2] =  rs.getObject("nome");
            }
        }
       
        for(int i = 1 ;i <= max;i++){
            out.write("{");
       
            for(int j = 0 ; j < 32;j++){
                if( (j%2) == 0 || j <=3)
                  out.write("\"");
                
                out.write( String.valueOf(prod[i][j]));
            
                if( (j%2) == 0 || j <=3 )
                    out.write("\"");
                
                if(j != 31 && j % 2 == 0)
                    out.write(": ");
                else
                    out.write(" , ");
            }
            out.write("} ,");
            out.newLine();  
       }
        out.write(" ] ; ");
        out.newLine();
        out.close();
    }   
        //////////////////////////////////////////////////////////////////////////////////////// Details /////////////////////////////////////
public void groupsColors() throws IOException, SQLException{
    
        int max = 0;
        ResultSet rs = null;
        String sql ="";             
        
        FileWriter fstream = new FileWriter("parallel/data/data.js",true);         //WRITE
        BufferedWriter out = new BufferedWriter(fstream); 
               
        out.newLine();
        out.write ("var groups = [ ");
        
        rs = dbPrincipal.selecting("select idunidade from unidadeacademica ;");
        
        while(rs.next()){
            out.write(rs.getInt("idunidade")+",\r\n");
             max = rs.getInt("idunidade");
        }
        out.write(" ];\r\n");
        out.write("var colors = { ");
        
        for(int i = 1 ;i <= max ;i++){
          out.write(" "+i+" : \'#"+newColors(i)+"\' ,\r\n");
        }
        
        out.write( "}");
        out.close();
    }
    
    public String colors(){
        String ans = "";
        int cor;
        char color[] = {'1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        Random rand = new Random();
        for(int i =0 ; i<6; i++){
            cor =  rand.nextInt(14);
            ans += color[cor];
        }
        return ans;
    }
    
    public String newColors(int nun){
      nun = nun%10;
      String ans[] = {"000000","FF0000","00FF00","0000FF","FFFF00","00FFFF","FF00FF","9900FF","FF6633","330000"};  
      return ans[nun];
    }	

    public void theVisual() throws SQLException, IOException{
        String dic[] = {"name", "group", "Capitulo Internacional", "Capitulo Nacional", "Livro Internacional", "Livro Nacional",
          "Artigo Completo Nacional", "Artigo Completo Internacional", "Artigo Resumo Internacional", "Artigo Resumo Nacional",
          "Trabalho Completo Internacional", "Trabalho Completo Nacional", "Trabalho Resumo Internacional", "Trabalho Resumo Nacional",
          "Trabalho Resumo Expandido Internacional", "Trabalho Resumo Expandido Nacional"};
        
        Object[][] prod = new Object[100000][16];
        int max = 0;
        ResultSet rs = null;
        String sql ="";              // BD

        FileWriter fstream = new FileWriter("parallel/data/data.js");         //WRITE
        BufferedWriter out = new BufferedWriter(fstream); 
        
        rs  = dbPrincipal.selecting("select nome,idpesquisador,keyunidade from pesquisador;");
        
        while(rs.next()){
          prod[rs.getInt("idpesquisador")][0] = rs.getObject("nome");
          prod[rs.getInt("idpesquisador")][1] = rs.getObject("keyunidade");
          max = rs.getInt("idpesquisador");
        }
        
        for(int i = 1 ; i <= 12; i++){
           rs  = dbPrincipal.selecting("select p.idpesquisador as \"p.idpesquisador\", count(p.nome) as \"count(p.nome)\"  from pesquisador as p , producao as j"
                 + " where p.idpesquisador = j.keypesquisador and j.keytipoproducao = "+(i+25)+ " group by idpesquisador ;");         
           while(rs.next()){
             prod[rs.getInt("p.idpesquisador")][i+2] = rs.getObject("count(p.nome)");
           }  
        }
        
        out.write("var datas = [ ");
        
        for(int i = 1; i <= max; i++){
          sql += (" { \""+dic[0]+"\""+" : \""+prod[i][0]+"\" ,"+" \""+dic[1]+"\""+" : "+prod[i][1]+" ,");
          for(int j = 2; j < 14; j++){
            sql+=(" \""+dic[j]+"\""+" : ");
            sql+=(prod[i][j]==null? "0, " : ""+prod[i][j]+", ");
          }
          sql+=("},\r\n");
          out.write(sql);
          sql = "";
        }
        out.write("];");
        out.close();
   }  

  }

