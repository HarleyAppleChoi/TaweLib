import javafx.scene.image.Image;

/**
 * User.java
 * this class creates all the instance of the user class .
 * 
 * @author Jwana Abdalah
 * @modified by James Hogg
 * @version 1.1
 *
 */
public abstract class User implements Storable {
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected int mobileNo;
	protected String address;
	protected int userImage;
	
	/**
	 * Constructor to construct a User's instance.
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param mobileNo
	 * @param address
	 * @param userImage
	 
	 */
	protected User(String username, String password, String firstName, String lastName, int mobileNo, String address, int userImage) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.address = address;
		this.userImage = userImage;
	}
	
	protected User() {
		
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
	protected void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * Get method to get the password.
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *  Set method to set the password.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @param lastName
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
	 * Set method to set the address.
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Get method to get the address.
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @param userImage
	 */
	protected void setImage(Image userImage) {
		this.userImage = userImage;
	}

}
