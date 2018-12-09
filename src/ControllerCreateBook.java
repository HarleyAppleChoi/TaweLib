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
	TextField numbOfCopies;
	
	@FXML
	TextField loanDurationLaptop;
	
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
private  void handleCreateEvent(ActionEvent e) throws NumberFormatException, SQLException {
	SQLHandle c = new SQLHandle();
	Librarian l = new Librarian();
	l.addBook(create_bookEnterTitle.getText(), create_bookEnterYear.getText(), path ,Integer.parseInt(numbOfCopies.getText())
			, Integer.parseInt(loanDurationLaptop.getText()), create_bookEnterAuthor.getText(), create_bookEnterPublisher.getText()
			, create_bookEnterGenre.getText(), create_bookEnterIsbn.getText(), create_bookEnterLanguage.getText());
			
	
	
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
private  void handleDrawImage(ActionEvent e) {
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
