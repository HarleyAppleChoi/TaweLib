import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles search queries from the database and filtering the search results.
 * 
 * @author Eniko Debreczeny
 * @version 2.1
 */

public class Search {
	
	private String statement;
	
	
	/**
	 * This method returns all the resources in the library for browsing.
	 * @return result The search results converted into string.
	 * @throws SQLException
	 */
    public String displayResources() throws SQLException{
    	String result = "ID        Title         Year";
        statement = "SELECT resourceID,title,year FROM resource;";
        ResultSet r = SQLHandle.get(statement);
         
        //results into string
        while(r.next()) {
        	result = result + String.format("%20s , %20s, %20s\n", r.getInt("resourceID"), r.getString("title"),
					r.getInt("year"));
        }
        return result;
    }
    
	/**
	 * Method to return every type of resource matching the search term.
	 * @param searchString The term the user searched for. 
	 * @return result The search results converted into string.
	 * @throws SQLException
	 */
    public String searchResources(String searchString) throws SQLException{
    	String result = "ID 		   Title		      Year\n";
    	statement = "SELECT resourceID,title,year,numAvCopies FROM resource "
    	+ "WHERE CONCAT(`resourceID`, `title`, `year`) LIKE '%"+searchString+"%';";
        ResultSet r = SQLHandle.get(statement);
        
        //results into string
        while(r.next()) {
        	result = result + String.format("%s %23s %20s\n", r.getInt("resourceID"), r.getString("title"),
					r.getInt("year"));
        }
        return result;
    }
    
	/**
	 * Method to return only book type resources matching the search term.
	 * @param searchString The term the user searched for. 
	 * @return result The search results converted into string.
	 * @throws SQLException
	 */
    public String searchBook(String searchString) throws SQLException{
    	String result = "ID               Title               Author              Publisher                Genre                ISBN               Language               Year               NumAvCopies\n";
    	statement = "SELECT distinct resource.resourceID,title,author,publisher,genre,ISBN,language,year,numAvCopies, image "
    			+ "FROM resource, book WHERE resource.resourceID = book.resourceID and "
    	+ "CONCAT( `title`, `year`,`author`, `publisher`, `genre`, `ISBN`, `language`) LIKE '%"+searchString+"%';";
        ResultSet r = SQLHandle.get(statement);
        
        //results into string
        while(r.next()) {
        	result = result + String.format("%s %20s  %20s %20s %20s %20s %20s %20s %20s\n", r.getInt("resourceID"), r.getString("title"),
        			r.getString("author"), r.getString("publisher"), r.getString("genre"), r.getString("ISBN"),
        			r.getString("language"), r.getInt("year"), r.getInt("numAvCopies"));
        }
        return result;
    }
    
	/**
	 * Method to return only dvd type resources matching the search criteria.
	 * @param searchString The term the user searched for. 
	 * @return result The search results converted into string.
	 * @throws SQLException
	 */
    public String searchDvd(String searchString) throws SQLException{
    	String result = "ID               Title               Director               Language               "
    			+ "Subtitle               Runtime               Year               AvailableCopies\n";
    	statement = "SELECT distinct resource.resourceID,title, director, _language, "
    			+ "GROUP_CONCAT(distinct subtitle SEPARATOR ',') as subs, "
    			+ "runtime,year,numAvCopies "
    			+ "FROM resource, dvd, dvd_subtitle where resource.resourceID = dvd.resourceID and"
    			+ " dvd.resourceID = dvd_subtitle.resourceID and"
    	+ " CONCAT(`title`, `year`, `_language`, `director`) LIKE '%"+searchString+"%' group by resourceID";
   
        ResultSet r = SQLHandle.get(statement);
 
        //results to string
        while(r.next()) {
        	result = result + String.format("%s %20s %20s %20s %30s %20s %20s %20s\n",r.getInt("resourceID"), 
        			r.getString("title"), r.getString("director"), r.getString("_language"), r.getString("subs"),
        			r.getInt("runtime"), r.getInt("year"), r.getInt("numAvCopies"));
        	}
        return result;
    }
    
