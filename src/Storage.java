
public class Storage {
private static String usernamee = "";
	public static void storeUsername(String username) {
	usernamee = username;
}
	public static String returnUsername() {
		return usernamee;
	}
}
