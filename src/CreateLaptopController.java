/**
 * Class to control the functioning of the create laptop gui
 * and all the functions they can perform.
 * @author Iestyn Price modified  by James Hogg
 * @version 2.1
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

	private String path;
	
	//enter input 
	@FXML
	private TextField numberOfCopiesLaptop;
	
	@FXML
	private TextField createLaptopTitle;
	
	@FXML
	private TextField createLaptopYear;
	
	@FXML
	private TextField durationLaptop;
	
	@FXML
	private TextField createLaptopManufacturer;
	
	@FXML
	private TextField createLaptopModel;
	
	@FXML
	private TextField createLaptopOS;
	
	@FXML
	private Button laptopCancelButton;
	
	@FXML
	private Button laptopCreateButton;

	
	@FXML
	private Button laptopDrawImage;
	
	/**
	 * Method to cancel create event based on action 
	 * @param e
	 */
	@FXML
	private void handleCancelButtonEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("libgui-3.fxml"));//load librarion gui
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method to create a laptop based on action 
	 * @param e
	 * @throws NumberException
	 * @throws SQLException
	 */
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
	
	/**
	 * Method to handle draw event based on action 
	 * @param e
	 */
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
