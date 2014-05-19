package starport.xmlImport;

public class Formacao {
	public static final char ENSINO_FUNDAMENTAL_INCOMPLETO = 'A';
	public static final char ENSINO_FUNDAMENTAL = 'B';
	public static final char ENSINO_MEDIO = 'C';
	public static final char ESPECIALIZACAO_RESIDENCIA_MEDICA = 'D';
	public static final char MBA = 'E';
	public static final char CURSO_CURTA_DURACAO = 'F';
	public static final char APERFEICOAMENTO = 'X';
	public static final char GRADUACAO = '1';
	public static final char ESPECIALIZACAO = '2';
	public static final char MESTRADO = '3';
	public static final char DOUTORADO = '4';
	public static final char POS_DOUTORADO = '5';
	public static final char LIVRE_DOCENCIA = '6';
	public static final char ENSINO_PROFISSIONAL_NIVEL_TECNICO = '7';
	public static final char EXTENSAO_UNIVERSITARIA = '8';
	public static final char MESTRADO_PROFISSIONALIZANTE = '9';
	
	
	private char codNivel;
	private byte seqOrdNivel;
	private String nome;
	private int quantidade;
	
	public Formacao(char codNivel, int qtd) {
		this.codNivel = codNivel;
		this.quantidade = qtd;
		switch (codNivel) {
		case 'A': 
			nome = "ENSINO FUNDAMENTAL INCOMPLETO";
			seqOrdNivel = 0;		
			break;
		case 'B': 
			nome = "ENSINO FUNDAMENTAL";
			seqOrdNivel = 1;		
			break;
		case 'C': 
			nome = "ENSINO MEDIO";
			seqOrdNivel = 2;		
			break;
		case 'D': 
			nome = "ESPECIALIZACAO RESIDENCIA MEDICA";
			seqOrdNivel = 5;		
			break;
		case 'E': 
			nome = "MBA";
			seqOrdNivel = 7;		
			break;
		case 'F': 
			nome = "CURSO CURTA DURACAO";
			seqOrdNivel = 0;		
			break;
		case 'X': 
			nome = "APERFEICOAMENTO";
			seqOrdNivel = 0;		
			break;
		case '1': 
			nome = "GRADUACAO";
			seqOrdNivel = 4;		
			break;
		case '2': 
			nome = "ESPECIALIZACAO";
			seqOrdNivel = 6;		
			break;
		case '3': 
			nome = "MESTRADO";
			seqOrdNivel = 9;		
			break;
		case '4': 
			nome = "DOUTORADO";
			seqOrdNivel = 10;		
			break;
		case '5': 
			nome = "POS_DOUTORADO";
			seqOrdNivel = 12;		
			break;
		case '6': 
			nome = "LIVRE_DOCENCIA";
			seqOrdNivel = 11;		
			break;
		case '7': 
			nome = "ENSINO PROFISSIONAL NIVEL TECNICO";
			seqOrdNivel = 3;		
			break;
		case '8': 
			nome = "EXTENSAO UNIVERSITARIA";
			seqOrdNivel = 0;		
			break;
		case '9': 
			nome = "MESTRADO PROFISSIONALIZANTE";
			seqOrdNivel = 8;		
			break;
		default:
			nome = "FUNDAMENTAL-MEDIO";
			seqOrdNivel = 0;		
			
			break;
		}
	}

	public char getCodNivel() {
		return codNivel;
	}

	public String getNome() {
		return nome;
	}

	public byte getSeqOrdNivel() {
		return seqOrdNivel;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
