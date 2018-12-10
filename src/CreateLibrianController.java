/**
 * CreateLibrianController displays the librarian window
 * @author Iestyn Price
 * @ version 3.0
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sun.security.util.Password;

public class CreateLibrianController {

	@FXML
	TextField usernameTextField;
	@FXML
	TextField usernamePassword;
	
	@FXML
	TextField firstNameTextField;
	
	@FXML
	TextField lastNameTextField;
	
	@FXML
	TextField mobileNumberTextField;
	
	@FXML
	TextField line1AddressTextField;
	
	@FXML
	TextField line2AdressTextField;
	
	@FXML
	TextField cityAdressTextField;
	
	@FXML
	TextField postcodeAdressTextField;
	
	@FXML
	TextField staffNoTextField;
	
	@FXML
	TextField employDateTextField;
	
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
	Button imageDrawnButton;
	
	@FXML
	Button createLibrianianCancelButton;
	
	@FXML
	ImageView librianImage;



    @FXML
    void avator1Button(ActionEvent event) {
    		
    	librianImage.setImage(getImage(1));
    		Storage.storeImagenum(1);
    }

    @FXML
    void avator2Button(ActionEvent event) {
    	librianImage.setImage(getImage(2));
    		Storage.storeImagenum(2);
    }

    @FXML
    void avator3Button(ActionEvent event) {
    	librianImage.setImage(getImage(3));
		Storage.storeImagenum(3);
    }

    @FXML
    void avator4Button(ActionEvent event) {
    	librianImage.setImage(getImage(4));
     	Storage.storeImagenum(4);
    }

    @FXML
    void avator5Button(ActionEvent event) {
    	librianImage.setImage(getImage(5));
		Storage.storeImagenum(5);
    }

    @FXML
    void avator6Button(ActionEvent event) {
    	librianImage.setImage(getImage(6));
		Storage.storeImagenum(6);
    }
    
    @FXML
    void imageDrawnAction(ActionEvent e) {
    	librianImage.setImage(getImage(Storage.returntNum()));
		Storage.storeImagenum(Storage.returntNum());
    }
    
    private Image getImage(int i) {
    		String location = "userPhoto/"+String.valueOf(i)+".png";
		File file = new File(location);
        Image image = new Image(file.toURI().toString());
        return image;
    }

    
    /**
     * @param 
     */

	@FXML
	private  void handleCancelLibrarianButtonEvent(ActionEvent e) {
		
	
		
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
	 * @param e
	 * @throws NumberFormatException if user entered wrong number format
	 * @throws SQLException
	 */
	@FXML
	private  void handleCreateLibrianEvent(ActionEvent e) throws NumberFormatException, SQLException {
			Librarian l = new Librarian();
			String address = line1AddressTextField.getText()+line2AdressTextField.getText()+cityAdressTextField.getText()+postcodeAdressTextField.getText();
			
		try {
			l.newLibrarian(usernameTextField.getText(), usernamePassword.getText(), firstNameTextField.getText(), lastNameTextField.getText()
					, Integer.parseInt(mobileNumberTextField.getText()), address, Storage.returnImagenum(), employDateTextField.getText());
		
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
	private void handleDrawImageEvent(ActionEvent e) {
	
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
