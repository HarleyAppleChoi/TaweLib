/**
@author Iestyn Price
@version 2.2
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
	private boolean laptopButton = false;

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

	// resrve tab
	@FXML
	Tab reserveTab;

	@FXML
	TextField reserveField;

	@FXML
	Button reserveButton;

	// Transaction Tab

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


	
	@FXML
	Button avatar1;
	@FXML
	Button showImage;
	
	@FXML
	Button avatar2;
	
	@FXML
	Button avatar3;
	
	@FXML
	Button avatar4;
	
	@FXML
	Button avatar5;
	
	@FXML
	Button avatar6;
	

	// UserData
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

	@FXML
	ImageView profileImageView;

	// detailscarchtab
	@FXML
	private Tab resourceDetail;

	@FXML // fx:id="resultText"
	private TextArea resultText; // Value injected by FXMLLoader

	@FXML // fx:id="thumbnIlImage"
	private ImageView thumbnIlImage; // Value injected by FXMLLoader

	@FXML // fx:id="resourceID"
	private TextField resourceID; // Value injected by FXMLLoader

	@FXML // fx:id="searchButton"
	private Button searchingButton; // Value injected by FXMLLoader

	@FXML // fx:id="searchButton"
	private Button backButton; // Value injected by FXMLLoader

	
    
    @FXML
    void handleAvatar1(ActionEvent event) {
    		
    	profileImageView.setImage(getImage(1));
    		Storage.storeImagenum(1);
    }

    @FXML
    void handleAvatar2(ActionEvent event) {
    	profileImageView.setImage(getImage(2));
    		Storage.storeImagenum(2);
    }

    @FXML
    void handleAvatar3(ActionEvent event) {
    	profileImageView.setImage(getImage(3));
		Storage.storeImagenum(3);
    }

    @FXML
    void handleAvatar4(ActionEvent event) {
    	profileImageView.setImage(getImage(4));
     	Storage.storeImagenum(4);
    }

    @FXML
    void handleAvatar5(ActionEvent event) {
    	profileImageView.setImage(getImage(5));
		Storage.storeImagenum(5);
    }

    @FXML
    void handleAvatar6(ActionEvent event) {
    	profileImageView.setImage(getImage(6));
		Storage.storeImagenum(6);
    }
    
    @FXML
    void imageDrawnAction(ActionEvent e) {
    	profileImageView.setImage(getImage(Storage.getNum()));
		Storage.storeImagenum(Storage.getNum());
    }
   
    
    private Image getImage(int i) {
    		String location = "userPhoto/"+String.valueOf(i)+".png";
		File file = new File(location);
        Image image = new Image(file.toURI().toString());
        return image;
    }
	
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

	// @FXML private void radioSearchBook(ActionEvent e) {
	// bookRadioButton = !bookRadioButton;
	// }

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
			// bookButton = false;
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
			// dvdButton = false;

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
			// laptopButton = false;
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
	private void handleDrawImage(ActionEvent e) throws SQLException {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DrawGUIUser.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
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

	private Image getImage(String s) {
		String location = s;
		File file = new File(location);
		Image image = new Image(file.toURI().toString());
		return image;
	}

	@SuppressWarnings("resource")
	@FXML
	void search(ActionEvent event) throws Exception {
		SQLHandle sql = new SQLHandle();
		String statement = "select resourceID from book where resourceID ='" + resourceID.getText() + "';";

		try {
			ResultSet r = sql.nonStaticGet(statement);
			if (r.next()) {
				// this is a book

				Resource re = new Resource(r.getInt("book.resourceID"));

				String s = "ID      Title              Author   Publisher  Genre   ISBN  Language Year NumAvCopies Image\n";

				statement = "select * from book,resource where book.resourceID = resource.resourceID and book.resourceID ='"
						+ resourceID.getText() + "';";

				r = sql.nonStaticGet(statement);
				r.next();
				s += String.format("%s %30s %10s %15s %10s %7s %7s %10s %7s\n", r.getInt("book.resourceID"),
						r.getString("resource.title"), r.getString("author"), r.getString("publisher"),
						r.getString("genre"), r.getString("ISBN"), r.getString("language"), r.getInt("year"),
						re.getAvCopies());

				resultText.setText(s);

				thumbnIlImage.setImage(getImage(r.getString("image")));

			} else {
				statement = "select resourceID from DVD where resourceID ='" + resourceID.getText() + "';";
				
				r = sql.nonStaticGet(statement);
				
				if (r.next()) {
					// this is a DVD
					

					statement = "SELECT distinct resource.resourceID,title, director, _language,image," + 
							"    GROUP_CONCAT(distinct subtitle SEPARATOR ',') as subs, " + 
							"    		runtime,year,numAvCopies" + 
							"    		FROM resource, dvd, dvd_subtitle where resource.resourceID = dvd.resourceID and" + 
							"    		dvd.resourceID = dvd_subtitle.resourceID and " + 
							"    	 DVD.resourceID ='" +  resourceID.getText() + "';";
					
					r = sql.nonStaticGet(statement);
					r.next();
					Resource re = new Resource(r.getInt("resource.resourceID"));
					
					String s = "ResourceID      Title       Director       Language          Subtitle      Runtime    Year    AvailableCopies\n";

					s += String.format("%s %20s %10s %18s (%20s) %16s %18s %s20\n", r.getInt("resource.resourceID"),
							r.getString("title"), r.getString("director"), r.getString("_language"),
							r.getString("subs"), r.getInt("runtime"), r.getInt("year"), re.getAvCopies());

					resultText.setText(s);

					thumbnIlImage.setImage(getImage(r.getString("image")));

				} else {
					statement = "select resourceID from laptop where resourceID ='" + resourceID.getText() + "';";
					r = sql.nonStaticGet(statement);
					if (r.next()) {
						// this is a laptop
						
					 	String s = "ResourceID            Title              Manufacturer              Model             OPSystem             Year              AvailableCopies\n";
					 	
					 	statement = "SELECT distinct resource.resourceID,image,title,manufacturer, model, operatingSystem,year,numAvCopies "
				    			+ "FROM resource, laptop where resource.resourceID = laptop.resourceID and laptop.resourceID='" +resourceID.getText() + "';";
					 	r = sql.nonStaticGet(statement);
					 	
					 	while(r.next()) {
					 		Resource re = new Resource(r.getInt("resource.resourceID"));
					        	s +=String.format("%9s %22s %19s %20s %21s %22s %26s\n", r.getInt("resource.resourceID"), r.getString("title"),
					        			r.getString("manufacturer"), r.getString("model"), r.getString("operatingSystem"), r.getInt("year"),
					        			re.getAvCopies());
					        
					 	
					 	resultText.setText(s);
					        
					 	thumbnIlImage.setImage(getImage(r.getString("image")));
					 	}
					} else {
						System.out.println("This resourceID is not in the database");
					}
				}
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void storeAction(ActionEvent event) throws SQLException {
		String query = "UPDATE `user_` SET `image`='" +Storage.returnImagenum()+"';";
		SQLHandle.set(query);
	}

}
