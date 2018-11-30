import com.sun.prism.Image;
/**
 * This class is a subclass of resource thta creat all instance of a book 
 * @author Emily Studly
 * @ version
 * 
 */

class Book extends Resource {

private String author;
private String publisher;
private String genre;
private int isbn;
private String language;
private int fineAmount = 2;
private int maxFineAmount = 25;



    /**
	 * @param id
	 * @param title
	 * @param year
	 * @param thumbNailImage
	 * @param numCopies
	 * @param numAvailableCopies
	 * @param author
	 * @param publisher
	 * @param genre
	 * @param isbn
	 * @param langauge
	 */


	public Book (int id, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies, String author, String publisher, String genre, int isbn, String langauge) {
		
		super.id = id;
		super.title = title;
		super.year = year;
		super.thumbNailImage = thumbNailImage;
		super.numCopies = numCopies;
		super.numAvailableCopies =  numAvailableCopies;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.isbn = isbn;
		this.language = langauge;
		
	}
	
    /**
	 * @return author
	 */
	public String getAuthor(){
		return author;
	}
	
	/**
	 * @param author
	 */
	public void setAuthor(String author){
		this.author = author;
	}
	
    /**
	 * @return publisher
	 */
	public String getPublisher(){
		return publisher;
	}
    
    /**
	 * @param publisher
	 */
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}
	
	/**
	 * @return genre
	 */
	public String getGenre(){
		return genre;
	}
    
    /**
	 * @param genre
	 */
	public void setGenre(String genre){
		this.genre= genre;
	}
    
    /**
	 * @return isbn
	 */
	public int getISBN(){
		return isbn;
	}
   
    /**
	 * @param isbn
	 */
	public void setISBN(int isbn){
		this.isbn = isbn;
	}
    
    /**
	 * @return language
	 */
	public String getLanguage(){
		return language;
	}
	
    /**
	 * @param language
	 */
	public void setLanguage(String language){
		this.language = language;

	}
	
	/**
	 * @return maxFineAmount
	 */
	public int getMaxFineAmount() {
		return maxFineAmount;
	}
	
	/**
	 * @param maxFineAmount
	 */
	public void setMaxFineAmount(int maxFineAmount) {
		this.maxFineAmount = maxFineAmount;
	}
	
   /**
	* @return
    */
		public int getFineAmount() {
		return fineAmount;
	}
	
	/**
	 * @param fineAmount
	 */
	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}

}