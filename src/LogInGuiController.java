
/**
@author Iestyn Price,Hau Yi Choi
*/

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInGuiController {

	// Text
	@FXML
	private TextField enterUserName;

	@FXML
	private PasswordField enterUserPassword;

	@FXML
	private Button logInButton;

	/**
	 * This do the login algorithm and identify the username is a librarian or
	 * normal user and direct them to different scene
	 * 
	 * @param e
	 *            Action Event when click on button
	 * @throws Exception
	 *             when some complicated exception is shown
	 */
	@FXML
	private void handleLogInEvent(ActionEvent e) throws Exception {
		SQLHandle sql = new SQLHandle();
		String username = enterUserName.getText();
		String query = "Select * from user_ where username = '" + username + "' and Password = '"
				+ enterUserPassword.getText().hashCode() + "';";
		ResultSet r = SQLHandle.get(query);

		if (!r.next()) {
			// password and username is not in the database
			System.out.println("password and username is not in the database");
		} else {
			query = "Select * from normal_user where username = '" + username + "';";
			r = SQLHandle.get(query);
			if (r.next()) {

				Storage.storeUsername(username);
				// that is a user and direct it to user dashboard
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("usergui.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Scene scene = new Scene(root);
					Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
					window.setScene(scene);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				// that is a librarian and direct it to librarian dashboard
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("libgui-3.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Scene scene = new Scene(root);
					Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
					window.setScene(scene);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}