/**
@author Iestyn Price
*/


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

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
		l.addDvd(dvdTitle.getText(), dvdYear.getText(), "", Integer.parseInt(numberOfCopiesDVD.getText()), Integer.parseInt(durationDVD.getText()),dvdDirector.getText(),dvdLanguage.getText(), dvdRuntime.getText(), dvdSubtitle.getText());
		
		
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
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(new Stage());
		if(file != null) {
	        String imagepath = file.getPath();
	        System.out.println("file:"+imagepath);
	        Image image = new Image(imagepath);
	        
	        SQLHandle sql = new SQLHandle();
			ResultSet r = sql.nonStaticGet("Select max(image) from user_;");
			int number = 0;
			while(r.next()) {
				number = r.getInt("max(image)")+1;
			}
	        
			BufferedImage bimage = new BufferedImage((int)image.getWidth(), (int)image.getHeight(),BufferedImage.TYPE_INT_RGB);

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(image, null, null);
		    bGr.dispose();
	        ImageIO.write(image, "png", "");
	        
	        //imageView.setImage(image);
	    }
	    else
	    {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Information Dialog");
	        alert.setHeaderText("Please Select a File");
	        /*alert.setContentText("You didn't select a file!");*/
	        alert.showAndWait();
	    }
	
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

