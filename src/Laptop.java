import com.sun.prism.Image;

/**
 * This class creates an instance of a Laptop.
 * 
 * @author Emily Studley
 * @version 2.1
 */
public class Laptop extends Resource {

	private String manufacturer;
	private String model;
	private String operatingSystem;

	/**
	 * Constructor to construct a Laptop using the paramaters from this class and
	 * the parameters from the superclass Resource.
	 * 
	 * @param id
	 * @param title
	 * @param year
	 * @param thumbNailImage
	 * @param numCopies
	 * @param numAvailableCopies
	 * @param manufacturer
	 * @param model
	 * @param operatingSystem
	 */
	public Laptop(int id, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies,
			String manufacturer, String model, String operatingSystem) {

		super.id = id;
		super.title = title;
		super.year = year;
		super.thumbNailImage = thumbNailImage;
		super.numCopies = numCopies;
		super.numAvailableCopies = numAvailableCopies;
		this.manufacturer = manufacturer;
		this.model = model;
		this.operatingSystem = operatingSystem;
	}

	/**
	 * Get method to get the manufacturer.
	 * 
	 * @return manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Set method to set the manufacturer.
	 * 
	 * @param manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Get method to get the model.
	 * 
	 * @return model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Set method to set the model.
	 * 
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Get method to get the operating system.
	 * 
	 * @return operatingSystem
	 */
	public String getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * Set method to set the operating system.
	 * 
	 * @param operatingSystem
	 */
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
}
