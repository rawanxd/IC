package starport.xmlImport; 

import java.util.Vector;

public class Curriculum{
    private String xmlPathname;
    private String name;

    private int idResearcher;        
    private int idIes;
    private int idUni;
    private String institutionName="";
    private String acronymInstitutionName;
    private String unidadeAcademica;
    private String area;
    private String subArea;
    private Vector formacao;


    private Vector orientacaoDoutorado ;
    private Vector orientacaoMestrado;        
    private Vector orientacaoEspecializacao ;        
    private Vector orientacaoGraduacao ;
    private Vector orientacaoIniciacao ;
    private Vector participacaoBancaGraduacao ;
    private Vector participacaoBancaMestrado ;
    private Vector participacaoBancaDoutorado ;
    private Vector participacaoBancaAperfeicoamentoEspecializacao ;
    private Vector participacaoBancaConcursoPublico ;  
    private Vector capitulosNac ;
    private Vector capitulosInt ;
    private Vector livroNac ;
    private Vector livroInt ;
    private Vector artigosCompletoInternacional ;
    private Vector artigosCompletoNacional ;
    private Vector artigosResumoInternacional ;
    private Vector artigosResumoNacional ;
    private Vector trabalhoCompNac ;
    private Vector trabalhoCompInt ;
    private Vector trabalhoResNac ;
    private Vector trabalhoResInt ;
    private Vector trabalhoResExpNac ;
    private Vector trabalhoResExpInt ;
    private Vector idioma ;
    private String date ;
    
    public Curriculum(){
        formacao = new Vector();
        orientacaoDoutorado = new Vector();
        orientacaoMestrado = new Vector();        
        orientacaoEspecializacao = new Vector();        
        orientacaoGraduacao = new Vector();
        orientacaoIniciacao = new Vector();
        participacaoBancaGraduacao = new Vector();
        participacaoBancaMestrado = new Vector();
        participacaoBancaDoutorado = new Vector();
        participacaoBancaAperfeicoamentoEspecializacao = new Vector();
        participacaoBancaConcursoPublico = new Vector();  
        capitulosNac = new Vector();
        capitulosInt = new Vector();
        livroNac = new Vector();
        livroInt = new Vector();
        artigosCompletoInternacional = new Vector();
        artigosCompletoNacional = new Vector();
        artigosResumoInternacional = new Vector();
        artigosResumoNacional = new Vector();
        trabalhoCompNac = new Vector();
        trabalhoCompInt = new Vector();
        trabalhoResNac = new Vector();
        trabalhoResInt = new Vector();
        trabalhoResExpNac = new Vector();
        trabalhoResExpInt = new Vector();
        idioma = new Vector();
        date  = "";
    }

    public void addFormacao(Formacao formacao){
            if (this.formacao == null){
                    this.formacao = new Vector();
            }
            this.formacao.add(formacao);
    }

    public void addProducaoCapitulosNac(Producao producao){
    if (capitulosNac == null){
            capitulosNac = new Vector();
    }
    capitulosNac.add(producao);
    }
    public void addProducaoCapitulosInt(Producao producao){
    if (capitulosInt == null){
            capitulosInt = new Vector();
    }
    capitulosInt.add(producao);
    }
    public void addProducaoIdioma(Producao producao){
    if (idioma == null){
            idioma = new Vector();
    }
    idioma.add(producao);
    }

    public void addProducaoLivrosNac(Producao producao){
    	if (livroNac == null){
    		livroNac = new Vector();
    	}
    	livroNac.add(producao);
    }

    public void addProducaoLivrosInt(Producao producao){
    	if (livroInt == null){
    		livroInt = new Vector();
    	}
    	livroInt.add(producao);
    }
    
    public void addProducaoArtigosCompletoInternacional(Producao producao){
    	if (artigosCompletoInternacional == null){
    		artigosCompletoInternacional = new Vector();
    	}
    	artigosCompletoInternacional.add(producao);
    }
           
    public void addProducaoArtigosCompletoNacional(Producao producao){
    	if (artigosCompletoNacional == null){
    		artigosCompletoNacional = new Vector();
    	}
    	artigosCompletoNacional.add(producao);
    }
    
    public void addProducaoArtigosResumoInternacional(Producao producao){
    	if (artigosResumoInternacional == null){
    		artigosResumoInternacional = new Vector();
    	}
    	artigosResumoInternacional.add(producao);
    }
    
    public void addProducaoArtigosResumoNacional(Producao producao){
    	if (artigosResumoNacional == null){
    		artigosResumoNacional = new Vector();
    	}
    	artigosResumoNacional.add(producao);
    }
    
    public void addProducaoTrabalhoCompNacional(Producao producao){
    	if (trabalhoCompNac == null){
    		trabalhoCompNac = new Vector();
    	}
    	trabalhoCompNac.add(producao);
    }
    
