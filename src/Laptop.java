import javafx.scene.image.Image;

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

	private int fineAmount = 10;
	private int maxFineAmount = 100;

	/**
	 * Constructor to construct a Laptop using the paramaters from this class and
	 * the parameters from the superclass Resource.
	 * 
	 * @param id
	 *            The unique identifier of the laptop.
	 * @param title
	 *            The title of the laptop.
	 * @param year
	 *            The year the laptop was produced.
	 * @param thumbNailImage
	 *            Image of the laptop.
	 * @param numCopies
	 *            Number of copies owned by the library.
	 * @param numAvailableCopies
	 *            Number of copies available to borrow from the library.
	 * @param manufacturer
	 *            The manufacturer of the laptop.
	 * @param model
	 *            The model of the laptop.
	 * @param operatingSystem
	 *            The operating system running on the laptop.
	 */
	public Laptop(int id, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies,
			String manufacturer, String model, String operatingSystem) {

		super(id, title, year, thumbNailImage, numCopies);
		this.manufacturer = manufacturer;
		this.model = model;
		this.operatingSystem = operatingSystem;
	}

	/**
	 * Get method to get the manufacturer.
	 * 
	 * @return manufacturer The manufacturer of the laptop.
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Set method to set the manufacturer.
	 * 
	 * @param manufacturer
	 *            The manufacturer of the laptop.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Get method to get the model.
	 * 
	 * @return model The model of the laptop.
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Set method to set the model.
	 * 
	 * @param model
	 *            The model of the laptop.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Get method to get the operating system.
	 * 
	 * @return operatingSystem The operating system running on the laptop.
	 */
	public String getOperatingSystem() {
		return operatingSystem;
	}

	/**
	 * Set method to set the operating system.
	 * 
	 * @param operatingSystem
	 *            The operating system running on the laptop.
	 */
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
}
