package starport.xmlImport;

public class Producao {
        private String idioma;
	private int ano;
	private int quantidade;
        private String pais;
        private String hash;
        private String nome;
        private int duvida;
        private String natureza;

	public Producao (int ano,String idioma,String pais,String nome,String natureza,int duvida,String hash) {
		this.ano = ano;
	//	this.quantidade = quantidade;
                this.idioma = idioma;
                this.pais = pais;
                this.natureza = natureza;
                this.hash = hash;
                this.nome = nome;
                this.duvida = duvida;
        }
        
        public Producao(int ano, int quantidade) {
            this.ano = ano;
            this.quantidade = quantidade;
        }
        
        public int getDuvida() {
            return duvida;
        }

        public String getNome() {
            return nome;
        }
        
        public String getNatureza() {
            return natureza;
        }

        public String getHash() {
            return hash;
        }
        
	public int getAno() {
		return ano;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getIdioma() {
		return idioma;
	}
       	public String getPais() {
		return pais;
	}
}
