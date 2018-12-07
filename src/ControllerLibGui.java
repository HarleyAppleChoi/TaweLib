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
import javafx.scene.control.RadioButton;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLibGui {
	//tables need type params but they are unknown atm
	//search tab

	@FXML
	RadioButton searchSelectBook;
	
	@FXML
	RadioButton searchSelectDvd;
	
	@FXML 
	RadioButton searchSelectLaptop;
	
	@FXML
	TextField searchEnterSearchQuery;
	

	
	@FXML
	Button searchOpenAdditionalInfo;
	
	//borrowing tab
	
	@FXML
	TextField borrowingEnterBorrowerUsername;
	
	@FXML
	TextField borrowingEnterSearchQuery;
	
	@FXML
	RadioButton borrowingSelectBook;
	
	@FXML
	RadioButton borrowingSelectDvd;
	
	@FXML
	RadioButton borrowingSelectLaptop;
	
	
	@FXML
	Button borrowingGenerateBorrow;
	
	//create resource tab
	
	@FXML
	Button create_resourceCreateBook;
	
	@FXML
	Button create_resourceCreateDvd;
	
	@FXML
	Button create_resouceCreateLaptop;
	
	//edit resource tab
	
	@FXML
	TextField edit_resourceEnterResourceId;
	
	@FXML
	Button edit_resourceBeginEdit;
	
	//create user tab
	
	@FXML
	Button create_userCreateNormal;
	
	@FXML
	Button create_userCreateLibrarian;
	
	//edit user tab
	
	@FXML
	TextField edit_userEnterUsername;
	
	@FXML
	Button edit_userBeginEdit;
	
	//return resource tab
	
	@FXML
	TextField returnEnterResourceId;
	
	@FXML
	Button returnGenerateReturn;
	
	@FXML
	TextField returnShowFineAdded;
	
	//pay fine tab
	
	@FXML
	TextField pay_fineEnterUsername;
	
	@FXML
	TextField pay_fineEnterPaymentAmount;
	
	@FXML
	TextField pay_fineShowFineRemaining;
	
	@FXML
	Button pay_fineGeneratePayment;
	
	//request tab
	
	@FXML
	TextField requestEnterUsername;
	
	@FXML
	TextField requestEnterResourceId;
	
	@FXML
	Button requestGenerateRequest;
	@FXML
	public void handleCreateBookEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createbook-3.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}

	}
	@FXML
	public void handleCreateDVDResourceEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createdvd-2.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}

	}
	@FXML
	public void handleCreateLaptopEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createlaptop-2.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}

	
	}
	@FXML
	public void createNormalUserEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createuser.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	
	}
	@FXML
	public void createLibrianUserEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createlibrarian.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	
	}
	
	@FXML
	public void handleResourceIDInput(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createlibrarian.fxml"));
			 Parent root = (Parent)fxmlLoader.load();
			 Scene scene= new Scene(root); 
			 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			 window.setScene(scene);
			
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}
}
