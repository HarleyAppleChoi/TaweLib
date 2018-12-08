
/**
@author Iestyn Price
*/

import java.sql.ResultSet;
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
import javafx.stage.Stage;


public class LogInGuiController {

	@FXML
	TextField enterUserName;
	
	@FXML
	PasswordField enterUserPassword;
	
	@FXML
	Button logInButton;
	
	@FXML
	private  void handleLogInEvent(ActionEvent e) throws NumberFormatException, Exception {
		SQLHandle sql = new SQLHandle();
		String username = enterUserName.getText();
		String query="Select * from user_ where username = '"+username
		+"' and Password = '"+ enterUserPassword.getText().hashCode()+"';";
		ResultSet r = SQLHandle.get(query);
		
		
		if(!r.next()) {
			//password and username is not in the database
			System.out.println("password and username is not in the database");
		}else {
			query="Select * from normal_user where username = '"+username+"';";
			r = SQLHandle.get(query);
			if(r.next()) {
				/*
				FXMLLoader loader = new FXMLLoader(getClass().getResource("logingui.fxml"));
				UserGuiController userGui = loader.getController();
				userGui.reserveAction(username);
				*/
				Storage.storeUsername(username);
				//that is a user and direct it to user dashboard
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("usergui.fxml"));
					 Parent root = (Parent)fxmlLoader.load();
					 Scene scene= new Scene(root); 
					 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
					 window.setScene(scene);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}else {
				//that is a librarian and direct it to librarian dashboard
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
		}
	}
}
		


			
		

	
	
	
	

