import javafx.scene.image.Image;

/**
 * user.java
 * user class contain all set and get method to get all the user's attributes 
 * and it collabrate with most of classes to log in the system and search for the resources 
 * @author Jwana Abdalah
 *
 */

public class User {
	protected String username;
	protected String firstName;
	protected String lastName;
	protected int mobileNo;
	protected Image userImage;
	
	/**
	 * @return username
	 */
	protected String getUsename() {
		return username;
	}
	
	/**
	 * @return firstName
	 */
	protected String getFirstNamee() {
		return firstName;
	}
    
    /**
	 * @return lastName
	 */
	protected String getLastName() {
		return lastName;
	}
	/**
	 * @return mibile number
	 */
	protected int getMobileNo() {
		return mobileNo;
	}
	
	/**
	 * @return user Image
	 */
	protected Image getUserImage() {
		return userImage;
	}
	
	/**
	 * method to set the username
	 * @param u
	 */
	protected void setUsername(String u) {
		username = u;
	}
	
	/**
	 * method to set firstName
	 * @param u
	 */
	protected void setFirstName(String u) {
		firstName = u;
	}
	/**
	 * method to set lastName
	 * @param u
	 */
	protected void setLastName(String u) {
		lastName = u;
	}
	/**
	 * method to set mobileNo
	 * @param u
	 */
	protected void setMobileNo(int u) {
		mobileNo = u;
	}
	/**
	 * method to set the image
	 * @param u
	 */
	protected void setImage(Image u) {
		userImage = u;
	}
	
	
}
