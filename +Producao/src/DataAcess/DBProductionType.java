package DataAcess;
import dataCon.DataSet;
import java.sql.*;
import java.util.Vector;

//import constants.*;
import dataCon.*;
/**
 * <p>Title: DBProductionData</p>
 * <p>Description: Um DataSet especializado em tipos de dados de produ��o.</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @author M�rcio Balian
 * @author Rog�rio Fiuza
 * @version 1.0
 */
public class DBProductionType extends DataSet{
   /**
    * Cria o objeto j� com todos os tipos de dados de produ��o.
    * @param db um banco de dados j� conectado
    */
	public DBProductionType(DataBase db){
		super(db, "SELECT * FROM TIPOPRODUCAO ORDER BY NOME");
	}
	/**
	 * @return o campo TIPOPRODUCAO.IDTIPOPRODUCAO
	 * @throws SQLException
	 */	
	public int getId() throws SQLException{
		return getResultSet().getInt("IDTIPOPRODUCAO");
	}
	/**
	 * @return o campo TIPOPRODUCAO.NOME
	 * @throws SQLException
	 */	
	public String getNome() throws SQLException{
		return getResultSet().getString("NOME");
	}
	/**
	 * @return o campo TIPOPRODUCAO.ABREVIACAO
	 * @throws SQLException
	 */	
	public String getAbreviacao() throws SQLException{
		return getResultSet().getString("ABREVIACAO");
	}
	/**
	 * @return o maior valor entre todos os tipos de produ��o atingindo por
	 * qualquer pesquisador. 
	 * @throws SQLException
	 */
	public int getMaxValue() throws SQLException{
		ResultSet aux = getDataBase().selecting("SELECT SUM(p.VALOR), " +
                                  "t.IDTIPOPRODUCAO, p.KEYPESQUISADOR " +
                                  "FROM PRODUCAO p JOIN TIPOPRODUCAO t ON " +
                                  "p.KEYTIPOPRODUCAO = t.IDTIPOPRODUCAO " +
                              "GROUP BY t.IDTIPOPRODUCAO, p.KEYPESQUISADOR");
		int max =0, sux = 0;
		aux.beforeFirst();
		while (aux.next()){
			sux = aux.getInt(1);
			if (sux > max)
				  max = sux;			
		}
		   
		
		return 0;// max + Consts.EXTRA_SPACE;
	}
	public Vector getIndicators() throws SQLException{
		Vector v = new Vector();
		getResultSet().beforeFirst();
		while (getResultSet().next()){
			v.add(getResultSet().getString("NOME"));
		}

		return v;
	}
	  
	  
}