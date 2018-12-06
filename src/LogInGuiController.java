
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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


public class LogInGuiController {

	@FXML
	PasswordField enterUserName;
	
	@FXML
	PasswordField enterUserPassword;
	
	@FXML
	Button logInButton;
	
	@FXML
	private  void handleLogInEvent(ActionEvent e) {
		
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("usergui.fxml"));
				 Parent root = (Parent)fxmlLoader.load();
				 Scene scene= new Scene(root); 
				 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
				 window.setScene(scene);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			
		
	}	
	
	
	
	
}
