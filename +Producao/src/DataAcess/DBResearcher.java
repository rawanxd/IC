package DataAcess;
import dataCon.DataSet;
import java.sql.SQLException;
import java.util.Hashtable;

import dataCon.*;
/**
 * <p>Title: DBProductionData</p>
 * <p>Description: Um DataSet especializado em pesquisadores. Este DataSet � a
 * parte Master de uma rela��o Master/Detail com os dados de produ��o de
 * cada pesquisador.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @author M�rcio Balian
 * @author Rog�rio Fiuza
 * @version 1.0
 */
public class DBResearcher extends DataSet {
    /**
     * DataSet Datail da rela��o Master/Detail.
     */
	private static DBResearchersProductions allProdData=null;
	private static DBResearchersFormations allFormation=null;
	private Hashtable prodData;
	private Hashtable formation;
   /**
    * Cria o objeto j� com todos os pesquisadores.
    * @param db um banco de dados j� conectado
    */	
	public DBResearcher(DataBase db)throws SQLException {
		super(db, "SELECT i.nome as iesName, " +
				  "i.sigla as iesAbv, " +
				  "i.idies ," +
				  "p.idPesquisador, " +
				  "p.nome " +
				  "FROM PESQUISADOR p LEFT JOIN IES i ON " +
				  "i.IDIES = p.KEYIES " +
				  "order by p.idPesquisador");
		if (allProdData==null)
			allProdData = new DBResearchersProductions(db);
		if (allFormation==null)
			allFormation = new DBResearchersFormations(db);
	}
	/**
	 * @return o DataSet Detail desta rela��o Master/Detail
	 */
	public Hashtable getProdData() {
		return prodData;
	}
	/**
	 * Sobrescrita do m�todo que far� com que o prodData sempre reflita os 
	 * dados de produ��o do pesquisador atualmente selecionado (ou seja o pesquisador
	 * do registro atualmente selecionado).
	 */
	public void updateSlave()throws SQLException {
		if (!getResultSet().isBeforeFirst()){
			prodData = allProdData.getProductionSet(getResearchId());
			formation = allFormation.getFormationSet(getResearchId());
		}
  		   
	}
	/**
	 * @return o campo PESQUISADOR.IDPESQUISADOR
	 * @throws SQLException
	 */		
	public int getResearchId() throws SQLException{
		return getResultSet().getInt("IDPESQUISADOR");	
	}
	/**
	 * @return o campo IES.IDIES
	 * @throws SQLException
	 */		
	public int getIESId() throws SQLException{
		return getResultSet().getInt("IDIES");	
	} 	
	/**
	 * @return o campo PESQUISADOR.NOME
	 * @throws SQLException
	 */		
	public String getResearchName() throws SQLException{
		return getResultSet().getString("NOME");	
	}
	/**
	 * @return o campo IES.NAME
	 * @throws SQLException
	 */		
	public String getIESName() throws SQLException{
		return getResultSet().getString("IESNAME");	
	} 
	/**
	 * @return o campo IES.ABREVIACAO
	 * @throws SQLException
	 */		
	public String getIESAbreviation() throws SQLException{
		return getResultSet().getString("IESABV");	
	}
	public Hashtable getFormation() {
		return formation;
	}
}
