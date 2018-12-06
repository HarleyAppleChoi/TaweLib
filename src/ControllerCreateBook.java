/**
@author Iestyn Price
*/


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCreateBook {

	@FXML
	TextField create_bookShowId;
	
	@FXML
	TextField create_bookEnterTitle;
	
	@FXML
	TextField create_bookEnterYear;
	
	@FXML
	Button create_bookCreateImage;
	
	@FXML
	TextField create_bookEnterAuthor;
	
	@FXML
	TextField create_bookEnterPublisher;
	
	@FXML
	TextField create_bookEnterGenre;
	
	@FXML
	TextField create_bookEnterIsbn;
	
	@FXML
	TextField create_bookEnterLanguage;
	
	@FXML
	Button create_bookCancelCreation;
	
	@FXML
	Button  create_bookGenerateBook;

	@FXML
	private  void handleCancelEvent(ActionEvent e) {
		
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
private  void handleCreateEvent(ActionEvent e) {
		
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
private  void handleDrawImage(ActionEvent e) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DrawGUI.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			
		
	}

}
