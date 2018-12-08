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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerCreateUser {

	@FXML
	TextField create_normalEnterUsername;
	
	@FXML
	TextField create_normalEnterFirstname;
	
	@FXML
	TextField create_normalEnterLastname;
	
	@FXML
	TextField create_normalEnterMobile;
	
	@FXML
	Button create_normalCreateImage;
	
	@FXML
	TextField create_normalEnterAddLn1;
	
	@FXML
	TextField create_normalEnterAddLn2;
	
	@FXML
	TextField create_normalEnterAddCity;
	
	@FXML
	TextField create_normalEnterAddPostcode;
	
	@FXML
	Button cancelButton;
	
	@FXML
	Button createButton;
	
	@FXML
	ImageView normalUserImage;
	
	@FXML
	private  void handleCreateButtonEvent(ActionEvent e) {
		
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
	private  void handleCancelButtonEvent(ActionEvent e) {
		
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

	public void handleImageEvent(ActionEvent e) {


			
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
