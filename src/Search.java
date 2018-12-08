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
    	String result = "";
        statement = "SELECT resourceID,title,_year FROM resource;";
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
    	String result = "";
    	statement = "SELECT resourceID,title,year,numAvCopies FROM resource "
    	+ "WHERE CONCAT(`resourceID`, `title`, `year`) LIKE '%"+searchString+"%'";
        ResultSet r = SQLHandle.get(statement);
        
        //results into string
        while(r.next()) {
        	result = result + String.format("%20s     %20s %20s\n", r.getInt("resourceID"), r.getString("title"),
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
    	String result = "ResourceID      Title              Author   Publisher  Genre   ISBN  Language Year NumAvCopies Image\n";
    	statement = "SELECT distinct resource.resourceID,title,author,publisher,genre,ISBN,language,year,numAvCopies, image "
    			+ "FROM resource, book WHERE resource.resourceID = book.resourceID and "
    	+ "CONCAT( `title`, `year`,`author`, `publisher`, `genre`, `ISBN`, `language`) LIKE '%"+searchString+"%'";
        ResultSet r = SQLHandle.get(statement);
        
        //results into string
        while(r.next()) {
        	result = result + String.format("%s %30s %10s %15s %10s %7s %7s %10s %7s %22s\n", r.getInt("resourceID"), r.getString("title"),
        			r.getString("author"), r.getString("publisher"), r.getString("genre"), r.getString("ISBN"),
        			r.getString("language"), r.getInt("year"), r.getInt("numAvCopies"), r.getString("image"));
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
    	String result = "ResourceID Title Director Language Subtitle Runtime Year AvailableCopies\n";
    	statement = "SELECT distinct resource.resourceID,title, director, _language, subtitle, runtime,year,numAvCopies "
    			+ "FROM resource, dvd, dvd_subtitle where resource.resourceID = dvd.resourceID and"
    			+ " resource.resourceID = dvd_subtitle.resourceID and"
    	+ " CONCAT(`title`, `year`, `_language`, `director`) LIKE '%"+searchString+"%'";
        ResultSet r = SQLHandle.get(statement);
        
        //results to string
        while(r.next()) {
        	result = result + String.format("%s, %s, %s, %s, %s, %s %s, %s\n", r.getInt("resourceID"), r.getString("title"),
        			r.getString("direction"), r.getString("_language"), r.getString("subtitle"), r.getInt("runtime"), 
        			r.getInt("year"), r.getInt("numAvCopies"));
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
    	String result = "ResourceID Title Manufacturer Model OPSystem Year AvailableCopies\n";
    	statement = "SELECT distinct resource.resourceID,title,manufacturer, model, operatingSystem,year,numAvCopies "
    			+ "FROM resource, laptop where resource.resourceID = laptop.resourceID and"
    	+ " CONCAT(`title`, `year`,`manufacturer`,`model`,`operatingSystem`) LIKE '%"+searchString+"%'";
        ResultSet r = SQLHandle.get(statement);
        
        //results to string
        while(r.next()) {
        	result = result + String.format("%s, %s, %s,%s, %s %s, %s\n", r.getInt("resourceID"), r.getString("title"),
        			r.getString("manufacturer"), r.getString("model"), r.getInt("operatingSystem"), r.getInt("_year"),
        			r.getInt("numAvCopies"));
        }
        return result;
    }


}

