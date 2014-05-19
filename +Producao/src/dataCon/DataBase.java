package dataCon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import test.Preferencias;
/**
 * <p>Title: DataBase</p>
 * <p>Description: Implementa uma Inst�ncia do Banco de Dados </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @author M�rcio Balian
 * @author Rog�rio Fiuza
 * @version 1.0
 */
public class DataBase {
  private static DataBase dataL = null;
  private static DataBase dataR = null;
  private Connection conn;

  public DataBase(String dir) {
      this.connect(dir);
  }
  /**
   * Faz a conex�o com o Banco de Dados via jodbc
   * @param alias caminho para o banco de dados, e.g. jdbc:odbc:SeuBancoDeDados
   * @param user nome do usu�rio
   * @param pwd senha do usu�rio
   */

  public static DataBase getDbL(String dir){
    if(dataL == null)
      dataL = new DataBase(dir);
    
    return dataL;
  }
  
  public static DataBase getDbR(String dir){
    if(dataR == null)
      dataR = new DataBase(dir);
    
    return dataR;
  }
  
  public boolean create(int typ, String ip, String login, String senha,String dbName) throws SQLException, IOException{
        boolean flag = false;
        int ans =0;
        BufferedReader br = null;                   // READ
        String sCurrentLine;
        String sql = "";
        Statement st;
       
     
       if(verifyConn("preferences.txt")) {          // verify if the user want to delete his database and create a new one
           ans = JOptionPane.showConfirmDialog(null," Banco de Dados existente , deseja criar novas tabelas?","Alert", JOptionPane.YES_NO_OPTION);
       }
       
       if(ans == 1)                 // verify if the user wants to delete his database
           return false;
       
       st = conn.createStatement();
              
       if(typ != 1){
            if( flag == false){
                st.executeUpdate("drop database if exists "+dbName+";");                  //drops the database and create one new if exists
                st.executeUpdate("create database "+dbName+";");                         // if theres no BD
            }

            st.executeUpdate("use "+dbName+" ;");

            br = new BufferedReader(new FileReader("lattes.sql"));            //// Read the BD arquiteture

            while ((sCurrentLine = br.readLine()) != null) {
              if(sCurrentLine.length() != 0){ 
                 sql+= sCurrentLine;                                          // Read until find "" to put an query to a line , then execute the line
              }
              else{  
                sql+= sCurrentLine;
                st.executeUpdate(sql);
                sql = "";
              }
            }
       }
       else{     
            br = new BufferedReader(new FileReader("HSQLDB.sql"));            //// Read the BD arquiteture

            while ((sCurrentLine = br.readLine()) != null) {
                if(sCurrentLine.length() != 0){ 
                   sql+= sCurrentLine;                                          // Read until find "" to put an query to a line , then execute the line
                }
                else{  
                  sql+= sCurrentLine;
                  st.executeUpdate(sql);
                  sql = "";
                }
            }    
       }
           
     return true;
  }
  
  public void connect(String dir){
    String[] prefer = readPreferences(dir);
    
    String alias = "";
    String user = prefer[3];
    String pwd = prefer[4];
    int typ = Integer.parseInt(prefer[0]);
    
    if(typ == 0){
        alias = "jdbc:mysql://"+prefer[2]+"/"+prefer[1];
        
        try{
          Class.forName("com.mysql.jdbc.Driver"); 
          conn = DriverManager.getConnection(
                             alias, 
                             user, 
                             pwd);
        }
        catch (Exception e){
           JOptionPane.showMessageDialog(null, "Erro IP ou Login ou Senha");;
           System.out.print(e.toString());
        };
    }
    else{
        alias = "jdbc:hsqldb:file:DB/"+prefer[2]+"/"+prefer[1];;
        try{
          Class.forName("org.hsqldb.jdbcDriver" ); 
          conn = DriverManager.getConnection(
                             alias, 
                             user, 
                             pwd);
        }
        catch (Exception cnfex){
            JOptionPane.showMessageDialog(null,"Erro IP ou Login ou Senha");;
            System.out.print(cnfex.toString());
        };
    }
  }
  
  public static boolean verifyConn(String info){
    Connection con;
    boolean flag = true;
    String[] prefer = readPreferences(info);
    String alias = "";
    String user = prefer[3];
    String pwd = prefer[4];
    int typ = Integer.parseInt(prefer[0]);
    
    if(typ == 0){
        alias = "jdbc:mysql://"+prefer[2]+"/"+prefer[1];
        
        try{
         Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection(
                             alias, 
                             user, 
                             pwd);
        }
        catch (ClassNotFoundException cnfex){
          return false;
        }
        catch (SQLException sqlex){
          return false;
        };
    }
    else{
        alias = "jdbc:hsqldb:file:DB/"+prefer[2]+"/"+prefer[1];
        try{
          Class.forName("org.hsqldb.jdbcDriver" ); 
          con = DriverManager.getConnection(
                             alias, 
                             user, 
                             pwd);
        }
        catch (ClassNotFoundException cnfex){
          return false;
        }
        catch (SQLException sqlex){
          return false;
        };   
    }
    return flag;
  }
  /**
   * Fecha a inst�ncia do banco de dados
   *
   */
  public void disconnect(){
	  try{
	    conn.close();
	  }
	  catch (SQLException e){
		  System.out.print(e.toString());
	  }
  }
  /** 
   * @param sql uma consulta SQL (SELECT * FROM tabela1...)
   * @return uma "tabela" com o conjunto de dados resultante da consulta 
   * informada
   */
  public ResultSet selecting(String sql){
      ResultSet res = null; 
      try{
          Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
          //ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
          res = stm.executeQuery(sql); 
      //  return stm.getResultSet();
       return res;
      }
      catch (SQLException e){
		  System.out.print(e.toString());
		  return null;
      }
  }
  
  /**
   * Executa uma instru��o SQL de atualiza��o (update, insert, ...) 
   * @param sql um comando SQL (INSERT INTO tabela1...)
   * @return -1 se houve algum erro. 
   * informada
   */  
  public int update(String sql){
	  try{
        Statement stm = conn.createStatement();            
        return stm.executeUpdate(sql);
	  }
	  catch (SQLException e){
		  System.out.print(e.toString());
		  return -1;
	  }
  }
  
  public static String[] readPreferences(String dir){
       BufferedReader br = null;                   // READ
       String sCurrentLine;
       String[] ans = new String[5];
       int i = 0;

       try {
           br = new BufferedReader(new FileReader(dir));            //// start copy first part of Index
           while ((sCurrentLine = br.readLine()) != null) {
                 ans[i] = sCurrentLine;  
                 i++;
           }
        } catch (IOException ex) {
            Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
        }
      return ans;
  } 
}