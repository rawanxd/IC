package dataCon;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * <p>Title: DataSet</p>
 * <p>Description: Implementa uma estrutura para a manipula��o de registros em 
 * um ResultSet </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Inform�tica UFG-Brasil</p>
 * @author Luciano Silva
 * @author M�rcio Balian
 * @author Rog�rio Fiuza
 * @version 1.0
 */
public class DataSet {
	/**
	 * Instancia v�lida e conectada ao banco de dados
	 */
	private DataBase db;
	/**
	 * Um ResultSet com o resultado de uma consulta
	 */
	private ResultSet rs;
	private String sql;
	/**
	 * Cria um dataset com o resultado da consulta informada
	 * @param db banco de dados j� conectado
	 * @param query uma consulta SQL v�lida
	 */
	public DataSet(DataBase db, String query){
		this.db = db;
		rs = db.selecting(query);
		sql = query;
	}
	/**
	 * Usado para realizar uma rela��o Master/Detail entre dois datasets.
	 * O dataset master fica respons�vel por sobrescrever este m�todo de tal modo
	 * que quando o cursor do Master se mover uma nova consulta seja feita no Datail
	 * utlizando a chave estrangeira das tabelas.
	 * TODOS OS M�TODOS QUE MOVIMENTAM O CURSOR FAZ UMA CHAMADA A ESTE M�TODO AP�S
	 * O CURSOR ASSUMIR A SUA NOVA POSI��O.
	 * @throws SQLException
	 */
	public void updateSlave() throws SQLException{
		
	}
	/**
	 * Faz uma nova consulta e atribui seu resultado ao resultset
	 * @param sql  uma consulta SQL v�lida
	 * @throws SQLException
	 */
	public void select(String sql) throws SQLException{
		rs = db.selecting(sql);
		this.sql = sql;
		updateSlave();
	}
	/**
	 * @throws SQLException
	 */
	public void first() throws SQLException {
              rs.first();	
        //    if(rs.next());
            
	    if (rs.getRow()!=0)
	       updateSlave();
	}
	/**
	 * @throws SQLException
	 */	
	public void beforeFirst() throws SQLException{
		rs.beforeFirst();
		updateSlave();		
	}
	/**
	 * @throws SQLException
	 */	
	public void last() throws SQLException {
		rs.last();
                updateSlave();		
	}
	/**
	 * @throws SQLException
	 */
	public boolean next() throws SQLException {
		boolean b = rs.next();
		if (b) 
		  updateSlave();
		return b; 
	}
	/**
	 * @throws SQLException
	 */
	public boolean prev() throws SQLException {
		boolean b = rs.previous();
		if (b) 
		  updateSlave();
		return b; 
	}
	/**
	 * Reexecuta a consulta original
	 * @throws SQLException
	 */	
	public void refresh() throws SQLException {
		rs = db.selecting(sql);
		updateSlave();	
	}
    /**
     * @return a quantidade de registros do resultset
     * @throws SQLException
     */
	public int getRecordCount() throws SQLException {
		int i = 0;
            	rs.last();
           //     while(rs.next()){ 
           //     } 
                i = rs.getRow();
		//updateSlave();
		return i;			
	}
    /**
     * @return o resultset no estado atual
     */
	public ResultSet getResultSet(){
		return rs;
	}
	/**
	 * @return o banco de dados
	 */
	public DataBase getDataBase(){
		return db;
	}
	/**
	 * Localiza um valor no resultset no campo informado, posicionando o cursor do
	 * DataSet nesta posi��o.
	 * @param field campo (coluna) do resultset onde ser� comparado o valor
	 * @param value valor a ser comparado
	 * @return true se encontrou
	 * @throws SQLException
	 */
	public boolean locate(String field, String value) throws SQLException{
		String s="";
		rs.beforeFirst();
		if (rs.getRow()!=0){
		while (rs.next()){
			 s = rs.getString(field);
			 if (s.equalsIgnoreCase(value))
				 break;
		};
		}
		return (s.equalsIgnoreCase(value))?true:false;
	}
    /**
     * Imprime no console o conte�do deste DataSet
     * @throws SQLException
     */		
	public void printAll()throws SQLException{
		int colunmCount = rs.getMetaData().getColumnCount();
		
		for (int i=1; i<= colunmCount; i++){
			 System.out.print(rs.getMetaData().getColumnName(i) + " | ");
		}
		beforeFirst();
		while (next()){
			System.out.println("");
			for (int i=1; i<= colunmCount; i++){
			  System.out.print(rs.getString(i) + " ");
			}
		}
	} 
	/**
	 * Move o cursor para a linha desenada
	 * @param pos posi��o do cursor
	 * @throws SQLException
	 */
	public void goRecNo(int pos)throws SQLException{
		  rs.absolute(pos);
	}
	/**
	 * @return posi��o atual do cursor
	 * @throws SQLException
	 */
	public int getRecNo()throws SQLException{
		return rs.getRow();
	}
}