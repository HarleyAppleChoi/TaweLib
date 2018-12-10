/**
 * EditDvdGuiController  class creates checkbox to select diffrent subtitle of dvd.
 * @author Iestyn Price
 * @ version 3.1
 */

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

public class EditDvdGuiController {
  //TextField tabs
	@FXML
	private TextField enterID;
	
	@FXML
	private TextField enterTitle;
	
	@FXML
	private TextField enterYear;
	
	@FXML
	private TextField enterDirector;

	
	@FXML
	private TextField enterRunTime;
	
	@FXML
	private TextField enterLanguage;
	
	@FXML
	private TextField enterNumCopies;
	
	@FXML
	private TextField editDuration;
	
	// button tabs
	@FXML
	private Button cancelButton;
	
	@FXML
	private Button editDvdDetails;
	
	//checkBox tabs
	@FXML
	private CheckBox selectEnglish;
	
	@FXML
	private CheckBox selectSpanish;
	
	@FXML
	private CheckBox selectGerman;
	
	@FXML
	private CheckBox selectFrench;
	
	@FXML
	private CheckBox selectJapanese;
	
	@FXML
	private TextField otherSubtitle;
	
	@FXML
	private ImageView dvdImage;
    
    
    /**
     * create a checkbox to select subtitle 
	 * @param e
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	@FXML
	public void editDVDEvent(ActionEvent e) throws NumberFormatException, SQLException {
		
		//see the subtitle selected state and add it to subtitle string.
		String subtitle="";
		Librarian l = new Librarian();
		if(selectJapanese.isSelected()) {
			subtitle += "Japanese,";
		}if (selectSpanish.isSelected()) {
			subtitle += "Spanish,";
		}if(selectGerman.isSelected()) {
			subtitle += "German,";
		}if(selectFrench.isSelected()){
			subtitle += "French,";
		}
		subtitle += otherSubtitle.getText();
		
		
		l.editDVD(Integer.parseInt(enterID.getText()), enterTitle.getText(), enterYear.getText(),"", Integer.parseInt(enterNumCopies.getText()), 
				Integer.parseInt(editDuration.getText()), enterDirector.getText(), enterLanguage.getText(), enterRunTime.getText(), subtitle);
	}

    /**
     * cancel the event and go back to main window
	 * @param e
	 */
	@FXML
	private void cancelEvent(ActionEvent e) {
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
