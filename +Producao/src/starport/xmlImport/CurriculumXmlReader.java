package starport.xmlImport;

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
import java.sql.Blob;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class CurriculumXmlReader
{
        Vector <Producao> production = new Vector(); 
	private Curriculum researcherResume;
	private Element elem;
	
	public CurriculumXmlReader()throws Exception
	{
	}
        
        public Curriculum getXmlRemote(String Blob) throws Exception{
          try
          {
              // Find XML ENCODE
              InputStreamReader r =  new InputStreamReader(new ByteArrayInputStream(Blob.getBytes()));
              String encode = r.getEncoding();

              // GET XML
              InputSource is = new InputSource(new ByteArrayInputStream(Blob.getBytes()));
              // Set XML Encode

              is.setEncoding(encode);                                                     
              this.elem = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is).getDocumentElement(); //GET XML Element

            } catch (Exception e) {
                    System.out.println("Arquivo vazio ou mau formado" + e.toString());
            }
          
            return getResearcherResume();
        }
        public Curriculum getXmlLocal(String xmlPathname) throws Exception{
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
		}
                
               return getResearcherResume();
        }
                
	public Curriculum getResearcherResume() throws Exception{
            
                researcherResume = new Curriculum();
                
                researcherResume.clearVet();
		String result[] = getInstitutionNameAndAcronym();
		researcherResume.setInstitutionName(result[0]);        //nome  da instituicao
		researcherResume.setAcronymInstitutionName(result[1]);   //sigla da instituicao
		researcherResume.setName(getResearcherName());
		researcherResume.setFormacao(getFormacao());
                
                setData();
                setUnidadeAcad();
                setAreas();
                
                // PRODUCOES
                
                researcherResume.setTrabalhos(getProd("DADOS-BASICOS-DO-TRABALHO","ANO-DO-TRABALHO","PAIS-DO-EVENTO","TITULO-DO-TRABALHO","PAIS-DE-PUBLICACAO"));

                researcherResume.setArtigos(getProd("DADOS-BASICOS-DO-ARTIGO","ANO-DO-ARTIGO","PAIS-DE-PUBLICACAO","TITULO-DO-ARTIGO","LOCAL-DE-PUBLICACAO"));
                
                researcherResume.setLivros(getProd("DADOS-BASICOS-DO-LIVRO","ANO","PAIS-DE-PUBLICACAO","TITULO-DO-LIVRO","PAIS-DE-PUBLICACAO"));
                
                researcherResume.setCapitulos(getProd("DADOS-BASICOS-DO-CAPITULO","ANO","PAIS-DE-PUBLICACAO","TITULO-DO-CAPITULO-DO-LIVRO","PAIS-DE-PUBLICACAO"));
                
                
                // ORIENTACOESS
                
                researcherResume.setOrientacao(getProd("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO","ANO","PAIS","TITULO","NOME-DA-INSTITUICAO"));
                
                researcherResume.setOrientacao(getProd("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO","ANO","PAIS","TITULO","NOME-DA-INSTITUICAO"));
                
                researcherResume.setOrientacao(getProd("OUTRAS-ORIENTACOES-CONCLUIDAS","ANO","PAIS","TITULO","NOME-DA-INSTITUICAO"));

                //PARTICIPACOESSS
                   
		
		researcherResume.setParticipacao(getProd("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-MESTRADO","ANO","PAIS","TITULO","NOME-DA-INSTITUICAO"));
                
                researcherResume.setParticipacao(getProd("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-DOUTORADO","ANO","PAIS","TITULO","NOME-DA-INSTITUICAO"));
                
                researcherResume.setParticipacao(getProd("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-GRADUACAO","ANO","PAIS","TITULO","NOME-DA-INSTITUICAO"));
                
                researcherResume.setParticipacao(getProd("DADOS-BASICOS-DA-PARTICIPACAO-EM-BANCA-DE-APERFEICOAMENTO-ESPECIALIZACAO","ANO","PAIS","TITULO","NOME-DA-INSTITUICAO"));
                
                researcherResume.setParticipacao(getProd("DADOS-BASICOS-DA-BANCA-JULGADORA-PARA-CONCURSO-PUBLICO","ANO","PAIS","TITULO","NOME-DA-INSTITUICAO"));

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
				qtd.add(new QtdForm(cod.charValue()));
			}
			else
			{
				((QtdForm) qtd.get(index)).incQtd();
			}
		}

		for (int i=0;i<qtd.size();i++){
			formation.add(new Formacao(((QtdForm) qtd.get(i)).getCodNivel(), ((QtdForm) qtd.get(i)).getQtd()));
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
				qtd.add(new Qtd(Integer.parseInt(anoPublicacao)));
			}
			else
			{
				((Qtd) qtd.get(index)).incQtd();
			}
		}
		
		for (i=0;i<qtd.size();i++){
			production.add(new Producao(((Qtd) qtd.get(i)).getAno(), ((Qtd) qtd.get(i)).getQtd()));
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
        
    public Vector getProd(String tag , String anoTag, String paisTag , String tituloTag, String localTag) throws Exception{
            Element product;
            production.clear();

            String ano;
            String natureza = "NO";
            String local = "";            // Variaveis a Serem Lidas nos Aqruivos XML
            String idioma;
            String pais = "NO";
            String nome;
            
            int duvida = 0, i = 0;
            
            String hash = "";

            NodeList nl = elem.getElementsByTagName(tag);

            while((product = (Element) nl.item(i++)) != null){

              ano=product.getAttribute(anoTag);
              ano=(ano.equals(""))?"1":ano;
              
              idioma = product.getAttribute("IDIOMA");
              idioma = trata(idioma);
              idioma = idioma.replaceAll("[^\\p{L}\\p{N} ]", "");
              
              natureza = product.getAttribute("NATUREZA");
              local = product.getAttribute(localTag);
             
              pais = product.getAttribute(paisTag);
              pais = trata(pais);
              pais = pais.replaceAll("[^\\p{L}\\p{N} ]", "");
              
              nome = product.getAttribute(tituloTag);
              nome = nome.replaceAll("[^\\p{L}\\p{N} ]", "");
              nome = trata(nome);
              
              hash = MD5.getHash(nome);
              
              if (pais.equals("Brasil"))//Capitulos nacional
                duvida = (idioma.equals("Português"))?0:1;

              production.add(new Producao(Integer.parseInt(ano), idioma, pais, nome, natureza,duvida,hash));

            }  
            return production;
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

