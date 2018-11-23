import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		SQLHandle sql = new SQLHandle();
		
		ResultSet result = null;
		
		try {
			result = sql.getAllResources("SELECT * FROM stud");
			while(result.next()) {
				
				System.out.print(result.getString("sid")+ " ");
				System.out.println(result.getString("firstname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Nothing to print out");
		}
		

		

}
}
