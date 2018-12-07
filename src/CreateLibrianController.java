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

public class CreateLibrianController {

	@FXML
	TextField UsernameTextField;
	
	@FXML
	TextField StaffNoTextField;
	
	@FXML
	TextField firstNameTextField;
	
	@FXML
	TextField lastNameTextField;
	
	@FXML
	TextField mobileNumberTextField;
	
	@FXML
	TextField line1AddressTextField;
	
	@FXML
	TextField line2AddressTextField;
	
	@FXML
	TextField cityAdressTextField;
	
	@FXML
	TextField postcodeAdressTextField;
	
	@FXML
	TextField staffNoTextField;
	
	@FXML
	TextField employmentDateTextField;
	
	@FXML
	Button cancelLibrianianCancelButton;
	
	@FXML
	Button createLibrianianCancelButton;
	
	@FXML
	ImageView librianImage;

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
	@FXML
	private  void handleCreateLibrianEvent(ActionEvent e) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("libgui-3.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		///add code to create user
			
		
	}	

}