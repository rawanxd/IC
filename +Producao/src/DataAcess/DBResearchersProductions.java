package DataAcess;
import dataCon.DataSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import dataCon.*;
/**
 * <p>Title: DBResearchersProductions</p>
 * <p>Description: Um DataSet especializado em dados de produ��o de
 * um determinado pesquisador. Este DataSet pode ser usado como um Detail 
 * em rela��o Master/Detail com um DBResearcher.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @version 1.0
 */
public class DBResearchersProductions extends DataSet{
    /**
     * Cria o objeto j� com todos os dados de produ��o do pesquisador 
     * informado.
     * @param db um banco de dados j� conectado
     * @param idPesquisador a chave estrangeira da tabela producao (keypesquisador)
     */
	public DBResearchersProductions(DataBase db)  {
		super(db, "SELECT SUM(p.VALOR) AS VALOR, " +
				  "P.KEYPESQUISADOR, t.IDTIPOPRODUCAO " +
				  "FROM PRODUCAO p LEFT OUTER JOIN TIPOPRODUCAO t ON " +
				  " p.KEYTIPOPRODUCAO = t.IDTIPOPRODUCAO" +
				  " GROUP BY P.KEYPESQUISADOR, t.IDTIPOPRODUCAO" + 
				  " ORDER BY P.KEYPESQUISADOR, t.IDTIPOPRODUCAO");
	}
    /**
     * Retorna um tablea hash onde a chave � o id da produ��o (idtipoproducao) 
     * e o valor � a quantidade porduzida pelo pesquisador informado.
     * @param keyPesquisador o id do pesquiador que se deseja os dados de produ��o
     */
	public Hashtable getProductionSet(int keyPesquisador) throws SQLException{
		ResultSet rs = getResultSet();
		rs.beforeFirst();
		Hashtable ht = new Hashtable(31,1f);
		boolean find=false;
			while (rs.next()){
				if (rs.getInt(2)==keyPesquisador){
					ht.put(Integer.valueOf(rs.getInt(3)),Integer.valueOf(rs.getInt(1)));
					find = true;
				}
				else if (find){
					return ht;
				}
			};
		if (ht.isEmpty())
			return null;
		else return ht;
	}
}
