/**
 * This class make a space in memory to store items.
 * 
 * @author Hau Yi Choi
 *
 */
public class Storage {
	private static String usernamee = "";

	/**
	 * This class store String in a memory call userName
	 * @param username the string you want to store
	 */
	public static void storeUsername(String username) {
		usernamee = username;
	}

	/**
	 * This class take out the string you store using storeUsername().
	 * @return take out the string you store using storeUsername().
	 */
	public static String returnUsername() {
		return usernamee;
	}
}
