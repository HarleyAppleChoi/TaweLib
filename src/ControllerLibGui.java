/**
 * ControllerLibGui.java
 * @author Iestyn Price modified  by James Hogg
 * @version 2.1
 */


import java.io.File;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerLibGui {
	
	private boolean dvdButton = false;
	private boolean bookButton = false;
	private boolean laptopButton = false;
    
	private Image getImage(String s) {
		String location = s;
		File file = new File(location);
		Image image = new Image(file.toURI().toString());
		return image;
	}
	


    // creat search tab
	@FXML
	private Button searchBook;

	@FXML
	private Button buttonOverdue;
	
	@FXML
	private TextArea textOverdue;
	
	@FXML
	private Button searchDvd;

	@FXML
	private Button searchLaptop;

	@FXML
	private TextField searchQuery;

	@FXML
	private Button buttonSearch;
	
	@FXML
	private Button buttonInfo;
	
	@FXML
	private TextArea basicInfo;
	
	@FXML
	private TextArea borrowReturn;
	@FXML
	private TextField borrowingEnterBorrowerUsername;

	@FXML
	private TextField borrowingEnterSearchQuery;

	@FXML
	private RadioButton borrowingSelectBook;

	@FXML
	private RadioButton borrowingSelectDvd;

	@FXML
	private RadioButton borrowingSelectLaptop;

	@FXML
	private Button borrowingGenerateBorrow;

	// create resource tab

	@FXML
	private Button create_resourceCreateBook;

	@FXML
	private Button create_resourceCreateDvd;

	@FXML
	private Button create_resouceCreateLaptop;

	// edit resource tab

	@FXML
	private TextField edit_resourceEnterResourceId;

	@FXML
	private Button edit_resourceBeginEdit;

	// create user tab

	@FXML
	private Button create_userCreateNormal;

	@FXML
	private Button create_userCreateLibrarian;

	// edit user tab

	@FXML
	private TextField edit_userEnterUsername;

	@FXML
	private Button edit_userBeginEdit;

	// return resource tab

	@FXML
	private TextField returnEnterResourceId;

	@FXML
	private Button returnGenerateReturn;

	@FXML
	private TextField returnShowFineAdded;

	@FXML
	private Label statusReturn;

	// pay fine tab

	@FXML
	private TextField pay_fineEnterUsername;

	@FXML
	private TextField pay_fineEnterPaymentAmount;

	@FXML
	private TextField pay_fineShowFineRemaining;

	@FXML
	private Button pay_fineGeneratePayment;
	
	@FXML
	private Label statusPayFine;

	// request tab

	@FXML
	private TextField requestEnterUsername;

	@FXML
	private TextField requestEnterResourceId;

	@FXML
	private Button requestGenerateRequest;
	
	//copy details tab
	
	@FXML
	private Button buttonSearchCopy;
	
	@FXML
	private TextField searchQueryCopy;
	
	@FXML
	private TextArea textBorrow;
	
	@FXML
	private TextArea textReturn;
	
	@FXML
	private Label labelBorrow;
	
	@FXML
	private Label labelReturn;
	
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
		//similarly this detects the dvd flag
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
			//this checks for all flags off, and searches the 
			//linked general attributes of every resource
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
	//button searches book table only, for more book details
	private void searchBook(ActionEvent e) {
		bookButton = !bookButton;
		dvdButton = false;
		laptopButton = false;
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			basicInfo.setText(s.searchBook(searchQuery.getText()));
			bookButton = false;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	@FXML
	//searches dvd table only, providing extra info
	private void searchDvd(ActionEvent e) {
		dvdButton = !dvdButton;
		laptopButton = false;
		bookButton = false;
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			basicInfo.setText(s.searchDvd(searchQuery.getText()));
			dvdButton = false;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	@FXML
	//searches laptop table only, providing extra info
	private void searchLaptop(ActionEvent e) {
			laptopButton = !laptopButton;
			dvdButton = false;
			bookButton = false;
			try {
				SQLHandle c = new SQLHandle();
				Search s = new Search();
				basicInfo.setText(s.searchLaptop(searchQuery.getText()));
				laptopButton = false;
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	// borrowing tab

	
	@FXML
	//displays borrow and return histories
	private void searchAdd(ActionEvent e) {
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			textBorrow.setText(s.borrowSearch(searchQueryCopy.getText()));
			textReturn.setText(s.returnSearch(searchQueryCopy.getText()));
		} catch (Exception ex) {
			ex.printStackTrace();;
		}
	}
	
	
	//overdue tab
	@FXML
	//method for displaying overdue copies
	public void overdue(ActionEvent e) {
		try {
			SQLHandle c = new SQLHandle();
			Search s = new Search();
			textOverdue.setText(s.overdueSearch());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//more info tab, used for thumbnail image
	
	@FXML
	Button buttonSearchMore;
	
	@FXML
	private void buttonSearchMore(ActionEvent e) throws Exception {
		SQLHandle sql = new SQLHandle();
		String statement = "select resourceID from book where resourceID ='" + searchQueryMore.getText() + "';";

		try {
			ResultSet r = sql.nonStaticGet(statement);
			if (r.next()) {
				// this is a book

				Resource re = new Resource(r.getInt("book.resourceID"));

				String s = "ID   Title  Author   Publisher  Genre   ISBN  Language Year NumAvCopies\n";

				statement = "select * from book,resource where book.resourceID = resource.resourceID and book.resourceID ='"
						+ searchQueryMore.getText() + "';";

				r = sql.nonStaticGet(statement);
				r.next();
				s += String.format("%s %10s %10s %10s %10s %7s %7s %10s %7s\n", r.getInt("book.resourceID"),
						r.getString("resource.title"), r.getString("author"), r.getString("publisher"),
						r.getString("genre"), r.getString("ISBN"), r.getString("language"), r.getInt("year"),
						re.getAvCopies());

				resultText.setText(s);

				thumbnailImage.setImage(getImage(r.getString("image")));

			} else {
				statement = "select resourceID from DVD where resourceID ='" + searchQueryMore.getText() + "';";
				
				r = sql.nonStaticGet(statement);
				
				if (r.next()) {
					// this is a DVD
					

					statement = "SELECT distinct resource.resourceID,title, director, _language,image," + 
							"    GROUP_CONCAT(distinct subtitle SEPARATOR ',') as subs, " + 
							"    		runtime,year,numAvCopies" + 
							"    		FROM resource, dvd, dvd_subtitle where resource.resourceID = dvd.resourceID and" + 
							"    		dvd.resourceID = dvd_subtitle.resourceID and " + 
							"    	 DVD.resourceID ='" +  searchQueryMore.getText() + "';";
					
					r = sql.nonStaticGet(statement);
					r.next();
					Resource re = new Resource(r.getInt("resource.resourceID"));
					
					String s = "ResourceID    Title    Director    Language   Subtitle      Runtime    Year    AvailableCopies\n";

					s += String.format("%s %20s %10s %18s (%20s) %16s %18s %s20\n", r.getInt("resource.resourceID"),
							r.getString("title"), r.getString("director"), r.getString("_language"),
							r.getString("subs"), r.getInt("runtime"), r.getInt("year"), re.getAvCopies());

					resultText.setText(s);

					thumbnailImage.setImage(getImage(r.getString("image")));

				} else {
					statement = "select resourceID from laptop where resourceID ='" + searchQueryMore.getText() + "';";
					r = sql.nonStaticGet(statement);
					if (r.next()) {
						// this is a laptop
						
					 	String s = "ResourceID            Title              Manufacturer              Model             OPSystem             Year              AvailableCopies\n";
					 	
					 	statement = "SELECT distinct resource.resourceID,image,title,manufacturer, model, operatingSystem,year,numAvCopies "
				    			+ "FROM resource, laptop where resource.resourceID = laptop.resourceID and laptop.resourceID='" +searchQueryMore.getText() + "';";
					 	r = sql.nonStaticGet(statement);
					 	
					 	while(r.next()) {
					 		Resource re = new Resource(r.getInt("resource.resourceID"));
					        	s +=String.format("%9s %22s %19s %20s %21s %22s %26s\n", r.getInt("resource.resourceID"), r.getString("title"),
					        			r.getString("manufacturer"), r.getString("model"), r.getString("operatingSystem"), r.getInt("year"),
					        			re.getAvCopies());
					 	resultText.setText(s);				        
					 	thumbnailImage.setImage(getImage(r.getString("image")));
					 	}
					} else {
						System.out.println("This resourceID is not in the database");
					}
				}
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}	
	@FXML
	TextField searchQueryMore;
	
	@FXML
	TextArea resultText;
	
	@FXML
	ImageView thumbnailImage;
	
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
		//SQLHandle c = new SQLHandle();
		Librarian l = new Librarian();
		l.payFine(Integer.parseInt(pay_fineEnterPaymentAmount.getText()), pay_fineEnterUsername.getText());
		
		NormalUser u = new NormalUser(pay_fineEnterUsername.getText());
		pay_fineShowFineRemaining.setText(String.valueOf(u.getBalance()));
		}catch(IllegalArgumentException rt){
			//statusPayFine.setText("The fine number is not a vaild number(0.01~full fine)");
		}catch(Exception r) {
			//statusPayFine.setText("Please fill in the fills!");
		}
	}
}
