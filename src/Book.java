import com.sun.prism.Image;

/**
 * This class creates an instance of a Book.
 * 
 * @author Emily Studley
 * @version 2.1
 * 
 */
class Book extends Resource {

	private String author;
	private String publisher;
	private String genre;
	private int isbn;
	private String language;


	/**
	 * Constructor to construct a Book using the parameters from this class and the
	 * parameter from the superclass Resource.
	 * 
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
	public Book(int id, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies,
			String author, String publisher, String genre, int isbn, String langauge) {

		super(id, title, year, thumbNailImage, numCopies, numAvailableCopies);
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.isbn = isbn;
		this.language = langauge;
		
	}

	/**
	 * Get method to get the author.
	 * 
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set method to set the author.
	 * 
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get method to get the publisher.
	 * 
	 * @return publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Set method to set the publisher.
	 * 
	 * @param publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Get method to get the genre.
	 * 
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Set method to set the genre.
	 * 
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Get method to get the isbn.
	 * 
	 * @return isbn
	 */
	public int getISBN() {
		return isbn;
	}

	/**
	 * Set method to set the isbn.
	 * 
	 * @param isbn
	 */
	public void setISBN(int isbn) {
		this.isbn = isbn;
	}

	/**
	 * Get method to get the language.
	 * 
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Set method to set the language.
	 * 
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;

	}
}