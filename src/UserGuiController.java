/**
@author Iestyn Price
*/

//import javax.swing.text.TableView;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class UserGuiController {
	private boolean bookButton = false;
	private boolean dvdButton = false;
	private boolean laptopButton= false;

	@FXML
	Tab searchTab;
	
	@FXML
	Tab editImageTab;
	@FXML
	Button drawProfileImage;
	
	@FXML
	Button changeProfileImageButton;
	
	@FXML
	TextField searchQuery;
	
	@FXML
	Button searchButton;
	
	@FXML
	Button searchBook;
	
	@FXML
	Button searchDvd;
	
	@FXML
	Button searchlaptop;
	
	@FXML
	TextField searchResource;

	@FXML
	TextArea userSearch;
	
	//resrve tab
	@FXML
	Tab reserveTab;
	
	@FXML
	TextField reserveField;
	
	@FXML
	Button reserveButton;
	
	//Transaction Tab
	
	@FXML
	Tab dashboardTab;
	
	@FXML
	TextArea transaction;
	
	@FXML
	TextArea borrowItem;
	
	@FXML
	TextArea reserved;
	
	@FXML
	TextArea requested;
	
	@FXML
	TextField balance;
	
	
	//UserData
	@FXML
    private Tab userDataTab;

    @FXML
    private TextField userDataUsername;

    @FXML
    private TextField userDataFirstName;

    @FXML
    private TextField userDataLastName;

    @FXML
    private TextField userDataMobileNumber;
    
    @FXML
    private TextField userDataAddress;
    
    @FXML ImageView profileImageView;
    
    
	
    @FXML
    private void userDataAction(ActionEvent e) throws Exception {
    		NormalUser u = new NormalUser(Storage.returnUsername());
    		userDataUsername.setText(u.getUsername());
    		userDataFirstName.setText(u.getFirstName());
    		userDataLastName.setText(u.getLastName());
    		userDataMobileNumber.setText(String.valueOf(u.getMobileNo()));
    		userDataAddress.setText(u.getAddress());
    		
    		//Image
    		profileImageView.setImage(u.getUserImage());
    		
    }
    
	@FXML
	private void dashBoard(ActionEvent e) throws Exception {
		NormalUser u = new NormalUser(Storage.returnUsername());
		transaction.setText(u.transactionHistory());
		borrowItem.setText(u.getBorrowedList());
		reserved.setText(u.getReservedItem());
		requested.setText(u.getRequestedItem());
		balance.setText(String.valueOf(u.getBalance()));
	}
	
	
	@FXML
	private void buttonSearchDvd(ActionEvent e) {
		
		dvdButton = !dvdButton;
		laptopButton = false;
		bookButton = false;
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			userSearch.setText(s.searchDvd(searchQuery.getText()));
			dvdButton = false;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	@FXML
	private void buttonSearchLaptop(ActionEvent e) {
		
		laptopButton = !laptopButton;
		dvdButton = false;
		bookButton = false;
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			userSearch.setText(s.searchLaptop(searchQuery.getText()));
			laptopButton = false;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	

	@FXML
	private void buttonSearchBook(ActionEvent e) {
		
		bookButton = !bookButton;
		dvdButton = false;
		laptopButton = false;
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			userSearch.setText(s.searchBook(searchQuery.getText()));
			bookButton = false;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	//@FXML private void radioSearchBook(ActionEvent e) {
	//	bookRadioButton = !bookRadioButton;
	//}
	
	@FXML
	private void loadDataFromDb(ActionEvent e) {
		

		if (bookButton == true) {
			bookButton = false;
			dvdButton = false;
			laptopButton = false;
			try {
				SQLHandle c = new SQLHandle();
				Search s = new Search();
				userSearch.setText(s.searchBook(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		//	bookButton = false;
		} else if (dvdButton == true) {
			bookButton = false;
			dvdButton = false;
			laptopButton = false;
			try {
				SQLHandle c = new SQLHandle();
				Search r = new Search();
				userSearch.setText(r.searchDvd(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		//	dvdButton = false;

		} else if (laptopButton == true) {
			bookButton = false;
			dvdButton = false;
			laptopButton = false;
			try {
				SQLHandle c = new SQLHandle();
				Search t = new Search();
				userSearch.setText(t.searchLaptop(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		//	laptopButton = false;
		} else if (bookButton == false || dvdButton == false || laptopButton == false) {
		try {
			SQLHandle c = new SQLHandle();
			Search p = new Search();
			userSearch.setText(p.searchResources(searchQuery.getText()));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	}
	
	
	@FXML
	private  void handleDrawImage(ActionEvent e) {
	
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
	
	LogInGuiController login;
	
	@FXML
	public void reserveAction(ActionEvent e) throws NumberFormatException, Exception {
		
		Librarian l = new Librarian();
		l.request(Integer.parseInt(reserveField.getText()), Storage.returnUsername());
	}
}
