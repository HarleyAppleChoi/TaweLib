
import java.sql.*;

//https://docs.oracle.com/javase/8/docs/api/index.html?java/sql/package-summary.html
//https://www.roseindia.net/jdbc/introduction-to-the-java.sql.shtml
/**
 * This class connects the user to the database and has the set and get methods
 * to retrieve information from it.
 * 
 * @author Hau Yi Choi
 *
 */
public class SQLHandle {

	// more than 1 database is not allowed

	// your hostname
	final static String HOST_NAME = "jdbc:mysql://localhost:3306/cw230";
	// your SQL username
	final static String USERNAME = "root";
	// your password
	final static String PASSWORD = "";
	final static String GETALLRESOURCES = " ";
	static Statement statement = null;
	static Connection conn = null;

	/**
	 * This method connects the user to the database.
	 */
	public SQLHandle() {

		try {
			conn = DriverManager.getConnection(HOST_NAME, USERNAME, PASSWORD);
			statement = conn.createStatement();
			System.out.println("Success! Connected to the database");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method allows us to create an SQL query in java that will link back to
	 * the database.
	 * 
	 * @param query
	 * @return the result of the query
	 * @throws SQLException
	 */
	public static ResultSet get(String query) throws SQLException {
		ResultSet result = statement.executeQuery(query);
		return result;
	}

	/**
	 * This method allows us to edit the database from a query.
	 * 
	 * @param query
	 * @throws SQLException
	 */
	public void set(String query) throws SQLException {
		statement.executeUpdate(query);
	}

	/**
	 * non static method of get
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet nonStaticGet(String query) throws SQLException {
		ResultSet result = statement.executeQuery(query);
		return result;
	}
}
