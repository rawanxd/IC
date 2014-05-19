/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starport.xmlImport;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author Victor
 */
public class SVe {
  
}

/*
 * package starport.xmlImport;

import com.sun.imageio.plugins.common.InputStreamAdapter;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class CurriculumXmlReader
{
        Vector production = new Vector(); 
        Vector anoCompletoN = new Vector();
        Vector qtdCompletoN = new Vector();
        Vector anoCompletoI = new Vector();
        Vector qtdCompletoI = new Vector();
        Vector<String> paisCompI = new Vector();
        Vector<String> idiomaCompI = new Vector();
        Vector<String> paisCompN = new Vector();
        Vector<String> idiomaCompN = new Vector();
        Vector nomeCompN = new Vector();
        Vector nomeCompI = new Vector();
        Vector duvidaCompN = new Vector();
        Vector duvidaCompIN = new Vector();
        Vector hashCompN = new Vector();
        Vector hashCompI = new Vector();

        Vector anoResumoN = new Vector();
        Vector qtdResumoN = new Vector();
        Vector anoResumoI = new Vector();
        Vector qtdResumoI = new Vector();
        Vector<String> paisResI = new Vector();
        Vector<String> idiomaResI = new Vector();
        Vector<String> paisResN = new Vector();
        Vector<String> idiomaResN = new Vector();
        Vector nomeResN = new Vector();
        Vector nomeResI = new Vector();
        Vector duvidaResN = new Vector();
        Vector duvidaResIN = new Vector();
        Vector hashResN = new Vector();
        Vector hashResI = new Vector();
        
	private Curriculum researcherResume = new Curriculum();
	private Element elem;
	
	public CurriculumXmlReader()throws Exception
	{
		
	}
                
	public Curriculum getResearcherResume(String xmlPathname) throws Exception
	{
		try
                {
                    //this.elem = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(xmlPathname)).getDocumentElement(); 
                    
                    File in =  new File(xmlPathname);                       // Find XML ENCODE
                    InputStreamReader r = new InputStreamReader(new FileInputStream(in));
                    //System.out.println(r.getEncoding());
                   
                    String encode = r.getEncoding();
                    InputSource is = new InputSource(new FileInputStream(xmlPathname)); //GET XML
                    is.setEncoding(encode);                                                     // Set XML Encode
                    this.elem = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is).getDocumentElement(); //GET XML Element
                                   
                } catch (Exception e) {
			System.out.println("Arquivo vazio ou mau formado" + e.toString());
			return null;
		}	  

		String result[] = getInstitutionNameAndAcronym();
		researcherResume.setInstitutionName(result[0]);        //nome  da instituicao
		researcherResume.setAcronymInstitutionName(result[1]);   //sigla da instituicao
		researcherResume.setName(getResearcherName());
		researcherResume.setFormacao(getFormacao());
                
                researcherResume.setOrientacaoMestrado(getProductions("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO",
				"DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO",
				"DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO",
				"TIPO-DE-ORIENTACAO",
				"ORIENTADOR_PRINCIPAL",
				null,
				null,
				null
		));
		researcherResume.setOrientacaoDoutorado(getProductions("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO",
				"DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO",
				"DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO",
				"TIPO-DE-ORIENTACAO",
				"ORIENTADOR_PRINCIPAL",
				null,
				null,
				null
		));        
		researcherResume.setOrientacaoGraduacao(getProductions("OUTRAS-ORIENTACOES-CONCLUIDAS",
				"DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"TIPO-DE-ORIENTACAO-CONCLUIDA",
				"ORIENTADOR_PRINCIPAL",
				"DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"NATUREZA",
				"TRABALHO_DE_CONCLUSAO_DE_CURSO_GRADUACAO"
		));        
		
		researcherResume.setOrientacaoIniciacao(getProductions("OUTRAS-ORIENTACOES-CONCLUIDAS",
				"DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"TIPO-DE-ORIENTACAO-CONCLUIDA",
				"ORIENTADOR_PRINCIPAL",
				"DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"NATUREZA",
				"INICIACAO_CIENTIFICA"
		));        
		
		researcherResume.setOrientacaoEspecializacao(getProductions("OUTRAS-ORIENTACOES-CONCLUIDAS",
				"DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"TIPO-DE-ORIENTACAO-CONCLUIDA",
				"ORIENTADOR_PRINCIPAL",
				"DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS",
				"NATUREZA",
				"MONOGRAFIA_DE_CONCLUSAO_DE_CURSO_APERFEICOAMENTO_E_ESPECIALIZACAO"
		));        
		
		researcherResume.setParticipacaoBancaMestrado(getProductions("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-MESTRADO",
				null,
				null,
				null,
				null,
				null,
				null,
				null
		));        
		
		researcherResume.setParticipacaoBancaDoutorado(getProductions("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-DOUTORADO",
				null,
				null,
				null,
				null,
				null,
				null,
				null
		));        
		
		researcherResume.setParticipacaoBancaGraduacao(getProductions("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-GRADUACAO",
				null,
				null,
				null,
				null,
				null,
				null,
				null
		));        
		
		researcherResume.setParticipacaoBancaAperfeicoamentoEspecializacao(getProductions("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-APERFEICOAMENTO-ESPECIALIZACAO",
				null,
				null,
				null,
				null,
				null,
				null,
				null
		));        
		
		researcherResume.setParticipacaoBancaConcursoPublico(getProductions("DADOS-BASICOS-DA-BANCA-JULGADORA-PARA-CONCURSO-PUBLICO",
				null,
				null,
				null,
				null,
				null,
				null,
				null
		));

		setData();
                setLivros();
                setCapitulos();
		setArtigosPeriodicos();
                setTrabalhos();
                setResumoExp();
                setUnidadeAcad();
                setAreas();
		return(researcherResume);
	}
	private Vector getFormacao(){
		Vector codNivel = new Vector();
		Vector qtd = new Vector();
		Vector formation = new Vector();
		
		Character cod;
		int index;

		NodeList nl = ((Element) elem.getElementsByTagName("FORMACAO-ACADEMICA-TITULACAO").item(0)).getChildNodes();
	
		for (int i = 1; i<nl.getLength(); i++){
			cod = new Character(((Element) nl.item(i++)).getAttribute("NIVEL").charAt(0));
			index = codNivel.indexOf(cod);
			if (index==-1){
				codNivel.add(cod);
				qtd.add(new CurriculumXmlReader.QtdForm(cod.charValue()));
			}
			else
			{
				((CurriculumXmlReader.QtdForm) qtd.get(index)).incQtd();
			}
		}

		for (int i=0;i<qtd.size();i++){
			formation.add(new Formacao(((CurriculumXmlReader.QtdForm) qtd.get(i)).getCodNivel(), ((CurriculumXmlReader.QtdForm) qtd.get(i)).getQtd()));
		}
		return formation;
	}
	private String[] getInstitutionNameAndAcronym() throws Exception
	{
		NodeList nl = elem.getElementsByTagName( "ENDERECO-PROFISSIONAL" );
		Element tagUsuario = (Element) nl.item(0);         
		
		String name = (tagUsuario==null)?"":tagUsuario.getAttribute( "NOME-INSTITUICAO-EMPRESA" );
		
		if (name.compareTo("")==0){
			String r[] = {"Falta Nome Da Instituicao","FNI"};
			return r;
		}
		name = name.replaceAll("[^\\p{L}\\p{N} ]", "");	 		
		String temp[]= name.split(" ");
		String acronym="";
		
		for(int cont = 0; cont<temp.length;++cont){
			if( temp[cont].compareToIgnoreCase("de")!=0 )
			{
				try {
				  acronym += ((temp[cont].charAt(0)));	
				} catch (Exception e) {
				}				 
			}
		}
		
		String result[]={name,acronym};
		return(result);
	}            
	
	
	private String getResearcherName() throws Exception
	{
		NodeList nl = elem.getElementsByTagName( "DADOS-GERAIS" );
		String Name;
		Element tagUsuario = (Element) nl.item(0);         
		Name = (tagUsuario == null )?"Falta Nome Do Pesquisador":tagUsuario.getAttribute( "NOME-COMPLETO" );
                Name = trata(Name);
                Name = Name.replaceAll("[^\\p{L}\\p{N} ]", "");
                
		return Name;
	}
        
        public Vector getProductions(String mainNode, String yearNode,  String testNode, String testAttribute,String testClause, String natureNode,String natureAttribute,String natureClause) 
                throws Exception { 
		
                Element product;
		Vector production = new Vector();
		
		Vector ano = new Vector();
		Vector qtd = new Vector();
		
		String anoPublicacao;
		boolean pass=false;
		
		int i = 0;
		int index;
		
		
		NodeList nl = elem.getElementsByTagName( mainNode );
		
		while((product = (Element) nl.item(i++)) != null){
                        
			if (testAttribute!=null){
				if (testNode!=null)
					pass=getChildTagAttribute( product, testNode, testAttribute).equals(testClause);
				else pass=product.getAttribute(testAttribute).equals(testClause);
				if (!pass)
					continue;
			}
			
			if (natureClause!=null){
				if (natureNode!=null)
					pass=getChildTagAttribute( product, natureNode, natureAttribute).equals(natureClause);
				else pass=product.getAttribute(natureAttribute).equals(natureClause);
				if (!pass)
					continue;
			}
			
			if (yearNode!=null)
				anoPublicacao=getChildTagAttribute( product, yearNode, "ANO" );
                        
			else anoPublicacao=product.getAttribute("ANO");
			
			anoPublicacao=(anoPublicacao.equals(""))?"1900":anoPublicacao;
			
			index = ano.indexOf(anoPublicacao);
			if (index==-1){
				ano.add(anoPublicacao);
				qtd.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
			}
			else
			{
				((CurriculumXmlReader.Qtd) qtd.get(index)).incQtd();
			}
		}
		
		for (i=0;i<qtd.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtd.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtd.get(i)).getQtd()));
		}
		return production;
	}
       //////////////////////////////////////////////////////// AREAS  e SUBAREA ///////////////////////////////////
        
         public void  setAreas() throws Exception{
         
                Element product;
                String area ="Nao Informada";
                String subArea = "Nao Informada";
                int i = 0;
                		
		NodeList nl = elem.getElementsByTagName("AREA-DO-CONHECIMENTO-1");
                
                
                if( (product = (Element) nl.item(i++)) != null){
                    subArea = product.getAttribute("NOME-DA-SUB-AREA-DO-CONHECIMENTO");
                    area = product.getAttribute("NOME-DA-AREA-DO-CONHECIMENTO");
                }
                
                if(area != ""){
                 area = area.replaceAll("[^\\p{L}\\p{N} ]", "");
                 area = trata(area);
		 researcherResume.setArea(area);
                }
                if(subArea != ""){
                 subArea = subArea.replaceAll("[^\\p{L}\\p{N} ]", "");
		 subArea = trata(subArea);
                 researcherResume.setSubArea(subArea);
                }
         }
        ///////////////////////////////////////////////////////////////////////// UNIDADE ACADEMICA //////////////////////////////
        
         public void setUnidadeAcad() throws Exception{
        	Element product;
                String unidadeAcademica ="Indeterminada";
                int i = 0;
                		
		NodeList nl = elem.getElementsByTagName("AREA-DO-CONHECIMENTO-1");
                
               if( (product = (Element) nl.item(i++)) != null)
                unidadeAcademica = product.getAttribute("NOME-GRANDE-AREA-DO-CONHECIMENTO");
                
                if(unidadeAcademica != ""){
                    unidadeAcademica = unidadeAcademica.replaceAll("[^\\p{L}\\p{N} _]", "");
                    unidadeAcademica = unidadeAcademica.replaceAll("[_]", " ");
                }
		 researcherResume.setUnidadeAcademica(unidadeAcademica);
        }
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   ///////////////// PEGA DATA DA Atualizacao do Curriculo
   public void setData(){
     Element product;          
     String date = "";
     int i =0 ;
     Element no;
     NodeList nl = elem.getElementsByTagName("DADOS-GERAIS");

     if((product = (Element) nl.item(i++)) != null){
       no =(Element) product.getParentNode();
       date = no.getAttribute("DATA-ATUALIZACAO");
     }
     researcherResume.setDate(date);
          
   }
        ///////////////////////////////////////////////////////// LIVROS  /////////////////////////////////////////////////////
        
        
        
    public void setLivros() throws Exception{
        	Element product;
		production.clear();
		
                nomeCompN.clear();              // Variaveis a Serem Lidas nos Aqruivos XML
                nomeCompI.clear();
		anoCompletoN.clear();
		qtdCompletoN.clear();
		anoCompletoI.clear();
		qtdCompletoI.clear();
		paisCompI.clear();
                idiomaCompI.clear();
                paisCompN.clear();
                idiomaCompN.clear();
                anoCompletoN.clear();
                anoCompletoI.clear();
                duvidaCompN.clear();
		duvidaCompIN.clear();
                hashCompN.clear();
                hashCompI.clear();          // Variaveis a Serem Lidas nos Aqruivos XML
                        
		String anoPublicacao;
		String natureza;
		String idioma = "";
                String pais = "Nao Informado";
		String tipo = "";
                String nome = "";           // Variaveis a Serem Lidas nos Aqruivos XML
                String ano = "";
                
		int i = 0;
                String hash = "";
		
		
		
		NodeList nl = elem.getElementsByTagName("DADOS-BASICOS-DO-LIVRO");
				
                while((product = (Element) nl.item(i++)) != null){
			
			anoPublicacao=product.getAttribute("ANO");
			anoPublicacao=(anoPublicacao.equals(""))?"1900":anoPublicacao;
			natureza = product.getAttribute("NATUREZA");
			idioma = product.getAttribute("IDIOMA");
                        pais = product.getAttribute("PAIS-DE-PUBLICACAO");
                        nome = product.getAttribute("TITULO-DO-LIVRO");
                        ano = product.getAttribute("ANO");
                        hash = MD5.getHash(nome);
                        nome = nome.replaceAll("[^\\p{L}\\p{N} ]", "");
                        nome = trata(nome);
                        
		//	if(tipo.equals("LIVRO_PUBLICADO")){
                                if (pais.equals("Brasil"))//completo nacional
				{
                                    if(idioma.equals("Português"))
                                    {                  
                                                paisCompN.add(pais);
                                                idiomaCompN.add(idioma);
                                                anoCompletoN.add(ano);
						anoCompletoN.add(anoPublicacao);
						qtdCompletoN.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                nomeCompN.add(nome);
                                                hashCompN.add(hash);
                                                duvidaCompN.add(0);

                                    }
                                    else
                                    {
                                                paisCompN.add(pais);
                                                idiomaCompN.add(idioma);
                                                anoCompletoN.add(ano);
						anoCompletoN.add(anoPublicacao);
						qtdCompletoN.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                nomeCompN.add(nome); 
                                                hashCompN.add(hash);
                                                duvidaCompN.add(1);  
                                    }
				}   
				else{//completo internacional
                                              	idiomaCompI.add(idioma);
                                                paisCompI.add(pais);
                                                anoCompletoI.add(ano);
						anoCompletoI.add(anoPublicacao);
						qtdCompletoI.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                nomeCompI.add(nome);
                                                hashCompI.add(hash);
                                                duvidaCompIN.add(0);
				}
                }
                
                                
		for (i=0;i<qtdCompletoN.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdCompletoN.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdCompletoN.get(i)).getQtd(),(String)idiomaCompN.get(i),(String)paisCompN.get(i),(String)nomeCompN.get(i)
                                ,(int)duvidaCompN.get(i),(String)hashCompN.get(i)));
                }

		researcherResume.setLivroNac((Vector)production.clone());
		production.clear();        
		
                for (i=0;i<qtdCompletoI.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdCompletoI.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdCompletoI.get(i)).getQtd(),idiomaCompI.get(i),paisCompI.get(i),(String)nomeCompI.get(i)
                                ,(int)duvidaCompIN.get(i),(String)hashCompI.get(i)));
		}//,(String)anoI.get(i)
		researcherResume.setLivroInt((Vector)production.clone());
		production.clear();  
                
        }
    /////////////////////////////////////////////////////////////CAPITULOS ////////////////////////////////////////////////////////////////////////
    
    
        public void setCapitulos() throws Exception{
        	Element product;
		Vector production = new Vector();
                
                
		anoResumoN.clear();
		qtdResumoN.clear();
		anoResumoI.clear();
		qtdResumoI.clear();
                paisResI.clear();
                idiomaResI.clear();
                paisResN.clear();;
                idiomaResN.clear();;
                nomeCompN.clear();;
                nomeCompI.clear();;
                duvidaCompN.clear();
		duvidaCompIN.clear();
                hashCompN.clear();
                hashCompI.clear();

		
		String anoPublicacao;
		String natureza;
		String idioma = "";
                String pais = "";
                String nome = "";
                String ano = "";
                
		int i = 0;
		String hash = "";
		
		
		
		NodeList nl = elem.getElementsByTagName("DADOS-BASICOS-DO-CAPITULO");
		
                while((product = (Element) nl.item(i++)) != null){
			
			anoPublicacao=product.getAttribute("ANO");
                        ano = anoPublicacao;
			anoPublicacao=(anoPublicacao.equals(""))?"1900":anoPublicacao;
			natureza = product.getAttribute("NATUREZA");
			idioma = product.getAttribute("IDIOMA");
                        pais = product.getAttribute("PAIS-DE-PUBLICACAO");
                        nome = product.getAttribute("TITULO-DO-CAPITULO-DO-LIVRO");
                        hash = MD5.getHash(nome);
                        nome = nome.replaceAll("[^\\p{L}\\p{N} ]", "");
                        nome = trata(nome);
                        
                                if (pais.equals("Brasil"))//Capitulos nacional
				{                                 
                                                paisResN.add(pais);
                                                idiomaResN.add(idioma);
						anoResumoN.add(anoPublicacao);
						qtdResumoN.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                hashCompN.add(hash);
                                                nomeCompN.add(nome);
                                 
                                    if(idioma.equals("Português"))
                                    {					
                                        duvidaCompN.add(0);
                                    }
                                    else
                                    {
                                        duvidaCompN.add(1);
                                    }

				}   
				else{// Capitulos internacional
			
                                              	idiomaResI.add(idioma);
                                                paisResI.add(pais);
						anoResumoI.add(anoPublicacao);
						qtdResumoI.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                nomeCompI.add(nome);
                                                hashCompI.add(hash);
                                                duvidaCompIN.add(0);
                                }
                }
	    
                
                for (i=0;i<qtdResumoI.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdResumoI.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdResumoI.get(i)).getQtd(),idiomaResI.get(i),paisResI.get(i),(String)nomeCompI.get(i)
                                ,(int)duvidaCompIN.get(i),(String)hashCompI.get(i)));
                }//anoResumoI.get(i);
		researcherResume.setCapitulosInt((Vector)production.clone());
		production.clear();
             
		for (i=0;i<qtdResumoN.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdResumoN.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdResumoN.get(i)).getQtd(),idiomaResN.get(i),paisResN.get(i),(String)nomeCompN.get(i)
                                ,(int)duvidaCompN.get(i),(String)hashCompN.get(i)));
		}//anoResumoN.get(i);
		researcherResume.setCapitulosNac((Vector)production.clone());
		production.clear();       
                
        }
    
    
    ///////////////////////////////////////////////////////////  ARTIGOS ///////////////////////////////////////////////////////////////////////////
        
	public void setArtigosPeriodicos() throws Exception {
		Element product;
		Vector production = new Vector();
		
		anoCompletoN.clear();
		qtdCompletoN.clear();
		anoCompletoI.clear();
		qtdCompletoI.clear();
		paisCompI.clear();
                idiomaCompI.clear();
                paisCompN.clear();
                idiomaCompN.clear();
                nomeCompN.clear();
                nomeCompI.clear();
                duvidaCompN.clear();
		duvidaCompIN.clear();
                hashCompN.clear();
                hashCompI.clear();

                
                
		Vector anoResumoN = new Vector();
		Vector qtdResumoN = new Vector();
		Vector anoResumoI = new Vector();
		Vector qtdResumoI = new Vector();
                Vector<String> paisResI = new Vector();
                Vector<String> idiomaResI = new Vector();
                Vector<String> paisResN = new Vector();
                Vector<String> idiomaResN = new Vector();
                Vector nomeResN = new Vector();
                Vector nomeResI = new Vector();
                Vector duvidaResN = new Vector();
		Vector duvidaResIN = new Vector();
                Vector hashResN = new Vector();
                Vector hashResI = new Vector();


		
		String anoPublicacao;
		String natureza;
		String idioma = "";
                String pais = "";
		String nome = "";
                
		int i = 0;
		String hash = "";
		
		
		NodeList nl = elem.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO");
		
		while((product = (Element) nl.item(i++)) != null){
			
			anoPublicacao=product.getAttribute("ANO-DO-ARTIGO");
			anoPublicacao=(anoPublicacao.equals(""))?"1900":anoPublicacao;
			natureza = product.getAttribute("NATUREZA");
			idioma = product.getAttribute("IDIOMA");
                        pais = product.getAttribute("PAIS-DE-PUBLICACAO");
			nome = product.getAttribute("TITULO-DO-ARTIGO");
			hash = MD5.getHash(nome);
                        nome = nome.replaceAll("[^\\p{L}\\p{N} ]", "");
                        nome = trata(nome);
                        
                        if (natureza.equals("COMPLETO")){
                                if (pais.equals("Brasil"))//completo nacional
				{
                                                paisCompN.add(pais);
                                                idiomaCompN.add(idioma);
						anoCompletoN.add(anoPublicacao);
						qtdCompletoN.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                nomeCompN.add(nome);
                                                hashCompN.add(hash);
                                    
                                    if(idioma.equals("Português"))
                                    {					
                                        duvidaCompN.add(0);
                                    }
                                    else
                                    {
                                        duvidaCompN.add(1);
                                    }
				 }   
				else{//completo internacional
                                                anoResumoI.add(anoPublicacao);
						idiomaCompI.add(idioma);
                                                paisCompI.add(pais);
						anoCompletoI.add(anoPublicacao);
						qtdCompletoI.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                nomeCompI.add(nome);
                                                hashCompI.add(hash);
                                                duvidaCompIN.add(0);
                                }
                        }        
			else //Resumo
			{
                         	if(idioma.equals("Brasil")) //resumo nacional
				{
                                                paisResN.add(pais);
                                                idiomaResN.add(idioma);
						anoResumoN.add(anoPublicacao);
						qtdResumoN.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                nomeResN.add(nome);
                                                hashResN.add(hash);
                                                                                                
                                                
                                    if(idioma.equals("Português"))
                                    {					
                                        duvidaCompN.add(0);
                                    }
                                    else
                                    {
                                        duvidaCompN.add(1);
                                    }
				}
				else{
						anoResumoI.add(anoPublicacao);
						idiomaResI.add(idioma);
                                                paisResI.add(pais);
                                                qtdResumoI.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                                nomeResI.add(nome);
                                                hashResI.add(hash);
                                                duvidaResIN.add(0);
				}

			}	   
		} 
                                
		for (i=0;i<qtdCompletoI.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdCompletoI.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdCompletoI.get(i)).getQtd(),idiomaCompI.get(i),paisCompI.get(i),(String)nomeCompI.get(i)
                                ,(int)duvidaCompIN.get(i),(String)hashCompI.get(i)));
		}//anoCompletoI.get(i);
		researcherResume.setArtigosCompletoInternacional((Vector)production.clone());
		production.clear();        
		
		for (i=0;i<qtdCompletoN.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdCompletoN.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdCompletoN.get(i)).getQtd(),idiomaCompN.get(i),paisCompN.get(i),(String)nomeCompN.get(i)
                                ,(int)duvidaCompN.get(i),(String)hashCompN.get(i)));
		}//anoCompletoN.get(i);
		researcherResume.setArtigosCompletoNacional((Vector)production.clone());
		production.clear();        
		
		for (i=0;i<qtdResumoI.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdResumoI.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdResumoI.get(i)).getQtd(),idiomaResI.get(i),paisResI.get(i),(String)nomeResI.get(i)
                                ,(int)duvidaResIN.get(i),(String)hashResI.get(i)));
		}//anoResumoI.get(i);
		researcherResume.setArtigosResumoInternacional((Vector)production.clone());
		production.clear();        
		
		for (i=0;i<qtdResumoN.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdResumoN.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdResumoN.get(i)).getQtd(),idiomaResN.get(i),paisResN.get(i),(String)nomeResN.get(i)
                                 ,(int)duvidaResIN.get(i),(String)hashResI.get(i)));
		}//anoResumoN.get(i);
		researcherResume.setArtigosResumoNacional(production);
	}
        
        
        /////////////////////////////////////////////////////// TRABALHOS EM EVENTOS - CONBRESSOSS ???????????????????????///////////////////////////////////////
        
        	public void setTrabalhos() throws Exception {
		Element product;
		Vector production = new Vector();
		
		Vector anoCompletoN = new Vector();
		Vector qtdCompletoN = new Vector();
		Vector anoCompletoI = new Vector();
		Vector qtdCompletoI = new Vector();
		Vector<String> paisCompI = new Vector();
                Vector<String> idiomaCompI = new Vector();
                Vector<String> paisCompN = new Vector();
                Vector<String> idiomaCompN = new Vector();
                Vector nomeCompN = new Vector();
                Vector nomeCompI = new Vector();
                Vector duvidaCompN = new Vector();
		Vector duvidaCompIN = new Vector();
                Vector hashCompN = new Vector();
                Vector hashCompI = new Vector();
                
		Vector anoResumoN = new Vector();
		Vector qtdResumoN = new Vector();
		Vector anoResumoI = new Vector();
		Vector qtdResumoI = new Vector();
                Vector<String> paisResI = new Vector();
                Vector<String> idiomaResI = new Vector();
                Vector<String> paisResN = new Vector();
                Vector<String> idiomaResN = new Vector();
                Vector nomeResN = new Vector();
                Vector nomeResI = new Vector();
                Vector duvidaResN = new Vector();
		Vector duvidaResIN = new Vector();
                Vector hashResN = new Vector();
                Vector hashResI = new Vector();
		
		String anoPublicacao;
		String natureza;
		String idioma = "";
                String pais = "";
		String nome = "";
                
		int i = 0;
		String hash;
		
		
		NodeList nl = elem.getElementsByTagName("DADOS-BASICOS-DO-TRABALHO");
		
		while((product = (Element) nl.item(i++)) != null){
			
			anoPublicacao=product.getAttribute("ANO-DO-TRABALHO");
			anoPublicacao=(anoPublicacao.equals(""))?"1900":anoPublicacao;
			natureza = product.getAttribute("NATUREZA");
			idioma = product.getAttribute("IDIOMA");
                        pais = product.getAttribute("PAIS-DO-EVENTO");
                        nome = product.getAttribute("TITULO-DO-TRABALHO");
                        hash = MD5.getHash(nome);
                        nome = nome.replaceAll("[^\\p{L}\\p{N} ]", ""); 
                        nome = trata(nome);
                        
                        
			if (natureza.equals("COMPLETO")){
                                if (pais.equals("Brasil"))                                  //completo nacional
				{
                                     paisCompN.add(pais);
                                     idiomaCompN.add(idioma);
	              		     anoCompletoN.add(anoPublicacao);
			       	     qtdCompletoN.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                     nomeCompN.add(nome);
                                     hashCompN.add(hash);
                                     
                                    if(idioma.equals("Português"))
                                    {					
                                        duvidaCompN.add(0);
                                    }
                                    else
                                    {
                                        duvidaCompN.add(1);
                                    }
                                
                                }   
				else{                                                           //completo internacional
                      		     idiomaCompI.add(idioma);
                                     paisCompI.add(pais);
				     anoCompletoI.add(anoPublicacao);
				     qtdCompletoI.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                     nomeCompI.add(nome);
                                     hashCompI.add(hash);
                                     duvidaCompIN.add(0);
                                }
                        }  
                        else if(natureza.equals("RESUMO")) //Resumo
			{
                         	if(pais.equals("Brasil")) //resumo nacional
				{
                                    paisResN.add(pais);
                                    idiomaResN.add(idioma);
				    anoResumoN.add(anoPublicacao);
				    qtdResumoN.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                    nomeResN.add(nome);
                                    hashResN.add(hash);                                  
				
                                    if(idioma.equals("Português"))
                                    {					
                                        duvidaResN.add(0);
                                    }
                                    else
                                    {
                                        duvidaResN.add(1);
                                    }
                                }
				else{
					anoResumoI.add(anoPublicacao);
					idiomaResI.add(idioma);
                                        paisResI.add(pais);
                                        qtdResumoI.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                        nomeResI.add(nome);
                                        hashResI.add(hash);
                                        duvidaResIN.add(0);
                                        
				}
			}
                  }
                //////////////////////////////////////////////////////////// Trabalho Completo ////////////////////////////////////////////////////
                
		for (i=0;i<qtdCompletoI.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdCompletoI.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdCompletoI.get(i)).getQtd(), idiomaCompI.get(i), paisCompI.get(i),(String)nomeCompI.get(i)
                                ,(int)duvidaCompIN.get(i),(String)hashCompI.get(i)));
		}//anoCompletoI.get(i);
		researcherResume.setTrabalhoCompInt((Vector)production.clone());
		production.clear();        
		
		for (i=0;i<qtdCompletoN.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdCompletoN.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdCompletoN.get(i)).getQtd(),idiomaCompN.get(i),paisCompN.get(i),(String)nomeCompN.get(i)
                                ,(int)duvidaCompN.get(i),(String)hashCompN.get(i)));
		}//anoCompletoN.get(i);
		researcherResume.setTrabalhoCompNac((Vector)production.clone());
		production.clear();        
		
                //////////////////////////////////////////////////////////// Resumo Em Congresso /////////////////////////////////////////////////
                
		for (i=0;i<qtdResumoI.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdResumoI.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdResumoI.get(i)).getQtd(),idiomaResI.get(i),paisResI.get(i),(String)nomeResI.get(i)
                                ,(int)duvidaResIN.get(i),(String)hashResI.get(i)));
		}
		researcherResume.setTrabalhoResInt((Vector)production.clone());
		production.clear();        
		
		for (i=0;i<qtdResumoN.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdResumoN.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdResumoN.get(i)).getQtd(),idiomaResN.get(i),paisResN.get(i),(String)nomeResN.get(i)
                                ,(int)duvidaResN.get(i),(String)hashResN.get(i)));
		}
		researcherResume.setTrabalhoResNac(production);
                
	}

                //////////////////////////////////RESUMO EXPANDIDO /////////////////////////////////////////////////////////////
                
                public void setResumoExp() throws Exception {
		Element product;
		Vector production = new Vector();

                anoCompletoN.clear();
		qtdCompletoN.clear();
		anoCompletoI.clear();
		qtdCompletoI.clear();
                paisCompI.clear();
                idiomaCompI.clear();
                paisCompN.clear();
                idiomaCompN.clear();
		nomeCompN.clear();
                nomeCompI.clear();
                duvidaCompN.clear();
		duvidaCompIN.clear();
                hashCompN.clear();
                hashCompI.clear();
		
                
		String anoPublicacao;
		String natureza;
		String idioma = "";
                String pais = "";
                String nome = "";
		
		int i = 0;
		String hash = "";
		
		
		NodeList nl = elem.getElementsByTagName("DADOS-BASICOS-DO-TRABALHO");
		
		while((product = (Element) nl.item(i++)) != null){
			
			anoPublicacao=product.getAttribute("ANO-DO-TRABALHO");
			anoPublicacao=(anoPublicacao.equals(""))?"1900":anoPublicacao;
			natureza = product.getAttribute("NATUREZA");
			idioma = product.getAttribute("IDIOMA");
                        pais = product.getAttribute("PAIS-DO-EVENTO");
			nome = product.getAttribute("TITULO-DO-TRABALHO");
                        hash = MD5.getHash(nome);
                        nome = nome.replaceAll("[^\\p{L}\\p{N} ]", "");
                        nome = trata(nome);
                        
                        if(natureza.equals("RESUMO_EXPANDIDO")){
                             if(pais.equals("Brasil")) //resumo EXPANDIDO nacional
			      {
                                     paisCompN.add(pais);
                                     idiomaCompN.add(idioma);
				     anoCompletoN.add(anoPublicacao);
				     qtdCompletoN.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                     nomeCompN.add(nome);
                                     hashCompN.add(hash);
                                     
                                    if(idioma.equals("Português"))
                                    {					
                                        duvidaCompN.add(0);
                                    }
                                    else
                                    {
                                        duvidaCompN.add(1);
                                    }
				}
				else{                                                   // RESUMO EXPANDIDO INTERNACIONAL
					anoCompletoI.add(anoPublicacao);
					idiomaCompI.add(idioma);
                                        paisCompI.add(pais);
                                        qtdCompletoI.add(new CurriculumXmlReader.Qtd(Integer.parseInt(anoPublicacao)));
                                        nomeCompI.add(nome);
                                        hashCompI.add(hash);
                                        duvidaCompIN.add(0);
                             }
		       }
            }
       
                for (i=0;i<qtdCompletoI.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdCompletoI.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdCompletoI.get(i)).getQtd(),idiomaCompI.get(i),paisCompI.get(i),(String)nomeCompI.get(i)
                                ,(int)duvidaCompIN.get(i),(String)hashCompI.get(i)));
		}
		researcherResume.setTrabalhoResExpInt((Vector)production.clone());
		production.clear();        
		
		for (i=0;i<qtdCompletoN.size();i++){
			production.add(new Producao(((CurriculumXmlReader.Qtd) qtdCompletoN.get(i)).getAno(), ((CurriculumXmlReader.Qtd) qtdCompletoN.get(i)).getQtd(),idiomaCompN.get(i),paisCompN.get(i),(String)nomeCompN.get(i)
                                ,(int)duvidaCompN.get(i),(String)hashCompN.get(i)));
		}
		researcherResume.setTrabalhoResExpNac(production);
	}  
                
                
//////////////////////////////////////////////////////////// ????????????????????///////////////////////////////////////////////////////////////	
	
	private String getChildTagAttribute( Element elem, String tagName, String attributeName ) throws Exception {
		NodeList children = elem.getElementsByTagName( tagName );
		if( children == null ) 
			return "";
		Element child = (Element) children.item(0);
		
		if( child == null ) 
			return "";
		
		return child.getAttribute(attributeName);
	}
	
	private class Qtd{
		private int qtd=1;
		private int ano;
		
		public Qtd(int ano){
			this.ano = ano;
		}
		public int getQtd(){
			return qtd;
		}
		public int getAno(){
			return ano;
		}
		public void setQtd(int qtd){
			this.qtd = qtd;
		}
		public void incQtd(){
			qtd++;
		}
	}
	
	private class QtdForm{
		private int qtd=1;
		private char codNivel;
		
		public QtdForm(char codNivel){
			this.codNivel = codNivel;
		}
		public char getCodNivel() {
			return codNivel;
		}
		public void setCodNivel(char codNivel) {
			this.codNivel = codNivel;
		}
		public int getQtd() {
			return qtd;
		}
		public void setQtd(int qtd) {
			this.qtd = qtd;
		}
		public void incQtd(){
			qtd++;
		}
    }
    public String trata (String passa){  
        passa = passa.replaceAll("[ÂÀÁÄÃ]","A");  
        passa = passa.replaceAll("[âãàáä]","a");  
        passa = passa.replaceAll("[ÊÈÉË]","E");  
        passa = passa.replaceAll("[êèéë]","e");  
        passa = passa.replaceAll("ÎÍÌÏ","I");  
        passa = passa.replaceAll("îíìïí","i");  
        passa = passa.replaceAll("[ÔÕÒÓÖ]","O");  
        passa = passa.replaceAll("[ôõòóö]","o");  
        passa = passa.replaceAll("[ÛÙÚÜ]","U");  
        passa = passa.replaceAll("[ûúùü]","u");  
        passa = passa.replaceAll("Ç","C");  
        passa = passa.replaceAll("ç","c");   
        passa = passa.replaceAll("[ýÿ]","y");  
        passa = passa.replaceAll("Ý","Y");  
        passa = passa.replaceAll("ñ","n");  
        passa = passa.replaceAll("Ñ","N");   
      return passa;  
    }
}


 * 
 * 
 * 
 */