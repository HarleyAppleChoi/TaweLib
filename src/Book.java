import com.sun.prism.Image;

class Book extends Resource {

private String author;
private String publisher;
private String genre;
private int isbn;
private String language;
private int fineAmount = 2;
private int maxFineAmount = 25;


	public Book (int ID, String title, int year, Image thumbNailImage
			, int numCopies, int numAvailableCopies, String author, String publisher
			, String genre, int isbn, String langauge) {
		
		super(ID,title,year,thumbNailImage,numCopies,numAvailableCopies);

		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.isbn = isbn;
		this.language = langauge;
		
	}


	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author = author;
	}

	public String getPublisher(){
		return publisher;
	}

	public void setPublisher(String publisher){
		this.publisher = publisher;
	}
	
	public String getGenre(){
		return genre;
	}

	public void setGenre(String genre){
		this.genre= genre;
	}

	public int getISBN(){
		return isbn;
	}

	public void setISBN(int isbn){
		this.isbn = isbn;
	}

	public String getLanguage(){
		return language;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public int getIsbn() {
		return isbn;
	}
	
	
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	
	public int getMaxFineAmount() {
		return maxFineAmount;
	}
	
	
	public void setMaxFineAmount(int maxFineAmount) {
		this.maxFineAmount = maxFineAmount;
	}
	
	
		public int getFineAmount() {
		return fineAmount;
	}
	
	
	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}

}