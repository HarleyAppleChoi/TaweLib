
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
	TextField enterID;
	
	@FXML
	TextField enterTitle;
	
	@FXML
	TextField enterYear;
	
	@FXML
	TextField enterAuthor;
	
	@FXML
	TextField enterPublisher;
	
	@FXML
	TextField enterGenre;
	
	@FXML
	TextField  enterISBN;
	
	@FXML 
	TextField enterLanguage;

	@FXML
	TextField enterNumCopies;
	
	@FXML
	TextField editDuration;
	
	@FXML
	Button cancelButton;
	
	@FXML
	Button editDetails;
	
	@FXML
	Button imageButton;

	@FXML
	public void editBookEvent(ActionEvent e) throws NumberFormatException, SQLException {
		Librarian l = new Librarian();
		l.editBook(Integer.parseInt(enterID.getText()), enterTitle.getText(), enterYear.getText()
				, "image", Integer.parseInt(enterNumCopies.getText()), Integer.parseInt(editDuration.getText())
				,enterAuthor.getText(), enterPublisher.getText(), enterGenre.getText(), enterISBN.getText(), enterLanguage.getText());
		
	}
	@FXML
	private void cancelEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("libgui-3.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	
	
	}
	@FXML
	private void handleImageEvent(ActionEvent e) {
		
	}

}

