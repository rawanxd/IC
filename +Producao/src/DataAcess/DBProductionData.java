package DataAcess;
import dataCon.DataSet;
import java.sql.SQLException;
import java.util.Calendar;

import dataCon.*;
/**
 * <p>Title: DBProductionData</p>
 * <p>Description: Um DataSet especializado em dados de produ��o de
 * um determinado pesquisador. Este DataSet pode ser usado como um Detail 
 * em rela��o Master/Detail com um DBResearcher.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @author M�rcio Balian
 * @author Rog�rio Fiuza
 * @version 1.0
 * @deprecated <p>devido ao baixo desempenho de sucessivas querys (uma para cada pesquisador)
 * esta classe n�o mais ser� usada em sim a DBResearchersProductions.</p>
 */
public class DBProductionData extends DataSet{
    /**
     * Cria o objeto j� com todos os dados de produ��o do pesquisador 
     * informado.
     * @param db um banco de dados j� conectado
     * @param idPesquisador a chave estrangeira da tabela producao (keypesquisador)
     */
	public DBProductionData(DataBase db, int idPesquisador)  {
		super(db, "SELECT SUM(p.VALOR) AS VALOR, " +
				  "t.IDTIPOPRODUCAO " +
				  "FROM PRODUCAO p RIGHT OUTER JOIN TIPOPRODUCAO t ON " +
				  " p.KEYTIPOPRODUCAO = t.IDTIPOPRODUCAO " +
				  "AND p.KEYPESQUISADOR = " + idPesquisador +
				  " GROUP BY t.IDTIPOPRODUCAO" + 
				  " ORDER BY t.NOME");
	}
	/**
	 * Atualiza a consulta sql. Se estiver usando esta classe como parte de uma
	 * rela��o Master/Detail, lembre-se de sobrescrever o m�todo DataSet.updateSlave()
	 * no DataSet Master, executando um refresh com o novo idPesquisador. e.g.:
	 * 
	 * 	public void updateSlave()throws SQLException {
	 *     prodData.refresh(getResearchId());
	 *  }   
	 *  
	 *  Lembrando que a sobrescrita deve ser implementada no DataSet MASTER nunca no
	 *  Detail.
	 * @param idPesquisador a chave estrangeira da tabela producao (keypesquisador)
	 * @throws SQLException
	 */
	public void refresh(int idPesquisador) throws SQLException{
		long init =Calendar.getInstance().getTimeInMillis();
		select("SELECT SUM(p.VALOR) AS VALOR, " +
				  "t.IDTIPOPRODUCAO " +
				  "FROM PRODUCAO p RIGHT OUTER JOIN TIPOPRODUCAO t ON " +
				  " p.KEYTIPOPRODUCAO = t.IDTIPOPRODUCAO " +
				  "AND p.KEYPESQUISADOR = " + idPesquisador +
				  " GROUP BY t.IDTIPOPRODUCAO" + 
				  " ORDER BY t.IDTIPOPRODUCAO");
		System.out.println(Calendar.getInstance().getTimeInMillis()-init);
	}
	/**
	 * @return o campo TIPOPRODUCAO.IDTIPOPRODUCAO
	 * @throws SQLException
	 */
	public int getProductionTypeId() throws SQLException{
		return getResultSet().getInt("IDTIPOPRODUCAO");
	}
 	/**
	 * @return o campo PRODUCAO.VALOR
	 * @throws SQLException
	 */	
	public int getValor() throws SQLException{
		return getResultSet().getInt("VALOR"); 
	}
}
