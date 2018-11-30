import javafx.scene.image.Image;

public abstract class User implements Storable {
	protected String username;
	protected String firstName;
	protected String lastName;
	protected int mobileNo;
	protected Image userImage;
	
	protected String getUsename() {
		return username;
	}
	
	protected String getFirstNamee() {
		return firstName;
	}

	protected String getLastName() {
		return lastName;
	}
	
	protected int getMobileNo() {
		return mobileNo;
	}
	
	protected Image getUserImage() {
		return userImage;
	}
	
	protected void setUsername(String u) {
		username = u;
	}
	
	protected void setFirstName(String u) {
		firstName = u;
	}
	protected void setLastName(String u) {
		lastName = u;
	}
	protected void setMobileNo(int u) {
		mobileNo = u;
	}
	protected void setImage(Image u) {
		userImage = u;
	}
	
	
}
