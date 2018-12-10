/**
* This class controller the create user gui in order to navigate between 
* Scenes and allow the librarian to create a new user
*	@author Iestyn Price
*	@version 2.2
*/

import java.io.File;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerCreateUser {

	//password field 
	@FXML
	PasswordField password;
	//user input fields
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
	
	//functional button
	@FXML
	Button cancelButton;
	
	@FXML
	Button createButton;
	
	
	//user avator buttons
	@FXML
    Button imageDrawnButton;

    @FXML
    Button avator1button;

    @FXML
    Button avator2button;

    @FXML
    Button avator3button;

    @FXML
    Button avator4button;

    @FXML
    Button avator5button;

    @FXML
    Button avator6button;

    @FXML
	ImageView normalUserImage;
	
    /**
   	 * Method to set the profile picture as preset 4
   	 * @param e
   	 */
    @FXML
   private void avator1Button(ActionEvent event) {
    		
     	normalUserImage.setImage(getImage(1));
    		Storage.storeImagenum(1);
    }
    /**
   	 * Method to set the profile picture as preset 2
   	 * @param e
   	 */
    @FXML
    private void avator2Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(2));
    		Storage.storeImagenum(2);
    }
    /**
   	 * Method to set the profile picture as preset 3
   	 * @param e
   	 */
    @FXML
    private void avator3Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(3));
		Storage.storeImagenum(3);
    }
    /**
   	 * Method to set the profile picture as preset 4
   	 * @param e
   	 */

    @FXML
    private void avator4Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(4));
     	Storage.storeImagenum(4);
    }
    /**
   	 * Method to set the profile picture as preset 5
   	 * @param e
   	 */

    @FXML
    private void avator5Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(5));
		Storage.storeImagenum(5);
    }
    /**
   	 * Method to set the profile picture as preset 6
   	 * @param e
   	 */
    
    @FXML
    private void avator6Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(6));
		Storage.storeImagenum(6);
    }
    /**
	 * Method to set the profile picture as the draw image
	 * and select it as your image 
	 * @param e
	 */
    @FXML
    private void imageDrawnAction(ActionEvent e) {
     	normalUserImage.setImage(getImage(Storage.returntNum()));
		Storage.storeImagenum(Storage.returntNum());
    }
    
    /**
   	 * Method to get a image from loaction
   	 * @param i location of the image
   	 * @return image returns a image 
   	 */
    
    private Image getImage(int i) {
    		String location = "userPhoto/"+String.valueOf(i)+".png";
		File file = new File(location);
        Image image = new Image(file.toURI().toString());
        return image;
    }
    /**
	 * Method to create a user based on a action event 
	 * @param e
	 */
    
	@FXML
	  private void handleCreateButtonEvent(ActionEvent e) {
		Librarian l = new Librarian();
		String address = create_normalEnterAddLn1.getText() +"\n," +create_normalEnterAddLn2.getText()+"\n,"+create_normalEnterAddCity.getText()
		+"\n," +create_normalEnterAddPostcode.getText();
		try { // get data from fields
			l.newNormalUser(create_normalEnterUsername.getText(), password.getText(), create_normalEnterFirstname.getText()
					, create_normalEnterLastname.getText(), Integer.parseInt(create_normalEnterMobile.getText()), address, Storage.returnImagenum());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			System.out.println("Mobile number should be int!");
			try { // recover if number not input correctly
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createuser.fxml"));
				 Parent root = (Parent)fxmlLoader.load();
				 Scene scene= new Scene(root); 
				 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
				 window.setScene(scene);
				
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		} catch (SQLException e1) {
		}
		
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
	 * Method to cancel create user and locate to the librarian gui
	 * @param e
	 */
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
	
	/**
	 * Method to draw a image based on event
	 * @param e
	 */
	@FXML
	 private void handleImageEvent(ActionEvent e) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DrawGUIUser.fxml"));
				 Parent root = (Parent)fxmlLoader.load();
				 Scene scene= new Scene(root); 
				 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
				 window.setScene(scene);
				
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				
			
	}



}
