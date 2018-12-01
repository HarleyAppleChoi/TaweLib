import javafx.scene.image.Image;

/**
 * This class creates a User.
 * 
 * @author Jwana Abdalah
 * @version 1
 *
 */
public abstract class User implements Storable {
	protected String username;
	protected String firstName;
	protected String lastName;
	protected int mobileNo;
	protected Image userImage;
	
	/**
	 * Constructor to construct a User.
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param mobileNo
	 * @param userImage
	 */
	protected User(String username, String firstName, String lastName, int mobileNo, Image userImage) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.userImage = userImage;
	}
	

	/**
	 * Get method to get the username.
	 * 
	 * @return username
	 */
	protected String getUsename() {
		return username;
	}

	/**
	 * Set method to set the username.
	 * 
	 * @param username
	 */
	protected void setUsername(String userame) {
		this.username = username;
	}

	/**
	 * Get method to get the firstName.
	 * 
	 * @return firstName
	 */
	protected String getFirstName() {
		return firstName;
	}

	/**
	 * Set method to set the firstName.
	 * 
	 * @param firstname
	 */
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get method to get the lastName.
	 * 
	 * @return lastName
	 */
	protected String getLastName() {
		return lastName;
	}

	/**
	 * Set method to set the lastName.
	 * 
	 * @param u
	 */
	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get method to get the mobile number.
	 * 
	 * @return mobileNo
	 */
	protected int getMobileNo() {
		return mobileNo;
	}

	/**
	 * Set method to set the mobile number.
	 * 
	 * @param mobileNo
	 */
	protected void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Get method to get the users image.
	 * 
	 * @return userImage
	 */
	protected Image getUserImage() {
		return userImage;
	}

	/**
	 * Set method to set the users image.
	 * 
	 * @param u
	 */
	protected void setImage(Image userImage) {
		this.userImage = userImage;
	}

}
