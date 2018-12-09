/**
@author Iestyn Price
*/

import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerCreateUser {

	
	@FXML
	PasswordField password;
	
	@FXML
	TextField create_normalEnterUsername;
	
	@FXML
	TextField create_normalEnterFirstname;
	
	@FXML
	TextField create_normalEnterLastname;
	
	@FXML
	TextField create_normalEnterMobile;
	
	@FXML
	Button create_normalCreateImage;
	
	@FXML
	TextField create_normalEnterAddLn1;
	
	@FXML
	TextField create_normalEnterAddLn2;
	
	@FXML
	TextField create_normalEnterAddCity;
	
	@FXML
	TextField create_normalEnterAddPostcode;
	
	@FXML
	Button cancelButton;
	
	@FXML
	Button createButton;
	
	@FXML
	ImageView normalUserImage;
	
	@FXML
    private Button imageDrawnButton;

    @FXML
    private Button avator1button;

    @FXML
    private Button avator2button;

    @FXML
    private Button avator3button;

    @FXML
    private Button avator4button;

    @FXML
    private Button avator5button;

    @FXML
    private Button avator6button;

    @FXML
    void avator1Button(ActionEvent event) {
    		
     	normalUserImage.setImage(getImage(1));
    		Storage.storeImagenum(1);
    }

    @FXML
    void avator2Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(2));
    		Storage.storeImagenum(2);
    }

    @FXML
    void avator3Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(3));
		Storage.storeImagenum(3);
    }

    @FXML
    void avator4Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(4));
     	Storage.storeImagenum(4);
    }

    @FXML
    void avator5Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(5));
		Storage.storeImagenum(5);
    }

    @FXML
    void avator6Button(ActionEvent event) {
     	normalUserImage.setImage(getImage(6));
		Storage.storeImagenum(6);
    }
    
    @FXML
    void imageDrawnAction(ActionEvent e) {
     	normalUserImage.setImage(getImage(Storage.getNum()));
		Storage.storeImagenum(Storage.getNum());
    }
    @FXML
    private Image getImage(int i) {
    		String location = "userPhoto/"+String.valueOf(i)+".png";
		File file = new File(location);
        Image image = new Image(file.toURI().toString());
        return image;
    }
	
	@FXML
	private  void handleCreateButtonEvent(ActionEvent e) {
		Librarian l = new Librarian();
		String address = create_normalEnterAddLn1.getText() +"\n," +create_normalEnterAddLn2.getText()+"\n,"+create_normalEnterAddCity.getText()
		+"\n," +create_normalEnterAddPostcode.getText();
		try {
			l.newNormalUser(create_normalEnterUsername.getText(), password.getText(), create_normalEnterFirstname.getText()
					, create_normalEnterLastname.getText(), Integer.parseInt(create_normalEnterMobile.getText()), address, Storage.returnImagenum());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			System.out.println("Mobile number should be int!");
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("libgui-3.fxml"));
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
	@FXML
	private  void handleCancelButtonEvent(ActionEvent e) {
		
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

	public void handleImageEvent(ActionEvent e) {


			
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DrawGUIUser.fxml"));
				 Parent root = (Parent)fxmlLoader.load();
				 Scene scene= new Scene(root); 
				 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
				 window.setScene(scene);
				
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				
			
	}



}
