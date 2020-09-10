/**
 * CreateLibrianController displays the librarian window
 * @author Iestyn Price
 * @ version 3.0
 */

import java.io.File;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sun.security.util.Password;

public class CreateLibrianController {

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField usernamePassword;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField mobileNumberTextField;

	@FXML
	private TextField line1AddressTextField;

	@FXML
	private TextField line2AdressTextField;

	@FXML
	private TextField cityAdressTextField;

	@FXML
	private TextField postcodeAdressTextField;

	@FXML
	private TextField staffNoTextField;

	@FXML
	private TextField employDateTextField;

	@FXML
	private Button avator1button;

	@FXML
	private Button avator2button;

	@FXML
	private Button avator3button;

	@FXML
	private Button avator4button;

	@FXML
	private Button avator5button;

	@FXML
	private Button avator6button;

	@FXML
	private Button imageDrawnButton;

	@FXML
	private Button createLibrianianCancelButton;

	@FXML
	private ImageView librianImage;

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void avator1Button(ActionEvent event) {

		librianImage.setImage(getImage(1));
		Storage.storeImagenum(1);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void avator2Button(ActionEvent event) {
		librianImage.setImage(getImage(2));
		Storage.storeImagenum(2);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void avator3Button(ActionEvent event) {
		librianImage.setImage(getImage(3));
		Storage.storeImagenum(3);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void avator4Button(ActionEvent event) {
		librianImage.setImage(getImage(4));
		Storage.storeImagenum(4);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void avator5Button(ActionEvent event) {
		librianImage.setImage(getImage(5));
		Storage.storeImagenum(5);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void avator6Button(ActionEvent event) {
		librianImage.setImage(getImage(6));
		Storage.storeImagenum(6);
	}

	/**
	 * Show and select a custom draw image. .
	 * 
	 * @param e
	 *            when the button is clicked
	 */
	@FXML
	private void imageDrawnAction(ActionEvent e) {
		librianImage.setImage(getImage(Storage.returntNum()));
		Storage.storeImagenum(Storage.returntNum());
	}

	/**
	 * get the image from location
	 * 
	 * @param i
	 *            number of photo that is created
	 * @return an Image instance that is the user custom image
	 */
	private Image getImage(int i) {
		String location = "userPhoto/" + String.valueOf(i) + ".png";
		File file = new File(location);
		Image image = new Image(file.toURI().toString());
		return image;
	}

	/**
	 * Change the scene to the previous one.
	 * 
	 * @param
	 */

	@FXML
	private void handleCancelLibrarianButtonEvent(ActionEvent e) {

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

	/**
	 * Read the field and write a new librarian into database.
	 * 
	 * @param e
	 *            when the button pressed
	 * @throws NumberFormatException
	 *             if user entered wrong number format
	 * @throws SQLException
	 */
	@FXML
	private void handleCreateLibrianEvent(ActionEvent e) throws NumberFormatException, SQLException {
		Librarian l = new Librarian();
		String address = line1AddressTextField.getText() + line2AdressTextField.getText()
				+ cityAdressTextField.getText() + postcodeAdressTextField.getText();

		try {
			l.newLibrarian(usernameTextField.getText(), usernamePassword.getText(), firstNameTextField.getText(),
					lastNameTextField.getText(), Integer.parseInt(mobileNumberTextField.getText()), address,
					Storage.returnImagenum(), employDateTextField.getText());

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("libgui-3.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Button that bring the user to draw program.
	 * 
	 * @param e
	 *            when the button pressed
	 */
	@FXML
	private void handleDrawImageEvent(ActionEvent e) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DrawGUI.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
