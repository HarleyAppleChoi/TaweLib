
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
	

	/**
	 * Constructor to construct a DVD using the parameters from this class and the
	 * parameters from the superclass Resource.
	 * 
	 * @param id
	 * @param title
	 * @param year
	 * @param thumbNailImage
	 * @param numCopies
	 * @param numAvailableCopies
	 * @param director
	 * @param runtime
	 * @param language
	 * @param subLanguages
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
	 * @return
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Set method to set the director.
	 * 
	 * @param director
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Get method to get the runtime.
	 * 
	 * @return runtime
	 */
	public int getRuntime() {
		return runtime;
	}

	/**
	 * Set method to set the runtime.
	 * 
	 * @param runtime
	 */
	public void setRuntime(int runtime) {
		this.runtime = runtime;
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