    public void addProducaoTrabalhoCompInternacional(Producao producao){
    	if (trabalhoCompInt == null){
    		trabalhoCompInt = new Vector();
    	}
    	trabalhoCompInt.add(producao);
    }
    
    public void addProducaoTrabalhoResInternacional(Producao producao){
    	if (trabalhoResInt == null){
    		trabalhoResInt = new Vector();
    	}
    	trabalhoResInt.add(producao);
    }
    
    public void addProducaoTrabalhoResNacional(Producao producao){
    	if (trabalhoResNac == null){
    		trabalhoResNac = new Vector();
    	}
    	trabalhoResNac.add(producao);
    }
        
    public void addProducaoTrabalhoResExpInternacional(Producao producao){
    	if (trabalhoResExpInt == null){
    		trabalhoResExpInt = new Vector();
    	}
    	trabalhoResExpInt.add(producao);
    }
    
    public void addProducaoTrabalhoResExpNacional(Producao producao){
    	if (trabalhoResExpNac == null){
    		trabalhoResExpNac = new Vector();
    	}
    	trabalhoResExpNac.add(producao);
    }

    public String getAcronymInstitutionName() {
            return acronymInstitutionName;
    }

    public String getArea() {
        return area;
    }

    public String getSubArea() {
        return subArea;
    }

    public String getUnidadeAcademica() {
        return unidadeAcademica;
    }     

    public int getIdUni() {
        return idUni;
    }

    public Vector getArtigosCompletoInternacional() {
            return artigosCompletoInternacional;
    }

    public Vector getArtigosCompletoNacional() {
            return artigosCompletoNacional;
    }

    public Vector getArtigosResumoInternacional() {
            return artigosResumoInternacional;
    }

    public Vector getArtigosResumoNacional() {
            return artigosResumoNacional;
    }

    public Vector getOrientacaoDoutorado() {
        return orientacaoDoutorado;
    }

    public Vector getOrientacaoEspecializacao() {
        return orientacaoEspecializacao;
    }

    public Vector getOrientacaoGraduacao() {
        return orientacaoGraduacao;
    }

    public Vector getOrientacaoIniciacao() {
        return orientacaoIniciacao;
    }

    public Vector getOrientacaoMestrado() {
        return orientacaoMestrado;
    }

    public Vector getParticipacaoBancaAperfeicoamentoEspecializacao() {
        return participacaoBancaAperfeicoamentoEspecializacao;
    }

    public Vector getParticipacaoBancaConcursoPublico() {
        return participacaoBancaConcursoPublico;
    }

    public Vector getParticipacaoBancaDoutorado() {
        return participacaoBancaDoutorado;
    }

    public Vector getParticipacaoBancaGraduacao() {
        return participacaoBancaGraduacao;
    }

    public Vector getParticipacaoBancaMestrado() {
        return participacaoBancaMestrado;
    }

    public Vector getCapitulosNac() {
            return capitulosNac;
    }
    public Vector getCapitulosInt() {
            return capitulosInt;
    }
    public Vector getTrabalhoCompInt() {
            return trabalhoCompInt;
    }
    public Vector getTrabalhoCompNac() {
            return trabalhoCompNac;
    }
    public Vector getTrabalhoResInt() {
            return trabalhoResInt;
    }
    public Vector getTrabalhoResNac() {
            return trabalhoResNac;
    }
    public Vector getTrabalhoResExpInt() {
            return trabalhoResExpInt;
    }
    public Vector getTrabalhoResExpNac() {
            return trabalhoResExpNac;
    }
    public Vector getIdioma() {
            return idioma;
    }

     public String getDate() {
        return date;
     }

     public int getIdIes() {
            return idIes;
     }

    public int getIdResearcher() {
            return idResearcher;
    }

    public String getInstitutionName() {
            return institutionName;
    }

    public Vector getLivroNac() {
            return livroNac;
    }

    public Vector getLivroInt() {
            return livroInt;
    }
    public String getName() {
            return name;
    }

    public String getXmlPathname() {
            return xmlPathname;
    }

    public void setAcronymInstitutionName(String acronymInstitutionName) {
            this.acronymInstitutionName = acronymInstitutionName;
    }

    public void setIdIes(int idIes) {
            this.idIes = idIes;
    }

    public void setIdResearcher(int idResearcher) {
            this.idResearcher = idResearcher;
    }

    public void setInstitutionName(String institutionName) {
            this.institutionName = institutionName;
    }

