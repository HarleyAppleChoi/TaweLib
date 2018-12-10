/**
 * ControllerCreatBook.java
 * @author Iestyn Price
 * @ version 2.1
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
	TextField createBookShowId;
	
	@FXML
	TextField createBookEnterTitle;
	
	@FXML
	TextField createBookEnterYear;
	
	@FXML
	Button createBookCreateImage;
	
	@FXML
	TextField createBookEnterAuthor;
	
	@FXML
	TextField createBookEnterPublisher;
	
	@FXML
	TextField createBookEnterGenre;
	
	@FXML
	TextField createBookEnterIsbn;
	
	@FXML
	TextField createBookEnterLanguage;
	@FXML
	TextField numbOfCopies;
	
	@FXML
	TextField loanDurationLaptop;
	
	@FXML
	Button createBookCancelCreation;
	
	@FXML
	Button  createBookGenerateBook;
	

	/**
	 * councel event and return to homescreen
	 * @param e
	 */
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
	

	 /**
      * creat window for librarian to add book to the library.
      * @param e
      * @throws NumberFormatException
      * @throws SQLExceptio
      */
@FXML
private  void handleCreateEvent(ActionEvent e) throws NumberFormatException, SQLException {

	SQLHandle c = new SQLHandle();
	Librarian l = new Librarian();
	l.addBook(createBookEnterTitle.getText(), createBookEnterYear.getText(), path ,Integer.parseInt(numbOfCopies.getText())
			, Integer.parseInt(loanDurationLaptop.getText()), createBookEnterAuthor.getText(), createBookEnterPublisher.getText()
			, createBookEnterGenre.getText(), createBookEnterIsbn.getText(), createBookEnterLanguage.getText());
			
	
	
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

	

//store the path that the user choose
private String path;

/**
 * loads Gui file to drow Image
 */	
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
