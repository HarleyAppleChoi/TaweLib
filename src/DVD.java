
import java.util.ArrayList;
import com.sun.prism.Image;

public class DVD extends Resource {
	private String director;
	private int runtime;
	private String language;
	ArrayList<String> subLanguages = new ArrayList<String>();
	private int fineAmount;
	private int maxFineAmount;
	
public DVD(int id, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies, String director, int runtime, String language, ArrayList<String> subLanguages) {
		
		super.id = id;
		super.title = title;
		super.year = year;
		super.thumbNailImage = thumbNailImage;
		super.numCopies = numCopies;
		super.numAvailableCopies =  numAvailableCopies;
		this.director = director;
		this.runtime = runtime;
		this.language = language;
		this.subLanguages = subLanguages;
	}
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
