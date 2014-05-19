package starport.xmlImport;
import java.sql.*;
import java.util.Vector;
import dataCon.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class StoreCurriculum
{
    // instance variables - replace the example below with your own
    private static DataBase dbmain;
    private static Curriculum researcher;
    public static int progress;
    
    
    public StoreCurriculum() throws SQLException
    {
    }

    public static void storeALL(Vector<Curriculum> curriculos)throws SQLException{
        StoreCurriculum.dbmain = DataBase.getDbL("preferences.txt");
        
        for(int i = 0 ;i < curriculos.size(); i++){
            researcher = curriculos.get(i);         
            storeInstitution();
            storeUnidade();
            storeName();
            storeCapitulosInt();
            storeCapitulosNac();
            storeLivrosInt();
            storeLivrosNac();
            storeArtigosCompletoNacional();
            storeArtigosCompletoInterNacional();
            storeArtigosResumoInternacional();
            storeArtigosResumoNacional();
            storeTrabalhoCompInt();
            storeTrabalhoCompNac();
            storeTrabalhoResInt();
            storeTrabalhoResNac();
            storeTrabalhoResExpInt();
            storeTrabalhoResExpNac();
            storeOrientacaoIniciacao();
            storeParticipacaoBancaConcursoPublico();
            storeOrientacaoGraduacao();
            storeOrientacaoEspecializacao();
            storeOrientacoesMestrado();
            storeOrientacaoDoutorado();
            storeParticipacaoBancaGraduacao();        
            storeParticipacaoBancaAperfeicoamentoEspecializacao();        
            storeParticipacaoBancaMestrado();
            storeParticipacaoBancaDoutorado();
            storeFormation();
            
            progress = ( ( (i+1)*100 ) / curriculos.size() );
        }
    }
    
    private static void storeFormation() throws SQLException
    {
      Vector<Formacao> form =  researcher.getFormacao();
      int  l = form.size();

    	for (int i = 0; i < l;i++){
          DataSet data = new DataSet( dbmain, "SELECT CODNIVEL FROM FORMACAO WHERE CODNIVEL = \'"+(form.get(i)).getCodNivel()+"\'");                        
          if( data.getRecordCount() == 0 )
            dbmain.update( "insert into FORMACAO (CODNIVEL,NOME,SEQORDNIVEL) values (\'" +
                          (form.get(i)).getCodNivel() + "\', \'" + (form.get(i)).getNome() + "\', \'" + (form.get(i)).getSeqOrdNivel() + "\')  " );

       // }
          
          dbmain.update( "insert into PESQUISADORFORMACAO (KEYPESQUISADOR,KEYFORMACAO,QUANTIDADE) values (\'" +
                        researcher.getIdResearcher() + "\', \'" +  (form.get(i)).getCodNivel() + "\', \'" + (form.get(i)).getQuantidade() + "\')  " );
        
        }
      }

    
    private static void storeInstitution() throws SQLException
    {  
        DataSet data = new DataSet( dbmain, "SELECT IDIES FROM IES WHERE SIGLA = \'"+researcher.getAcronymInstitutionName()+"\'");            
        //rs  = dbmain.update("SELECT IDIES FROM IES WHERE SIGLA = \'"+researcher.getAcronymInstitutionName()+"\'");
        if( data.getRecordCount() == 0 )
        { 
            dbmain.update( "insert into IES ( NOME, SIGLA) "+"values (\'"+researcher.getInstitutionName()+"\' , \'"+researcher.getAcronymInstitutionName()+"\') " );
     //       System.out.println("insert into IES ( NOME, SIGLA) "+"values (\'"+researcher.getInstitutionName()+"\' , \'"+researcher.getAcronymInstitutionName()+"\')" );
            DataSet data2 = new DataSet( dbmain, "SELECT IDIES FROM IES WHERE SIGLA = \'"+researcher.getAcronymInstitutionName()+"\'" );                                    
            data2.first();  
            researcher.setIdIes( data2.getResultSet().getInt("IDIES") );   
        }
        else
        {
            data.first();              
            researcher.setIdIes( data.getResultSet().getInt("IDIES") );   
        }
        
    }
    
    private static void storeUnidade() throws SQLException
    {
        
        DataSet data = new DataSet( dbmain, "SELECT IDUNIDADE FROM UNIDADEACADEMICA WHERE nome = \'"+researcher.getUnidadeAcademica()+"\'");            
            
        if( data.getRecordCount() == 0 )
        { 
            dbmain.update( "insert into UNIDADEACADEMICA(NOME) "+"values (\'"+researcher.getUnidadeAcademica()+"\')  " );
            DataSet data2 = new DataSet( dbmain, "SELECT IDUNIDADE FROM UNIDADEACADEMICA WHERE NOME = \'"+researcher.getUnidadeAcademica()+"\'" );                                    
            data2.first();  
            researcher.setIdUni( data2.getResultSet().getInt("IDUNIDADE") );   
        }
        else
        {
            data.first();              
            researcher.setIdUni( data.getResultSet().getInt("IDUNIDADE") );   
        }
    }
    
    private static void storeName() throws SQLException{
    	DataSet data = new DataSet( dbmain, "SELECT IDPESQUISADOR FROM PESQUISADOR WHERE NOME = \'"+researcher.getName()+"\'");            
            
        if( data.getRecordCount() == 0 )
        { 
            dbmain.update(  "insert into PESQUISADOR ( KEYIES,NOME,KEYUNIDADE,AREA,SUBAREA) " + "values (\'" + researcher.getIdIes()+ "\', \'"+researcher.getName()+
                    "\', \'"+researcher.getIdUni()+"\', \'"+researcher.getArea()+"\', \'"+researcher.getSubArea()+"\')  ");
            DataSet data2 = new DataSet( dbmain, "SELECT IDPESQUISADOR FROM PESQUISADOR WHERE NOME = \'"+researcher.getName()+"\'");                                    
            data2.first();  
            researcher.setIdResearcher( data2.getResultSet().getInt("IDPESQUISADOR") );   
       }
        else
        {
            data.first();              
            researcher.setIdResearcher( data.getResultSet().getInt("IDPESQUISADOR") );   
        }  
   }
    
              

 /*     data = dbmain.selecting("SELECT HASH FROM PRODUCAO WHERE KEYPESQUISADOR = "+idResearcher+" AND KEYTIPOPRODUCAO = "+idTipoProducao+
                " AND HASH = "+"\'"+producao.get(i).getHash()+"\'");       
        
        if(!data.next())
 //         hash = data.getString("HASH");
        */
  //      if( hash == ""){}

    private static void storeProducao( int idResearcher, int idTipoProducao, Vector<Producao> producao ) throws SQLException{
        //   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	//   Date date = new Date();
      ResultSet data; 
      String hash = "";
      String dataUp = fixData();

      for (int i=0;i<producao.size();i++)
      {	                   
        DataSet datas = new DataSet( dbmain,"SELECT HASH FROM PRODUCAO WHERE KEYPESQUISADOR = "+idResearcher+" AND KEYTIPOPRODUCAO = "+idTipoProducao+
                                   " AND HASH = "+"\'"+producao.get(i).getHash()+"\'");                   

        if( datas.getRecordCount() == 0 )  
          dbmain.update(  "INSERT INTO PRODUCAO ( KEYPESQUISADOR, KEYTIPOPRODUCAO, ANO, VALOR,IDIOMA,PAIS,DATE,HASH,DUVIDA,NOME) "+
            "VALUES (\'"+idResearcher+"\' , \'"+
                        idTipoProducao+"\', \'"+
                        producao.get(i).getAno()+"\' , \'"+
                        producao.get(i).getQuantidade()+"\' , \'"+(producao.get(i)).getIdioma()+"\' , \'"+
                        producao.get(i).getPais()+"\' , \'"+
                        dataUp+"\' , \'"+producao.get(i).getHash()+"\' , \'"+producao.get(i).getDuvida()+"\' , \'"+
                        producao.get(i).getNome()+"\')  ;");                               
      } 
    }
    
    private static String fixData(){
      String result = "";

      String oldFormat = "ddMMyyyy";
      String newFormat = "yyyy-MM-dd";

      SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
      SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

      try {
         result = sdf2.format(sdf1.parse(researcher.getDate()));

      } catch (ParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      
      return result;
    }
    
    private static int getIdTipoProducao(String parameter) throws SQLException
    {
        int idTipoProducao = 0 ;
        DataSet data = new DataSet( dbmain, "SELECT IDTIPOPRODUCAO FROM TIPOPRODUCAO WHERE NOME = \'"+parameter+"\'");            
            
        if(data.getRecordCount() ==0 )
        { 
            dbmain.update(  "insert into TIPOPRODUCAO ( NOME ) "+"values (\'"+parameter+"\')  ");
                        
            DataSet data2 = new DataSet( dbmain, "SELECT IDTIPOPRODUCAO FROM TIPOPRODUCAO WHERE NOME = \'"+parameter+"\'");                                    
            data2.first();  
            idTipoProducao = ( data2.getResultSet().getInt("IDTIPOPRODUCAO") );
        }
        else
        {
            data.first();              
            idTipoProducao = ( data.getResultSet().getInt("IDTIPOPRODUCAO") );
        }
        return( idTipoProducao );
    }
    
    private static void storeCapitulosInt() throws SQLException
    {
  	    if (researcher.getCapitulosInt()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Capitulo Internacional"),
                         researcher.getCapitulosInt()
                        );
    }
    
    private static void storeCapitulosNac() throws SQLException
    {
  	    if (researcher.getCapitulosNac()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Capitulo Nacional"),
                         researcher.getCapitulosNac()
                        );
    }
    
    private static void storeLivrosInt() throws SQLException
    {
    	if (researcher.getLivroInt()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Livro Internacional"),
                         researcher.getLivroInt()
                        );
    }                    

     private static void storeLivrosNac() throws SQLException
    {
    	if (researcher.getLivroNac()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Livro Nacional"),
                         researcher.getLivroNac()
                        );
    }

    private static void storeArtigosCompletoInterNacional() throws SQLException
    {
    	if (researcher.getArtigosCompletoInternacional()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Artigo Completo Internacional"),
                         researcher.getArtigosCompletoInternacional()
                        );        
        
    }  
    private static void storeArtigosCompletoNacional() throws SQLException
    {
    	if (researcher.getArtigosCompletoNacional()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Artigo Completo Nacional"),
                         researcher.getArtigosCompletoNacional()
                        );        
        
    }                    
    
    private static void storeArtigosResumoInternacional() throws SQLException
    {
    	if (researcher.getArtigosResumoInternacional()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Artigo Resumo Internacional"),
                         researcher.getArtigosResumoInternacional()
                        );        
        
    }                    
    
    private static void storeArtigosResumoNacional() throws SQLException
    {
    	if (researcher.getArtigosResumoNacional()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Artigo Resumo Nacional"),
                         researcher.getArtigosResumoNacional()
                        );        
        
    }
    
    private static void storeTrabalhoCompNac() throws SQLException
    {
  	    if (researcher.getTrabalhoCompNac()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Trabalho Completo Nacional"),
                         researcher.getTrabalhoCompNac()
                        );
    }
    
    private static void storeTrabalhoCompInt() throws SQLException
    {
  	    if (researcher.getTrabalhoCompInt()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Trabalho Completo Internacional"),
                         researcher.getTrabalhoCompInt()
                        );
    }
    
    private static void storeTrabalhoResNac() throws SQLException
    {
  	    if (researcher.getTrabalhoResNac()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Trabalho Resumo Nacional"),
                         researcher.getTrabalhoResNac()
                        );
    }
    
    private static void storeTrabalhoResInt() throws SQLException
    {
  	    if (researcher.getTrabalhoResInt()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Trabalho Resumo Internacional"),
                         researcher.getTrabalhoResInt()
                        );
    }
    
    private static void storeTrabalhoResExpNac() throws SQLException
    {
  	    if (researcher.getTrabalhoResExpNac()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Trabalho Resumo Expandido Nacional"),
                         researcher.getTrabalhoResExpNac()
                        );
    }
    
    private static void storeTrabalhoResExpInt() throws SQLException
    {
  	    if (researcher.getTrabalhoResExpInt()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Trabalho Resumo Expandido Internacional"),
                         researcher.getTrabalhoResExpInt()
                        );
    }
     private static void storeOrientacaoIniciacao() throws SQLException
    {
    	if (researcher.getOrientacaoIniciacao()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Orientacao Iniciacao"),
                         researcher.getOrientacaoIniciacao()
                        );
    }
        
    private static void storeOrientacaoGraduacao() throws SQLException
    {
    	if (researcher.getOrientacaoGraduacao()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Orientacao Graduacao"),
                         researcher.getOrientacaoGraduacao()
                        );
    }
        
    private static void storeOrientacaoEspecializacao() throws SQLException
    {
    	if (researcher.getOrientacaoEspecializacao()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Orientacao Especializacao"),
                         researcher.getOrientacaoEspecializacao()
                        );
    }

    private static void storeOrientacoesMestrado() throws SQLException
    {
    	if (researcher.getOrientacaoMestrado()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Orientacao Mestrado"),
                         researcher.getOrientacaoMestrado()
                        );
    }
    
    private static void storeOrientacaoDoutorado() throws SQLException
    {
    	if (researcher.getOrientacaoDoutorado()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Orientacao Doutrorado"),
                         researcher.getOrientacaoDoutorado()
                        );
    }    
        
    private static void storeParticipacaoBancaAperfeicoamentoEspecializacao() throws SQLException
    {
    	if (researcher.getParticipacaoBancaAperfeicoamentoEspecializacao()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Participacao Banca Aperfeicoamento Especializacao"),
                         researcher.getParticipacaoBancaAperfeicoamentoEspecializacao()
                        );
    }            
    private static void storeParticipacaoBancaGraduacao() throws SQLException
    {       
    	if (researcher.getParticipacaoBancaGraduacao()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Participacao Banca Graduacao"),
                         researcher.getParticipacaoBancaGraduacao()
                        );
    }    
    
    private static void storeParticipacaoBancaMestrado() throws SQLException
    {
    	if (researcher.getParticipacaoBancaMestrado()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Participao Banca Mestrado"),
                         researcher.getParticipacaoBancaMestrado()
                        );
    }    

    private static void storeParticipacaoBancaDoutorado() throws SQLException
    {
    	if (researcher.getParticipacaoBancaDoutorado()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Participao Banca Doutrorado"),
                         researcher.getParticipacaoBancaDoutorado()
                        );
    }    
    
    private static void storeParticipacaoBancaConcursoPublico() throws SQLException
    {
    	if (researcher.getParticipacaoBancaConcursoPublico()!=null)
            storeProducao( researcher.getIdResearcher(),
                         getIdTipoProducao("Participao em Banca Concurso Publico"),
                         researcher.getParticipacaoBancaConcursoPublico()
                        );        
        
    }
/*    
    public static int getProgress(){
        return progress;
    }
    
    public static void setProgress(int x){
        progress = x;
    }*/
}