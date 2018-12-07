import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

	public Connection Connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/cw230";
			String user = "root";
			String password = "";
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url, user, password);
			return conn;
			
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
