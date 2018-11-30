import com.sun.prism.Image;

public class Laptop extends Resource {
	
	private String manufacturer;
	private String model;
	private String operatingSystem;
	private int fineAmount = 10;
	private int maxFineAmount = 100;
	
	
	public Laptop(int ID, String title, int year, Image thumbNailImage, int numCopies, int numAvailableCopies, String manufacturer, String model, String operatingSystem) {
		super(ID,title,year,thumbNailImage,numCopies,numAvailableCopies);
		this.manufacturer = manufacturer;
		this.model = model;
		this.operatingSystem = operatingSystem;
	}
	
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
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
