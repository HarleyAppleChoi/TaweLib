/**
@author Iestyn Price
*/


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CreateDVDController {
	
	@FXML
	TextField dvdID;
	@FXML
	TextField numberOfCopiesDVD;
	
	@FXML
	TextField durationDVD;
	
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
	private void handleCreateDVDEvent(ActionEvent e) throws NumberFormatException, SQLException {
		SQLHandle c = new SQLHandle();
		Librarian l = new Librarian();
		l.addDvd(dvdTitle.getText(), dvdYear.getText(), path, Integer.parseInt(numberOfCopiesDVD.getText())
				, Integer.parseInt(durationDVD.getText()),dvdDirector.getText(),dvdLanguage.getText()
				, dvdRuntime.getText(), dvdSubtitle.getText());
		
		
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

	
	private String path;

	@FXML
	private void handleCreateDVDImage(ActionEvent e) throws IOException, SQLException {
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

