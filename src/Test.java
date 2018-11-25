import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		//should be in main method.
		SQLHandle sql = new SQLHandle();

		ResultSet result = null;
		ResultSet result2 = null;

		try {
			result = SQLHandle.get("SELECT * FROM stud");
			while (result.next()) {

				System.out.print(result.getString("sid") + " ");
				System.out.println(result.getString("firstname"));
	
			}
			
			SQLHandle.set("INSERT INTO stud (sid,firstname) VALUES (00003,'apple')");
			
			System.out.println("added");
			
			result2 = SQLHandle.get("SELECT * FROM stud");
			while (result2.next()) {

				System.out.print(result2.getString("sid") + " ");
				System.out.println(result2.getString("firstname"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Somethings goes wrong!");
			e.printStackTrace();
		} finally {
		}
	}
}
