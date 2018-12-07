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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateLaptopController {


	@FXML
	TextField numberOfCopiesLaptop;
	
	@FXML
	TextField createLaptopTitle;
	
	@FXML
	TextField createLaptopYear;
	
	@FXML
	TextField durationLaptop;
	
	@FXML
	TextField createLaptopManufacturer;
	
	@FXML
	TextField createLaptopModel;
	
	@FXML
	TextField createLaptopOS;
	
	@FXML
	Button laptopCancelButton;
	
	@FXML
	Button laptopCreateButton;

	
	@FXML
	Button laptopDrawImage;
	
	
	@FXML
	private void handleCancelButtonEvent(ActionEvent e) {
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
	private void handleCreateButtonEvent(ActionEvent e) throws NumberFormatException, SQLException {
		SQLHandle c = new SQLHandle();
		Librarian l = new Librarian();
		l.addLaptop(createLaptopTitle.getText(), createLaptopYear.getText(), " ",Integer.parseInt(numberOfCopiesLaptop.getText()), createLaptopManufacturer.getText(), createLaptopModel.getText(), createLaptopOS.getText(),Integer.parseInt(durationLaptop.getText()));

		
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
	private void handleDrawEvent(ActionEvent e) {
		
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
