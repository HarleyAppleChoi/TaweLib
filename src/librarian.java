import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class librarian extends User {
	
	protected int staffNo;
	protected int employmentDate;
	
	public librarian(int staffNo, int employmentDaye, String username, String firstName,  String lastName,  int mobileNo, Image userImage) 
	{
	}
	
		
	private int getStaffNo() {
		return staffNo;
	}
	
	private int getEmploymentDate() {
		return employmentDate;
	}
	
	private void setStaffNo(int l) {
		staffNo = l;
	}
	
	public void setEmploymentDat(int l) {
		employmentDate = l;
	}
	
	
	private int addResource(String title,String year, String image, int numCopies, int duration) throws SQLException {
		int id = SQLHandle.get("select max(id) from resource;").getInt(0);
		System.out.println(id);
		
		String query = "insert into resource (id,title,year_,image,numcopies, duration)"
				+ "values ('" + id +"','" + title +"','" + year + "','" + image + "','" + numCopies +"','" + duration +")"  ;		

		SQLHandle.set(query);
		return id;
	}
	
	public void addBook(String title,String year, String image, int numCopies, int duration
			,String author, String publisher, String genre, String ISBN ) throws SQLException {
		int id = addResource(title,year, image, numCopies, duration);
		
		String query = "insert into book (id, author,publisher,genre,ISBN.language_)" 
				+ "values(" + id +"','" + author+"','" +publisher;
		
		if (genre != null) {
			
		}
		
	}

	public void addLaptop() {
		
	}
}
