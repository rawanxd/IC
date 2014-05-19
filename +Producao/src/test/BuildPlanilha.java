/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dataCon.DataBase;
import static dataCon.DataBase.readPreferences;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brawan
 */
public class BuildPlanilha {
    
    public BuildPlanilha(){
    
    }
    
     public BuildPlanilha(int begin ,int end) throws IOException, SQLException{
        int adjust[] = {29,28,26,34,38,36,30,27,25,33,37,35,};
        int i = 0;
        int j = 0;   
        int k = 0;
        boolean flag = true;
        ResultSet rs;
        String sql ="";
        DataBase dbPrincipal = DataBase.getDbL("preferences.txt"); 
        
        Object[][] data = new Object[126][14];
        String[] columnNames = { "PRODUCAO","jan","fev","mar","abr","maio","junho","julho","agos","set","out","nov","dez","TOTAL" };    // cabecalho	
        String[] unidades = new String [9];      // TOTAL = UFG + 8 grandes areas do conhecimento
        String[] tipoProducao = {   "periodicos nacionais"    
                                    ,"Livro nacional"   
                                    ,"Capitulos de livro nacional" 
                                    ,"Trabalhos completos publicados em anais de congresso nacional"
                                    ,"Resumos expandidos nacionais"
                                    ,"Resumos em congressos nacionais"
                                    ,"Artigos completos publicados em periodicos internacionais"
                                    ,"Livro internacional"
                                    ,"Capitulos de livro Internacional"
                                    ,"Trabalhos completos publicados em anais de congresso internac."
                                    ,"Resumos expandidos internacionais"
                                    ,"Resumos em congressos internacionais"
                                    ,"\n"
                                };
        
        unidades[0] = "UNIVERSIDADE FEDERAL DE GOIAS";
        
        for(i =1 ;i<9;i++){ 
            sql = "select nome from unidadeacademica where idunidade ="+i;          //// NOME  DA UNIDADE ACADEMICA
            rs  = dbPrincipal.selecting(sql);

            while(rs.next())
              unidades[i] = rs.getString("nome");
        }
       
        for(i = 0 ;i<9;i++){          
           for(j=0 ;j<14;j++){
             if(flag){
                 data[(i*14)+j][0] = unidades[i];                                    ///  PUT THE NAME OF PRODUCTIONS AND ACADEMIC UNITY ON THE DATA
                 flag = false;
             }
            else
                data[(i*14)+j][0] = tipoProducao[j-1];                    
          }
           flag = true;
        }
       
        //UFG TOTAL
        for(i=1;i<13;i++){        // Tipos Producao                
           for(j=1;j<14;j++){  //mes                    
              if(j==13)
                sql = "select count(keypesquisador) as \"count(keypesquisador)\" from producao,pesquisador,ies where keytipoproducao = "+adjust[i-1]+" and keypesquisador = idpesquisador"+" and year(date) > "+begin+" and year(date) < "+end+" and sigla = \'UFG\' and keyies = idies";
              else
               sql = "select count(keypesquisador) as \"count(keypesquisador)\" from producao,pesquisador,ies where keytipoproducao = "+adjust[i-1]+" and keypesquisador = idpesquisador"+" and year(date) > "+begin+" and year(date) < "+end+" and month(date) = "+j+" and sigla = \'UFG\' and keyies = idies";;   
              
              rs  = dbPrincipal.selecting(sql);
              
                while(rs.next())
                 data[i][j] = rs.getInt("count(keypesquisador)");   
           }   
         }
        
         for(k = 1; k<9;k++){          //Areas do Conhecimento
            for(i=1;i<13;i++){        // Tipos Producao                
                for(j=1;j<14;j++){  //Mes                    
                    if(j==13)                       //// Caso seja o ultimo nao eh o mes e sim o TOTAL
                      sql = "select count(keypesquisador) as \"count(keypesquisador)\" from producao,pesquisador,ies where keytipoproducao = "+adjust[i-1]+" and keypesquisador = idpesquisador"+" and year(date) > "+begin+" and year(date) < "+end+" and keyunidade = "+k+" and sigla = \'UFG\' and keyies = idies";
                    else
                      sql = "select count(keypesquisador) as \"count(keypesquisador)\" from producao,pesquisador,ies where keytipoproducao = "+adjust[i-1]+" and keypesquisador = idpesquisador"+" and year(date) > "+begin+" and year(date) < "+end+" and month(date) = "+j+ " and keyunidade = "+k+" and sigla = \'UFG\' and keyies = idies";   

                    rs  = dbPrincipal.selecting(sql);
                      while(rs.next())
                       data[(k*14)+i][j] = rs.getInt("count(keypesquisador)");          // Adiciona o resultado no data
                        
                }   
            }
        }
       
        Tables table = new Tables(columnNames,data);
     }
}
