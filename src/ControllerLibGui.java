
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerLibGui {
	// tables need type params but they are unknown atm
	// search tab

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

	@FXML
	private Label statusReturn;

	// pay fine tab

	@FXML
	TextField pay_fineEnterUsername;

	@FXML
	TextField pay_fineEnterPaymentAmount;

	@FXML
	TextField pay_fineShowFineRemaining;

	@FXML
	Button pay_fineGeneratePayment;
	
	@FXML
	Label statusPayFine;

	// request tab

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
		try {
			l.borrow(Integer.parseInt(borrowingEnterSearchQuery.getText()), borrowingEnterBorrowerUsername.getText());
		} catch (IllegalArgumentException e) {
			System.out.println("You cannot borrow!");
		}
	}

	@FXML
	public void returnEvent()  {
		try {
			Librarian l = new Librarian();
			int borrowID = Integer.parseInt(returnEnterResourceId.getText());
			l.returnResource(borrowID);
			Borrowing b = new Borrowing(borrowID);
			returnShowFineAdded.setText(String.valueOf(b.fine()));
		} catch (Exception n) {
			statusReturn.setText("Please fill in the fills!");
		}
	}

	@FXML
	public void handlePayEvent(ActionEvent e)  {
		try {
		SQLHandle c = new SQLHandle();
		Librarian l = new Librarian();
		l.payFine(Integer.parseInt(pay_fineEnterPaymentAmount.getText()), pay_fineEnterUsername.getText());
		NormalUser u = new NormalUser(pay_fineEnterUsername.getText());
		pay_fineShowFineRemaining.setText(String.valueOf(u.getBalance()));
		}catch(IllegalArgumentException rt){
			statusPayFine.setText("The fine number is not a vaild number(0.01~full fine)");
		}catch(Exception r) {
			statusPayFine.setText("Please fill in the fills!");
		}

	}
}
