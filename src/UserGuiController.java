/**
@author Iestyn Price
*/

//import javax.swing.text.TableView;

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
	Tab dashboardTab;
	
	@FXML
	Tab userDataTab;
	
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
	
	@FXML
	private void buttonSearchDvd(ActionEvent e) {
		dvdButton = !dvdButton;
		laptopButton = false;
		dvdButton = false;
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			userSearch.setText(s.searchDvd(searchQuery.getText()));
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
			try {
				SQLHandle c = new SQLHandle();
				Search s = new Search();
				userSearch.setText(s.searchBook(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else if (dvdButton == true) {
			try {
				SQLHandle c = new SQLHandle();
				Search s = new Search();
				userSearch.setText(s.searchDvd(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else if (laptopButton == true) {
			try {
				SQLHandle c = new SQLHandle();
				Search s = new Search();
				userSearch.setText(s.searchLaptop(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else if (bookButton == false && dvdButton == false && laptopButton == false) {
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			userSearch.setText(s.searchResources(searchQuery.getText()));
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
}
