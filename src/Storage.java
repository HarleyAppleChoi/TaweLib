/**
 * This class make a space in memory to store items.
 * 
 * @author Hau Yi Choi
 *
 */
public class Storage {
	private static String usernamee = "";
	private static int num;
	private static int imagenum;

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
	
	public static void storeNum(int number) {
		num = number;
	}
	
	public static int returntNum() {
		return num;
	}
	
	public static void  storeImagenum(int i) {
		imagenum = i;
	}
	
	public static int returnImagenum() {
		return imagenum;
	}

}