	/**
	 * Method to return only laptop type resources matching the search criteria.
	 * @param searchString The term the user searched for. 
	 * @return result The search results converted into string.
	 * @throws SQLException
	 */
    public String searchLaptop(String searchString) throws SQLException{
		String result = "ID               Title               Manufacturer              Model               OPSystem               Year               AvailableCopies\n";
		statement = "SELECT distinct resource.resourceID,title,manufacturer, model, operatingSystem,year,numAvCopies "
				+ "FROM resource, laptop where resource.resourceID = laptop.resourceID and"
				+ " CONCAT(`title`, `year`,`manufacturer`,`model`,`operatingSystem`) LIKE '%" + searchString + "%'";
		ResultSet r = SQLHandle.get(statement);

		// results to string
		while (r.next()) {
			result = result + String.format("%s %20s %20s %20s %20s %20s %20s\n", r.getInt("resourceID"),
					r.getString("title"), r.getString("manufacturer"), r.getString("model"),
					r.getString("operatingSystem"), r.getInt("year"), r.getInt("numAvCopies"));
		}
        return result;
    }

//method for returning a string of borrowed books with the id, username and date borrowed
    
    /**
     * this a borrow search that search for the current borrowing thing
     * @param searchString
     * @return
     * @throws SQLException
     */
    public String borrowSearch(String searchString) throws SQLException {
    	String result = "BorrowingID    Username     DateBorrowed\n";
    	
    	if (searchString.equals("")) {
    		result = "input ID please.";
    		return result;
    	} else {
    	
	    	statement = "select borrowing.borrowDate, borrowing.returnDate, T.username, T.borrowingID " + "from borrowing," 
	    					+ " ((select * from returned_his) union all (select * from current_borrowing)) as T" 
	    					+ " where borrowing.borrowingID = T.borrowingID " + "and resourceID = '" + searchString 
	    					+ "' order by borrowDate";
	    	
	    	 ResultSet r = SQLHandle.get(statement);
	         while(r.next()) {
	         	result = result + String.format("%9s %22s %19s\n", r.getInt("borrowingID"), r.getString("username"),
	         			r.getString("borrowDate"));
	         }
	    	 return result;
    	}
    	
    }

    //method for returning string of returned copies, with id, username and date returns
    
    /**
     * This is the method that return that returned borrowing.
     * @param searchString
     * @return
     * @throws SQLException
     */
    public String returnSearch(String searchString) throws SQLException {
    	String result = "BorrowingID    Username     DateReturned\n";
    	
    	if (searchString.equals("")) {
    		result = "input ID please.";
    		return result;
    	} else {
    	
	    	statement = "SELECT borrowing.borrowingID, username, returnDate "
	    			+ "FROM borrowing, returned_his "
	    			+ "WHERE borrowing.borrowingID = returned_his.borrowingID and resourceID = " + searchString + "";
	    	
	    	 ResultSet r = SQLHandle.get(statement);
	         while(r.next()) {
	         	result = result + String.format("%9s %22s %19s\n", r.getInt("borrowingID"), r.getString("username"),
	         			r.getString("returnDate"));
	         }
	    	 return result;
    	}	
    }

    /**
     * method for returning a string of overdue copies, with id, title, year, username, and overdue days
     * @return
     * @throws SQLException
     */
    public String overdueSearch() throws SQLException {
    	SQLHandle sql = new SQLHandle();
    	
    	String result = "resourceID     Title           Year     Username     Days Overdue\n";
    	statement = "SELECT distinct resource.resourceID, title, year, username, dueDate FROM borrowing, resource, "
    			+ "current_borrowing WHERE borrowing.resourceID = resource.resourceID "
    			+ "and borrowing.borrowingID = current_borrowing.borrowingID";
    	
    	ResultSet r = sql.nonStaticGet(statement);
    	while(r.next() ) {
    		System.out.println("while cycle");
    		if (r.getDate("dueDate")!=null) {
    			System.out.println("gets to date field not null");
    			if ((r.getDate("dueDate").getTime() - System.currentTimeMillis() < 0)) {
    				System.out.println("gets to overdue add to rs");
        			result = result + String.format("%5s %20s %10s %15s %15s\n", r.getString("resourceID"), r.getString("title"), r.getInt("year"), 
        	    	r.getString("username"), (System.currentTimeMillis() - r.getDate("dueDate").getTime())/86400000);
    		} else if ((r.getDate("dueDate").getTime() - System.currentTimeMillis() >= 0)){
    			System.out.println("gets to time is postive, no t overdue > skip");
    			r.next();
    		}
    		}
    	}
    	return result;
    	}
    
    
    
    
}


