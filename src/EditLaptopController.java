import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EditLaptopController {

	@FXML
	private TextField enterID;

	@FXML
	private TextField laptopTitle;

	@FXML
	private TextField laptopYear;

	@FXML
	private TextField laptopManufactuer;

	@FXML
	private TextField laptopModel;

	@FXML
	private TextField laptopOS;
	@FXML
	private TextField loanDuration;

	@FXML
	private Button laptopCancelButton;
	@FXML
	private Button laptopEditButton;

	@FXML
	private ImageView laptopImage;

	@FXML
	private TextField NumberOfCopies;

	/**
	 * Direct it to the previous scene;
	 * 
	 * @param e
	 *            when button pressed
	 * @throws SQLException
	 *             when SQL Query go wrong.
	 */
	@FXML
	private void HandleCancelEvent(ActionEvent e) throws SQLException {

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
	 * Edit a laptop.
	 * 
	 * @param e
	 *            when button pressed
	 * @throws SQLException
	 *             SQL Query go wrong.
	 */
	@FXML
	private void handleEditLaptopEvent(ActionEvent e) throws SQLException {
		SQLHandle c = new SQLHandle();
		Librarian l = new Librarian();
		l.editLaptop(Integer.parseInt(enterID.getText()), laptopTitle.getText(), laptopYear.getText(), " ",
				Integer.parseInt(NumberOfCopies.getText()), Integer.parseInt(loanDuration.getText()),
				laptopManufactuer.getText(), laptopModel.getText(), laptopOS.getText());

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