    public void setUnidadeAcademica(String unidadeAcademica) {
        this.unidadeAcademica = unidadeAcademica;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public void setIdUni(int idUni) {
        this.idUni = idUni;
    }

    public void setName(String name) {
            this.name = name;
    }

    public void setXmlPathname(String xmlPathname) {
            this.xmlPathname = xmlPathname;
    }

    public void setArtigos(Vector<Producao> artigos) {
      
      for(int i = 0; i < artigos.size(); i++)
      {
        if(artigos.get(i).getPais().equals("Brasil"))
        {
          if(artigos.get(i).getNatureza().equals("COMPLETO"))
            artigosCompletoNacional.add(artigos.get(i));
          else
            artigosResumoNacional.add(artigos.get(i));
        }
        else
        {
          if(artigos.get(i).getNatureza().equals("COMPLETO"))
            artigosCompletoInternacional.add(artigos.get(i));
          else
            artigosResumoInternacional.add(artigos.get(i));
        }
      }

    }
    public void setCapitulos (Vector<Producao> capitulos) {

      for(int i = 0; i < capitulos.size(); i++)
      {
        String str = capitulos.get(i).getIdioma();
        if(str.equals("Portugus") || str.equals("Português") || str.equals("Portugues"))
          capitulosNac.add(capitulos.get(i));
        else
          capitulosInt.add(capitulos.get(i));
      }

    }

    public void setTrabalhos (Vector<Producao> trabalho) {

      for(int i = 0; i < trabalho.size(); i++)
      {
        if(trabalho.get(i).getPais().equals("Brasil"))
        {
          if(trabalho.get(i).getNatureza().equals("COMPLETO"))
            trabalhoCompNac.add(trabalho.get(i));
          else if(trabalho.get(i).getNatureza().equals("RESUMO"))
            trabalhoResNac.add(trabalho.get(i));
          else
            trabalhoResExpNac.add(trabalho.get(i));
        }
        else 
        {
          if(trabalho.get(i).getNatureza().equals("COMPLETO"))
            trabalhoCompInt.add(trabalho.get(i));
          else if(trabalho.get(i).getNatureza().equals("RESUMO")) 
            trabalhoResInt.add(trabalho.get(i));
          else
            trabalhoResExpInt.add(trabalho.get(i));
        }
      }

    }

    public void setIdioma (Vector idioma) {
            this.idioma = idioma;
    }

    public void setLivros (Vector <Producao> livro){ 

      for(int i = 0; i < livro.size(); i++)
      {
        String str = livro.get(i).getIdioma();
        if(str.equals("Portugus") || str.equals("Português") || str.equals("Portugues"))
          livroNac.add(livro.get(i));
        else
          livroInt.add(livro.get(i));
      }

    }

    public Vector getFormacao () {
            return formacao;
    }

    public void setFormacao (Vector formacao) {
            this.formacao = formacao;
    }

    public void setDate (String date) {
        this.date = date;
    }

    void setOrientacao(Vector <Producao> productions) {

      for(int i = 0; i < productions.size(); i++)
      {
        if(productions.get(i).getNatureza().equals(""))
          orientacaoDoutorado.addElement(productions.get(i));
        else if(productions.get(i).getNatureza().equals("TRABALHO_DE_CONCLUSAO_DE_CURSO_ESPECIALIZACAO"))
          orientacaoEspecializacao.add(productions.get(i));
        else if(productions.get(i).getNatureza().equals("TRABALHO_DE_CONCLUSAO_DE_CURSO_GRADUACAO"))
          orientacaoGraduacao.add(productions.get(i));
        else if(productions.get(i).getNatureza().equals("INICIACAO_CIENTIFICA"))
          orientacaoIniciacao.add(productions.get(i));
        else
          orientacaoMestrado.add(productions.get(i));
     }

    }

    void setParticipacao(Vector <Producao> productions) {

      for(int i = 0; i < productions.size(); i++)
      {
        if(productions.get(i).getNatureza().equals("Curso de aperfeiçoamento/especialização"))
          participacaoBancaAperfeicoamentoEspecializacao.add(productions.get(i));
        else if(productions.get(i).getNatureza().equals("Concurso público"))
          participacaoBancaConcursoPublico.add(productions.get(i));
        else if(productions.get(i).getNatureza().equals("Doutorado"))
          participacaoBancaDoutorado.add(productions.get(i));
        else if(productions.get(i).getNatureza().equals("Graduação"))
          participacaoBancaGraduacao.add(productions.get(i));
        else
          participacaoBancaMestrado.add(productions.get(i));
      }

    }
    void clearVet(){
      orientacaoDoutorado.clear();
      orientacaoEspecializacao.clear();        
      orientacaoGraduacao.clear();
      orientacaoIniciacao.clear();
      participacaoBancaGraduacao.clear();
      participacaoBancaMestrado.clear();
      participacaoBancaDoutorado.clear();
      participacaoBancaAperfeicoamentoEspecializacao.clear();
      participacaoBancaConcursoPublico.clear();  
      capitulosNac.clear();
      capitulosInt.clear();
      livroNac.clear();
      livroInt.clear();
      artigosCompletoInternacional.clear();
      artigosCompletoNacional.clear();
      artigosResumoInternacional.clear();
      artigosResumoNacional.clear();
      trabalhoCompNac.clear();
      trabalhoCompInt.clear();
      trabalhoResNac.clear();
      trabalhoResInt.clear();
      trabalhoResExpNac.clear();
      trabalhoResExpInt.clear();
      idioma.clear();
    }

}
