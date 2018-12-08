import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EditLaptopController {

	@FXML
	TextField enterID;
	
	
	@FXML
	TextField laptopTitle;
	
	@FXML
	TextField laptopYear;
	
	@FXML
	TextField laptopManufactuer;
	
	@FXML
	TextField laptopModel;
	
	@FXML
	TextField laptopOS;
	@FXML
	TextField loanDuration;
	
	@FXML
	Button laptopCancelButton;
	@FXML
	Button laptopEditButton;
	
	@FXML
	ImageView laptopImage;
	
	@FXML
	TextField NumberOfCopies;
	
	@FXML
	private  void HandleCancelEvent(ActionEvent e) throws SQLException {
		
			
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
	private  void handleEditLaptopEvent(ActionEvent e) throws SQLException {
		SQLHandle c = new SQLHandle();
		Librarian l = new Librarian();
		l.editLaptop(Integer.parseInt(enterID.getText()), laptopTitle.getText(), laptopYear.getText(), " ",Integer.parseInt(NumberOfCopies.getText()) ,Integer.parseInt(loanDuration.getText()), laptopManufactuer.getText(), laptopModel.getText(), laptopOS.getText());
			
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
