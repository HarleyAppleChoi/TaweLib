
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
import javafx.stage.Stage;


public class LogInGuiController {

	@FXML
	PasswordField enterUserName;
	
	@FXML
	PasswordField enterUserPassword;
	
	@FXML
	Button logInButton;
	
	@FXML
	private  void handleLogInEvent(ActionEvent e) throws SQLException {
		SQLHandle sql = new SQLHandle();
		String query="Select * from user_ where username = '"+enterUserName.getText()
		+"' and Password = '"+enterUserPassword.getText().hashCode()+"';";
		ResultSet r = SQLHandle.get(query);
		
		if(!r.next()) {
			//password and username is not in the database
			System.out.println("password and username is not in the database");
		}else {
			query="Select * from normal_user where username = '"+enterUserName.getText()+"';";
			r = SQLHandle.get(query);
			if(r.next()) {
				//that is a user and direct it to user dashboard
			}else {
				//that is a librarian and direct it to librarian dashboard
			}
		}
		
		
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
