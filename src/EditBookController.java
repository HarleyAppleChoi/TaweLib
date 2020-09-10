
/**
@author Iestyn Price
*/

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

public class EditBookController {

	@FXML
	private TextField enterID;

	@FXML
	private TextField enterTitle;

	@FXML
	private TextField enterYear;

	@FXML
	private TextField enterAuthor;

	@FXML
	private TextField enterPublisher;

	@FXML
	private TextField enterGenre;

	@FXML
	private TextField enterISBN;

	@FXML
	private TextField enterLanguage;

	@FXML
	private TextField enterNumCopies;

	@FXML
	private TextField editDuration;

	@FXML
	private Button cancelButton;

	@FXML
	private Button editDetails;

	@FXML
	private Button imageButton;

	/**
	 * Edit book base of info given
	 * 
	 * @param e
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	@FXML
	public void editBookEvent(ActionEvent e) throws NumberFormatException, SQLException {
		Librarian l = new Librarian();
		l.editBook(Integer.parseInt(enterID.getText()), enterTitle.getText(), enterYear.getText(), "image",
				Integer.parseInt(enterNumCopies.getText()), Integer.parseInt(editDuration.getText()),
				enterAuthor.getText(), enterPublisher.getText(), enterGenre.getText(), enterISBN.getText(),
				enterLanguage.getText());

	}

	/**
	 * Direct it to the previous scene.
	 * 
	 * @param e
	 */
	@FXML
	private void cancelEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("libgui-3.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/**
		 * Emptu
		 */
	}

	@FXML
	private void handleImageEvent(ActionEvent e) {

	}

}
