import java.util.ArrayList;

public class DVD extends Resource {
	private String director;
	private int runtime;
	private String language;
	ArrayList<String> subLanguages = new ArrayList<String>();
	
	
	
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
	public DVD(String director, int runtime, String language, ArrayList<String> subLanguages) {
		super();
		this.director = director;
		this.runtime = runtime;
		this.language = language;
		this.subLanguages = subLanguages;
	}
	
	
	
	
}
