import javafx.scene.image.Image;

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
	private String isbn;
	private String language;


	/**
	 * Constructor to construct a Book using the parameters from this class and the
	 * parameter from the superclass Resource.
	 * 
	 * @param id The unique identifier of the book.
	 * @param title The title of the book.
	 * @param year The year the book was published.
	 * @param thumbNailImage Image of the book cover.
	 * @param numCopies Number of copies owned by the library.
	 * @param numAvailableCopies Number of copies available to borrow in the library.
	 * @param author The author of the book.
	 * @param publisher The publisher of the book.
	 * @param genre The genre of the book.
	 * @param isbn the ISBN number of the book.
	 * @param langauge The language of the book.
	 */
	public Book(int id, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies,
			String author, String publisher, String genre, String isbn, String langauge) {

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
	 * @return author The author of the book.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set method to set the author.
	 * 
	 * @param author The author of the book.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get method to get the publisher.
	 * 
	 * @return publisher The publisher of the book.
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Set method to set the publisher.
	 * 
	 * @param publisher The publisher of the book.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Get method to get the genre.
	 * 
	 * @return genre The genre of the book.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Set method to set the genre.
	 * 
	 * @param genre The genre of the book.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Get method to get the ISBN.
	 * 
	 * @return isbn The ISBN number of the book.
	 */
	public String getISBN() {
		return isbn;
	}

	/**
	 * Set method to set the ISBN.
	 * 
	 * @param isbn The ISBN number of the book.
	 */
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Get method to get the language.
	 * 
	 * @return language The language of the book.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Set method to set the language.
	 * 
	 * @param language The language of the book.
	 */
	public void setLanguage(String language) {
		this.language = language;

	}
}