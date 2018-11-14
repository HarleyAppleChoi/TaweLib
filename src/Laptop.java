
public class Laptop extends Resource {
	
	private String manufacturer;
	private String model;
	private String operatingSystem;
	
	
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
	
	
	public Laptop(String manufacturer, String model, String operatingSystem) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.operatingSystem = operatingSystem;
	}
	
	
	
	
}
