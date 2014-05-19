package DataAcess;
import dataCon.DataSet;
import java.sql.SQLException;
import dataCon.*;
/**
 * <p>Title: DBClientes</p>
 * <p>Description: Um DataSet especializado em clientes. Este DataSet � a
 * parte Master de uma rela��o Master/Detail com os contatos de cada cliente.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @author M�rcio Balian
 * @author Rog�rio Fiuza
 * @version 1.0
 */

public class DBCliente extends DataSet{
         /**
	     * DataSet Datail da rela��o Master/Detail.
	     */
	   DBContatos contatos;
	   /**
	    * Cria o objeto j� com todos os pesquisadores.
	    * @param db um banco de dados j� conectado
	    */	
		public DBCliente(DataBase db)throws SQLException {
			super(db, "SELECT * FOM CLIENTES");
			contatos = new DBContatos(db,getID());
		}
		/**
		 * @return o DataSet Detail desta rela��o Master/Detail
		 */
		public DBContatos getContatos() {
			return contatos;
		}
		/**
		 * Sobrescrita do m�todo que far� com que o prodData sempre reflita os 
		 * dados de produ��o do pesquisador atualmente selecionado (ou seja o pesquisador
		 * do registro atualmente selecionado).
		 */
		public void updateSlave()throws SQLException {
			if (!getResultSet().isBeforeFirst()){
				contatos.refresh(getID());
			}  		   
		}
		/**
		 * @return o campo CLIENTE.IDCLIENTE
		 * @throws SQLException
		 */		
		public int getID() throws SQLException{
			return getResultSet().getInt("IDCLIENTE");	
		}
		/**
		 * @return o campo CLIENTE.IDCLIENTE
		 * @throws SQLException
		 */		
		public String getNome() throws SQLException{
			return getResultSet().getString("NOME");	
		}
		/**
		 * @return o campo CLIENTE.IDCLIENTE
		 * @throws SQLException
		 */		
		public String getCNPJ() throws SQLException{
			return getResultSet().getString("CNPJ");	
		}
		
}
