import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		//should be in main method.
		SQLHandle sql = new SQLHandle();

		ResultSet result = null;
		ResultSet result2 = null;

		try {
			result = SQLHandle.get("select * from resource");
			while(result.next()) {
				System.out.println(result.getString("title"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
