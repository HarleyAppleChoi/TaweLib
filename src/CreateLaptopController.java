/**
@author Iestyn Price
*/


import java.io.File;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
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
		l.addLaptop(createLaptopTitle.getText(), createLaptopYear.getText(), path,Integer.parseInt(numberOfCopiesLaptop.getText()), 
				Integer.parseInt(durationLaptop.getText()), createLaptopModel.getText(), 
				createLaptopManufacturer.getText(),createLaptopOS.getText());
		
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
	
	private String path;
	@FXML
	private void handleDrawEvent(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(new Stage());
		if(file != null) {
	        String imagepath = file.getPath();
	        System.out.println("file:"+imagepath);
	        //Image image = new Image(imagepath);
	        
	        path = imagepath;
			
	    }
	    else
	    {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Information Dialog");
	        alert.setHeaderText("Please Select a File");
	        /*alert.setContentText("You didn't select a file!");*/
	        alert.showAndWait();
	    }
	
	}



}
