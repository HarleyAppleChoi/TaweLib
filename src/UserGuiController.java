/**
 * This class control the user interface GUI.
@author Iestyn Price, Hau Yi Choi
@version 2.2
 * UserGuiController.java
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
	
	//Default search setting
	private boolean bookButton = false;
	private boolean dvdButton = false;
	private boolean laptopButton = false;

	@FXML
	private Tab searchTab;

	@FXML
	private Tab editImageTab;
	@FXML
	private Button drawProfileImage;

	@FXML
	private Button changeProfileImageButton;

	@FXML
	private TextField searchQuery;

	@FXML
	private Button searchButton;

	@FXML
	private Button searchBook;

	@FXML
	private Button searchDvd;

	@FXML
	private Button searchlaptop;

	@FXML
	private TextField searchResource;

	@FXML
	TextArea userSearch;

	// resrve tab
	@FXML
	private Tab reserveTab;

	@FXML
	private TextField reserveField;

	@FXML
	private Button reserveButton;

	// Transaction Tab

	@FXML
	private Tab dashboardTab;

	@FXML
	private TextArea transaction;

	@FXML
	private TextArea borrowItem;

	@FXML
	private TextArea reserved;

	@FXML
	private TextArea requested;

	@FXML
	private TextField balance;

	@FXML
	private Button avatar1;
	@FXML
	private Button showImage;

	@FXML
	private Button avatar2;

	@FXML
	private Button avatar3;

	@FXML
	private Button avatar4;

	@FXML
	private Button avatar5;

	@FXML
	private Button avatar6;

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
	private ImageView profileImageView;

	// detailscarchtab
	@FXML
	private Tab resourceDetail;

	@FXML
	private TextArea resultText;

	@FXML
	private ImageView thumbnIlImage;

	@FXML
	private TextField resourceID;

	@FXML
	private Button searchingButton;

	@FXML
	private Button backButton;

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void handleAvatar1(ActionEvent event) {

		profileImageView.setImage(getImage(1));
		Storage.storeImagenum(1);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void handleAvatar2(ActionEvent event) {
		profileImageView.setImage(getImage(2));
		Storage.storeImagenum(2);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void handleAvatar3(ActionEvent event) {
		profileImageView.setImage(getImage(3));
		Storage.storeImagenum(3);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void handleAvatar4(ActionEvent event) {
		profileImageView.setImage(getImage(4));
		Storage.storeImagenum(4);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void handleAvatar5(ActionEvent event) {
		profileImageView.setImage(getImage(5));
		Storage.storeImagenum(5);
	}

	/**
	 * That let the user choose one of the avatar image.
	 * 
	 * @param event
	 *            when the button is clicked
	 */
	@FXML
	private void handleAvatar6(ActionEvent event) {
		profileImageView.setImage(getImage(6));
		Storage.storeImagenum(6);
	}

	/**
	 * Show and select a custom draw image.  .
	 * @param e when the button is clicked
	 */
	@FXML
	private void imageDrawnAction(ActionEvent e) {
		profileImageView.setImage(getImage(Storage.returntNum()));
		Storage.storeImagenum(Storage.returntNum());
	}

	
	/**
	 * get the image from location
	 * @param i number of photo that is created
	 * @return an Image instance that is the user custom image
	 */
	private Image getImage(int i) {
		String location = "userPhoto/" + String.valueOf(i) + ".png";
		File file = new File(location);
		Image image = new Image(file.toURI().toString());
		return image;
	}

	/**
	 * get the user info
	 * @param e when the button pressed
	 * @throws Exception when complicated exception occur
	 */
	@FXML
	private void userDataAction(ActionEvent e) throws Exception {
		NormalUser u = new NormalUser(Storage.returnUsername());
		userDataUsername.setText(u.getUsername());
		userDataFirstName.setText(u.getFirstName());
		userDataLastName.setText(u.getLastName());
		userDataMobileNumber.setText(String.valueOf(u.getMobileNo()));
		userDataAddress.setText(u.getAddress());

		// Image
		profileImageView.setImage(u.getUserImage());

	}

	/**
	 * initialise dashboard for normal user with information about transaction,
	 * borrowedItem,reserved , requested Item abd balance of normalUser
	 *
	 */
	@FXML
	private void dashBoard(ActionEvent e) throws Exception {
		SQLHandle sql = new SQLHandle();

		String username = Storage.returnUsername();
		NormalUser u = new NormalUser(username);
		String statement = "SELECT * FROM `request_item` WHERE username = '" + username + "';";
		ResultSet r = sql.nonStaticGet(statement);
		String request = "ResourceID:";
		while (r.next()) {
			request += String.valueOf(r.getInt("resourceID")) + "\n";
		}

		requested.setText(request);

		statement = "SELECT * FROM `reserved_item` where username = '" + username + "';";
		r = sql.nonStaticGet(statement);
		String reserve = "ResourceID:";
		while (r.next()) {
			reserve += String.valueOf(r.getInt("resourceID")) + "\n";
		}
		reserved.setText(reserve);

		/*
		 * statement = "select borrowingID from current_borrowing where username = '" +
		 * username + "';"; r = sql.nonStaticGet(statement); String borrow =
		 * "ResourceID:"; while(r.next()) { b += String.valueOf(r.getInt("resourceID"))
		 * + "\n"; } reserved.setText(reserve);
		 */

		balance.setText(String.valueOf(u.getBalance()));
		transaction.setText(u.transactionHistory());
		borrowItem.setText(u.getBorrowedList());
		/*
		 * 
		 * reserved.setText(u.getReservedItem());
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
	}

	/**
	 * display box where user can enter dvd query. Query is then sent to searchdvd
	 * method.
	 * 
	 */
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

	/**
	 * display box where user can enter laptop query. Query is then sent to
	 * searchLaptop method.
	 * 
	 */
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

	/**
	 * display box where user can enter book query. Query is then sent to searchBook
	 * method.
	 * 
	 */
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

	/**
	 * awt information from database about resorces , and check if the resource book
	 * , dvd ,or laptop. if the resources none of the above retuen to search box.
	 * @param e when button are pressed
	 */
	@FXML
	private void loadDataFromDb(ActionEvent e) {
		
		//ask button is in which state and then show the result base on that
		
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

	/**
	 * Direct user to drawing program
	 * @param e when button are pressed
	 * @throws SQLException When SQLExpection occur
	 */
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

	

	/**
	 * That allow user to reserve a resource base on resourceID.
	 * @param e when button are pressed
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@FXML
	public void reserveAction(ActionEvent e) throws NumberFormatException, Exception {

		Librarian l = new Librarian();
		l.request(Integer.parseInt(reserveField.getText()), Storage.returnUsername());
	}

	/**
	 * That return an image that the user drawn.
	 * @param s
	 * @return
	 */
	private Image getImage(String s) {
		String location = s;
		File file = new File(location);
		Image image = new Image(file.toURI().toString());
		return image;
	}

	/**
	 * That is the button that show a info of that particular resource.
	 * @param event
	 * @throws Exception
	 */
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

					statement = "SELECT distinct resource.resourceID,title, director, _language,image,"
							+ "    GROUP_CONCAT(distinct subtitle SEPARATOR ',') as subs, "
							+ "    		runtime,year,numAvCopies"
							+ "    		FROM resource, dvd, dvd_subtitle where resource.resourceID = dvd.resourceID and"
							+ "    		dvd.resourceID = dvd_subtitle.resourceID and " + "    	 DVD.resourceID ='"
							+ resourceID.getText() + "';";

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
								+ "FROM resource, laptop where resource.resourceID = laptop.resourceID and laptop.resourceID='"
								+ resourceID.getText() + "';";
						r = sql.nonStaticGet(statement);

						while (r.next()) {
							Resource re = new Resource(r.getInt("resource.resourceID"));
							s += String.format("%9s %22s %19s %20s %21s %22s %26s\n", r.getInt("resource.resourceID"),
									r.getString("title"), r.getString("manufacturer"), r.getString("model"),
									r.getString("operatingSystem"), r.getInt("year"), re.getAvCopies());

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

	SQLHandle sql = new SQLHandle();

	
	/**
	 * Store the avatar image that the user choose.
	 * @param event
	 * @throws SQLException
	 */
	@FXML
	void storeAction(ActionEvent event) throws SQLException {

		String query = "UPDATE `user_` SET `image`='" + Storage.returnImagenum() + "' where username = '"
				+ Storage.returnUsername() + "';";
		sql.set(query);
	}

}
