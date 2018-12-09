/**
 * CreateLibrianController displays the librarian window
 * @author Iestyn Price
 * @ version 3.0
 */



import java.sql.SQLException;

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
	TextField line2AddressTextField;
	
	@FXML
	TextField cityAdressTextField;
	
	@FXML
	TextField postcodeAdressTextField;
	
	@FXML
	TextField staffNoTextField;
	
	@FXML
	TextField employDateTextField;
	
	@FXML
	Button cancelLibrianianCancelButton;
	
	@FXML
	Button createLibrianianCancelButton;
	
	@FXML
	ImageView librianImage;
    
    /**
     * @param 
     */
	@FXML
	private  void handleCancelLibrarianButtonEvent(ActionEvent e) {
		Librarian l = new Librarian();
		String address = line1AddressTextField.getText() +"," +line2AddressTextField.getText()+","+cityAdressTextField.getText()
		+"," +cityAdressTextField.getText()+"," +postcodeAdressTextField.getText();
		try {
			l.newLibrarian(usernameTextField.getText(), usernamePassword.getText(), firstNameTextField.getText(), lastNameTextField.getText()
					, Integer.parseInt(mobileNumberTextField.getText()), address, Storage.getNum(), employDateTextField.getText());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			System.out.println("Mobile number should be int!");
			try {
				 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createlibraruan.fxml"));
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
	 * @param e
	 * @throws NumberFormatException if user entered wrong number format
	 * @throws SQLException
	 */
	@FXML
	private  void handleCreateLibrianEvent(ActionEvent e) throws NumberFormatException, SQLException {
			SQLHandle c = new SQLHandle();
			Librarian l = new Librarian();
			l.newLibrarian(usernameTextField.getText(), usernamePassword.getText(),firstNameTextField.getText(), lastNameTextField.getText(), Integer.parseInt(mobileNumberTextField.getText()),line1AddressTextField.getText(), " ",  employDateTextField.getText());
		
		
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
