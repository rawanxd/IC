package DataAcess;
import dataCon.DataSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import starport.xmlImport.Formacao;

import dataCon.*;
/**
 * <p>Title: DBResearchersFormations</p>
 * <p>Description: Um DataSet especializado em dados da forma��o de
 * um determinado pesquisador. Este DataSet pode ser usado como um Detail 
 * em rela��o Master/Detail com um DBResearcher.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @version 1.0
 */
public class DBResearchersFormations extends DataSet{
    /**
     * Cria o objeto j� com todos os dados de produ��o do pesquisador 
     * informado.
     * @param db um banco de dados j� conectado
     * @param idPesquisador a chave estrangeira da tabela producao (keypesquisador)
     */
	public DBResearchersFormations(DataBase db)  {
		super(db, "SELECT r.idpesquisador, f.codNivel, p.quantidade " + 
				"FROM pesquisadorformacao p left join formacao f on" +
				" codNivel = keyformacao, pesquisador r " + 
				" where keypesquisador = idpesquisador " +
				" order by r.idpesquisador");
	}
	
    /**
     * Retorna um tablea hash onde a chave � o id da produ��o (idtipoproducao) 
     * e o valor � a quantidade porduzida pelo pesquisador informado.
     * @param keyPesquisador o id do pesquiador que se deseja os dados de produ��o
     */
	public Hashtable getFormationSet(int keyPesquisador) throws SQLException{
		ResultSet rs = getResultSet();
		rs.beforeFirst();
		Hashtable ht = new Hashtable(13,1.0f);
		boolean find=false;
			while (rs.next()){
				if (rs.getInt(1)==keyPesquisador){
					char aux = rs.getString(2).charAt(0);
					ht.put(new Character(aux),new Formacao(aux, rs.getInt(3)));
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
