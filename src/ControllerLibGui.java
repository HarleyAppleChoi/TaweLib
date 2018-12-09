
/**
@author Iestyn Price
*/

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLibGui {
	// search tab
	private boolean dvdButton = false;
	private boolean bookButton = false;
	private boolean laptopButton = false;
	
	@FXML
	Button searchBook;

	@FXML
	Button searchDvd;

	@FXML
	Button searchLaptop;

	@FXML
	TextField searchQuery;

	@FXML
	Button buttonSearch;
	
	@FXML
	Button buttonInfo;
	
	@FXML
	TextArea basicInfo;
	
	@FXML
	TextArea borrowReturn;
	
	//search button showing resource info
	@FXML
	private void search(ActionEvent e) {
		//if the book flag is true, search is limited to books
		if (bookButton == true) {
			try {
				SQLHandle c = new SQLHandle();
				Search s = new Search();
				basicInfo.setText(s.searchBook(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		//similarly
		} else if (dvdButton == true) {
			try {
				SQLHandle c = new SQLHandle();
				Search r = new Search();
				basicInfo.setText(r.searchDvd(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else if (laptopButton == true) {
			try {
				SQLHandle c = new SQLHandle();
				Search t = new Search();
				basicInfo.setText(t.searchLaptop(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else if (bookButton == false || dvdButton == false || laptopButton == false) {
		try {
			SQLHandle c = new SQLHandle();
			Search p = new Search();
			basicInfo.setText(p.searchResources(searchQuery.getText()));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	}	
	@FXML
	private void searchBook(ActionEvent e) {
		bookButton = !bookButton;
		dvdButton = false;
		laptopButton = false;
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			basicInfo.setText(s.searchBook(searchQuery.getText()));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	@FXML
	private void searchDvd(ActionEvent e) {
		dvdButton = !dvdButton;
		laptopButton = false;
		bookButton = false;
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			basicInfo.setText(s.searchDvd(searchQuery.getText()));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	@FXML
	private void searchLaptop(ActionEvent e) {
			laptopButton = !laptopButton;
			dvdButton = false;
			bookButton = false;
			try {
				SQLHandle c = new SQLHandle();
				Search s = new Search();
				basicInfo.setText(s.searchLaptop(searchQuery.getText()));
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	// borrowing tab

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

	// create resource tab

	@FXML
	Button create_resourceCreateBook;

	@FXML
	Button create_resourceCreateDvd;

	@FXML
	Button create_resouceCreateLaptop;

	// edit resource tab

	@FXML
	TextField edit_resourceEnterResourceId;

	@FXML
	Button edit_resourceBeginEdit;

	// create user tab

	@FXML
	Button create_userCreateNormal;

	@FXML
	Button create_userCreateLibrarian;

	// edit user tab

	@FXML
	TextField edit_userEnterUsername;

	@FXML
	Button edit_userBeginEdit;

	// return resource tab

	@FXML
	TextField returnEnterResourceId;

	@FXML
	Button returnGenerateReturn;

	@FXML
	TextField returnShowFineAdded;

	// pay fine tab

	@FXML
	TextField pay_fineEnterUsername;

	@FXML
	TextField pay_fineEnterPaymentAmount;

	@FXML
	TextField pay_fineShowFineRemaining;

	@FXML
	Button pay_fineGeneratePayment;

	// request tab

	@FXML
	TextField requestEnterUsername;

	@FXML
	TextField requestEnterResourceId;

	@FXML
	Button requestGenerateRequest;
	
	//copy details tab
	
	@FXML
	Button buttonSearchCopy;
	
	@FXML
	TextField searchQueryCopy;
	
	@FXML
	TextArea textBorrow;
	
	@FXML
	TextArea textReturn;
	
	@FXML
	private void searchAdd(ActionEvent e) {
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			//textBorrow.setText(s.borrowSearch(searchQueryCopy.getText()));
			//textReturn.setText(s.returnSearch(searchQueryCopy.getText()));
		} catch (Exception ex) {
			ex.printStackTrace();;
		}
	}
	//overdue tab
	
	@FXML
	Button buttonOverdue;
	
	@FXML
	TextArea textOverdue;
	
	@FXML
	public void overdue(ActionEvent e) {
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			//textOverdue.setText(s.overdueSearch());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//unorganised methods:

	@FXML
	public void handleCreateBookEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createbook-3.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	public void handleCreateDVDResourceEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createdvd-2.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	public void handleCreateLaptopEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createlaptop-2.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	public void createNormalUserEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createuser.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	public void createLibrianUserEvent(ActionEvent e) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createlibrarian.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(scene);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void handleResourceIDInput(ActionEvent e) throws SQLException {
		SQLHandle sql = new SQLHandle();
		String statement = "select resourceID from book where resourceID ='" + edit_resourceEnterResourceId.getText()
				+ "';";
		ResultSet r = sql.nonStaticGet(statement);
		if (r.next()) {
			// this is a book
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editbook.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Scene scene = new Scene(root);
				Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
				window.setScene(scene);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			statement = "select resourceID from DVD where resourceID ='" + edit_resourceEnterResourceId.getText()
					+ "';";
			r = sql.nonStaticGet(statement);
			if (r.next()) {
				// this is a DVD
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editdvd.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Scene scene = new Scene(root);
					Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
					window.setScene(scene);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				statement = "select resourceID from laptop where resourceID ='" + edit_resourceEnterResourceId.getText()
						+ "';";
				r = sql.nonStaticGet(statement);
				if (r.next()) {
					// this is a laptop
					try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editlaptop.fxml"));
						Parent root = (Parent) fxmlLoader.load();
						Scene scene = new Scene(root);
						Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
						window.setScene(scene);

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					System.out.println("This resourceID is not in the database");
				}
			}
		}
	}

	@FXML
	public void borrowEvent() throws NumberFormatException, Exception {
		Librarian l = new Librarian();
		try{
			l.borrow(Integer.parseInt(borrowingEnterSearchQuery.getText()), 
					borrowingEnterBorrowerUsername.getText());
		}catch(IllegalArgumentException e) {
			System.out.println("You cannot borrow!");
		}
		}
	
		@FXML
		public void returnEvent() throws NumberFormatException, Exception {
			Librarian l = new Librarian();
			l.returnResource(Integer.parseInt(returnEnterResourceId.getText()));
		}
	
	@FXML
	public void handlePayEvent(ActionEvent e) throws NumberFormatException, Exception {
		SQLHandle c = new SQLHandle();
		Librarian l = new Librarian();
		l.payFine(Integer.parseInt(pay_fineEnterPaymentAmount.getText()), pay_fineEnterUsername.getText());

}
}


