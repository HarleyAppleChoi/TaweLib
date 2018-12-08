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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class UserGuiController {

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
	/**
	@FXML
	TableView<UserSearchTable> tableUserSearch;
	
	@FXML
	TableColumn<UserSearchTable, String> idColumn;
	
	@FXML
	TableColumn<UserSearchTable, String> titleColumn;
	
	@FXML
	TableColumn<UserSearchTable, String> yearColumn;
	*/
	
	@FXML
	TextField searchQuery;
	
	@FXML
	Button searchButton;
	
	@FXML
	RadioButton bookButton;
	
	@FXML
	RadioButton dvdButton;
	
	@FXML
	RadioButton laptopButton;
	
	@FXML
	TextField searchResource;
	
	@FXML
	TextArea userSearch;
	
	//private ObservableList<UserSearchTable>data;
	//private DBConnection dc;
	
	//public void initialise(URL url, ResourceBundle rb) {
		//dc = new DBConnection();
	//}
	
	@FXML
	private void loadDataFromDb(ActionEvent e) {
		/**
		try {
			Connection conn = dc.Connect();
			data = FXCollections.observableArrayList();
			ResultSet rs = conn.createStatement().executeQuery("SELECT resourceID, title, year FROM resource");
			while (rs.next()) {
				data.add(new UserSearchTable(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException ex) {
			System.err.println("Error" + ex);
		}
		
		
		
		idColumn.setCellValueFactory(new PropertyValueFactory<> ("ID"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<> ("ID"));
		yearColumn.setCellValueFactory(new PropertyValueFactory<> ("Year"));
		
		tableUserSearch.setItems(null);
		tableUserSearch.setItems(data);
		*/
		//Search s = new Search();
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			userSearch.setText(s.searchBook(searchQuery.getText()));
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
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
