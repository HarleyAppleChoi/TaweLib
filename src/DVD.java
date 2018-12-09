
import java.util.ArrayList;
import com.sun.prism.Image;

/**
 * This class creates an instance of a DVD.
 * 
 * @author Emily Studley
 * @version 2.1
 *
 */
public class DVD extends Resource {
	private String director;
	private int runtime;
	private String language;
	ArrayList<String> subLanguages = new ArrayList<String>();

	private int fineAmount = 2;
	private int maxFineAmount = 25;
	

	/**
	 * Constructor to construct a DVD using the parameters from this class and the
	 * parameters from the superclass Resource.
	 * 
	 * @param id The unique identifier of the DVD.
	 * @param title The title of the DVD.
	 * @param year The year the DVD was released.
	 * @param thumbNailImage Image of the DVD cover.
	 * @param numCopies Number of copies owned by the library.
	 * @param numAvailableCopies Number of copies available to borrow from the library.
	 * @param director The director of the contents of the DVD.
	 * @param runtime The runtime of the content of the DVD.
	 * @param language The language of the DVD.
	 * @param subLanguages The subtitles available on the DVD.
	 */
	public DVD(int id, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies,
			String director, int runtime, String language, ArrayList<String> subLanguages) {

		super(id, title, year, thumbNailImage, numCopies, numAvailableCopies);
		this.director = director;
		this.runtime = runtime;
		this.language = language;
		this.subLanguages = subLanguages;
	}

	/**
	 * Get method to get the director.
	 * 
	 * @return The director of the contents of the DVD.
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Set method to set the director.
	 * 
	 * @param director The director of the contents of the DVD.
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Get method to get the runtime.
	 * 
	 * @return runtime The runtime of the contents of the DVD.
	 */
	public int getRuntime() {
		return runtime;
	}

	/**
	 * Set method to set the runtime.
	 * 
	 * @param runtime The runtime of the contents of the DVD.
	 */
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	/**
	 * Get method to get the language.
	 * 
	 * @return language The language of the contents of the DVD.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Set method to set the language.
	 * 
	 * @param language The language of the contents of the DVD.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
