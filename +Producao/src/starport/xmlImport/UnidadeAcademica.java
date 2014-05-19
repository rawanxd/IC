/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starport.xmlImport;

/**
 *
 * @author Brawan
 */
public class UnidadeAcademica {
    public static int idUnidade;
    private String nome = "";
    private int ID;
    private String tipo = "";
        
        public UnidadeAcademica(String nome){
            this.nome = nome;
            this.ID = idUnidade++;
            this.tipo = "";
        }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
}
