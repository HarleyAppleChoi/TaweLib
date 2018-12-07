import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserSearchTable {
	
	private final StringProperty ID;
	private final StringProperty Title;
	private final StringProperty Year;
	
	public UserSearchTable(String ID, String Title, String Year) {
		this.ID = new SimpleStringProperty(ID);
		this.Title = new SimpleStringProperty(Title);
		this.Year = new SimpleStringProperty(Year);
	}
	public String getID() {
		return ID.get();
	}
	public String getTitle() {
		return Title.get();
	}
	public String getYear() {
		return Year.get();
	}
	public void setID(String value) {
		ID.set(value);
	}
	public void setTitle(String value) {
		Title.set(value);
	}
	public void setYear(String value) {
		Year.set(value);
	}
	public StringProperty IDProperty() {
		return ID;
	}
	public StringProperty TitleProperty() {
		return Title;
	}
	public StringProperty YearProperty() {
		return Year;
	}

}
