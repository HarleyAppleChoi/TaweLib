/**
@author Iestyn Price
*/

import javax.swing.text.TableView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
//import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class UserGuiController {

	@FXML
	Tab searchTab;
	
	@FXML
	Tab dashboardTab;
	
	@FXML
	Tab userDataTab;
	
	@FXML
	Tab editImageTab;
	@FXML
	Button drawProfileImage;
	
	@FXML
	Button changeProfileImageButton;
	@FXML
	TableView viewResource;
	
	@FXML
	RadioButton bookButton;
	
	@FXML
	RadioButton dvdButton;
	
	@FXML
	RadioButton laptopButton;
	
	@FXML
	TextField searchResource;
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
