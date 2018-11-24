
import java.sql.*;
//https://docs.oracle.com/javase/8/docs/api/index.html?java/sql/package-summary.html
//https://www.roseindia.net/jdbc/introduction-to-the-java.sql.shtml
public class SQLHandle {
	
	//more than 1 database is not allowed
	final static String HOST_NAME = "jdbc:mysql://localhost:3306/cw2";
	final static String USERNAME = "root";
	final static String PASSWORD = "123";
	final static String GETALLRESOURCES = " ";
	static Statement statement = null;
	static Connection conn = null;

	public SQLHandle(){
		
	try {
		conn = DriverManager.getConnection(HOST_NAME,USERNAME,PASSWORD);
		statement= conn.createStatement();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} 
	System.out.println("Success!");
}
	
	
	public static ResultSet get(String query) throws SQLException {
		ResultSet result = statement.executeQuery(query);
		return result;
	}
	
	public static void set(String query) throws SQLException {
		statement.executeUpdate(query);
	}
}
