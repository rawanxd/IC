package DataAcess;
import java.sql.*;

public class DBPecas {
    Connection conn;
    Statement stm;
    ResultSet rs;
	public DBPecas() {
	    try{
	        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	        conn = DriverManager.getConnection(
	        		    "jdbc:odbc:empresa", 
	      		         "limitedUser", 
	      		         "1234");
	        stm  = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	        							ResultSet.CONCUR_UPDATABLE);
	        rs = stm.executeQuery("SELECT COD, NOME FROM PECAS ORDER BY NOME");
	      }
	    catch (ClassNotFoundException cnfex){
	        System.out.print(cnfex.toString());
	      }
	    catch (SQLException sqlex){
	        System.out.print(sqlex.toString());
	      };
	}
	
	public void listaPecas() throws SQLException{
		rs.beforeFirst();
		while (rs.next()){
			System.out.println(rs.getInt("COD") + "  ".concat(rs.getString("NOME")));
		}
	}
	public void excluaPeca() throws SQLException{
		rs.deleteRow();
	}
	public void atualizePeca(int cod, String nome) throws SQLException{
		rs.updateInt("COD", cod);
		rs.updateString("NOME", nome);
		rs.updateRow();
	}
	public void insiraPeca(int cod, String nome) throws SQLException{
		rs.moveToInsertRow();
		rs.updateInt("COD", cod);
		rs.updateString("NOME", nome);
		rs.insertRow();
		rs.moveToCurrentRow();
	}
}
