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

public class CreateDVDController {
	
	@FXML
	TextField dvdID;
	
	@FXML
	TextField dvdTitle;
	
	@FXML
	TextField dvdYear;
	
	@FXML
	TextField dvdDirector;
	
	@FXML
	TextField dvdRuntime;
	
	@FXML
	TextField dvdLanguage;
	
	@FXML
	TextField dvdSubtitle;
	
	@FXML
	Button dvdCancel;
	
	@FXML
	Button dvdCreate;
	
	@FXML
	Button dvdImage;
	
	
	
	
	

	@FXML
	private void handleCreateDVDEvent(ActionEvent e) {
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
	private void handleCancelDVDEvent(ActionEvent e) {
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
	private void handleCreateDVDImage(ActionEvent e) {
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