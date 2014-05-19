package DataAcess;
import dataCon.DataSet;
import java.sql.SQLException;
import dataCon.*;
/**
 * <p>Title: DBContatos</p>
 * <p>Description: Um DataSet especializado em contatos de um cliente. 
 * Este DataSet pode ser usado como um Detail 
 * em rela��o Master/Detail com um DBClientes.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @author M�rcio Balian
 * @author Rog�rio Fiuza
 * @version 1.0
 * @deprecated <p>devido ao baixo desempenho de sucessivas querys (uma para cada pesquisador)
 * esta classe n�o mais ser� usada em sim a DBResearchersProductions.</p>
 */

public class DBContatos extends DataSet{
    /**
     * Cria o objeto j� com todos os dados de produ��o do pesquisador 
     * informado.
     * @param db um banco de dados j� conectado
     * @param idPesquisador a chave estrangeira da tabela producao (keypesquisador)
     */
	public DBContatos(DataBase db, int idCliente)  {
		super(db, "SELECT * " +
				  "FROM CONTATOS " +
				  "WHERE KEYCLIENTE = " + idCliente);
	}
	 /**
	 * @param idPesquisador a chave estrangeira da tabela producao (keypesquisador)
	 * @throws SQLException
	 */
	public void refresh(int idCliente) throws SQLException{
		select("SELECT * " +
			   "FROM CONTATOS " +
			   "WHERE KEYCLIENTE = " + idCliente);
	}
	/**
	 * @return o campo CONTATOS.TIPO
	 * @throws SQLException
	 */
	public String getTipoContato() throws SQLException{
		return getResultSet().getString("TIPO");
	}
 	/**
	 * @return o campo CONTATO.VALOR
	 * @throws SQLException
	 */	
	public String getValor() throws SQLException{
		return getResultSet().getString("VALOR"); 
	}
}
