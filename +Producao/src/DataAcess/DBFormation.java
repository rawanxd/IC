package DataAcess;
import dataCon.DataSet;
import java.sql.*;
import java.util.Vector;

import dataCon.*;
/**
 * <p>Title: DBFormation</p>
 * <p>Description: Um DataSet especializado em tipos de forma��o (mestrado, doutorado, etc).</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @version 1.0
 */
public class DBFormation extends DataSet{
   /**
    * Cria o objeto j� com todos os tipos de dados de produ��o.
    * @param db um banco de dados j� conectado
    */
	public DBFormation(DataBase db){
		super(db, "SELECT * FROM FORMACAO ORDER BY CODNIVEL");
	}
	/**
	 * @return o campo FORMACAO.CODNIVEL
	 * @throws SQLException
	 */	
	public char getCodNivel() throws SQLException{
		return getResultSet().getString("CODNIVEL").charAt(0);
	}
	/**
	 * @return o campo FORMACAO.NOME
	 * @throws SQLException
	 */	
	public String getNome() throws SQLException{
		return getResultSet().getString("NOME");
	}
	/**
	 * @return o campo FORMACAO.ABREVIACAO
	 * @throws SQLException
	 */	
	public String getSeqOrdNivel() throws SQLException{
		return getResultSet().getString("SEQORDNIVEL");
	}
	/**
	 * @return um vetor de vectors; o vetor[0] armazena o codNivel e pode ser
	 * usado como �ndice do vetor[1] que armazena o nome da forma��o
	 * @throws SQLException
	 */	
	public Vector[] getFormations() throws SQLException{
		Vector[] v = new Vector[2];
		v[0] = new Vector();
		v[1] = new Vector();
		getResultSet().beforeFirst();
		while (getResultSet().next()){
			v[0].add(new Character(getResultSet().getString("CODNIVEL").charAt(0)));
			v[1].add(getResultSet().getString("NOME"));
		}
		return v;
	}
}