import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		//should be in main method.
		SQLHandle sql = new SQLHandle();

		ResultSet result = null;
		ResultSet result2 = null;

		try {
			Librarian l = new Librarian();
			/*
			result = SQLHandle.get("select * from resource");
			while(result.next()) {
				System.out.println(result.getString("title"));
			}
			
			l.borrow(1, "apple");
			*/
			//l.borrow(1, "apple");
			NormalUser u = new NormalUser("overdue");
			Librarian b = new Librarian();
			b.addBook("aaa", "123", "adfsa", 1, 8, "asdf", "asdfa", "asdf", "12341", "asdf");
			b.addDvd("wra", "12ss3", "aaaasdfasf", 2, 0, "sssf", "ssasdfa","sssasdf" ,"sssssssadas,sfddf");
			b.addLaptop("asdfa", "1002", "jpeg", 42, 34, "apple", "macbook", "high sherra");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
