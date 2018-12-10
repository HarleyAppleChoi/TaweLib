/**
 * Class to control the functioning of the resource details gui
 * and all the functions they can perform.
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControllerResourceDetail {
	//input resource details 
	@FXML 
	private TextArea resultText; 

	@FXML 
	private ImageView thumbnIlImage; 

	@FXML 
	private TextField resourceID; 

	@FXML 
	private Button searchButton; 

	@FXML 
	private Button backButton; 

	/**
	 * Method to locate image in path and return the image
	 * @param s
	 * @return image 
	 */
	
	private Image getImage(String s) {
		String location = s;
		File file = new File(location);
		Image image = new Image(file.toURI().toString());
		return image;
	}
	/**
	 * Method to search through all resources and particular resources
	 * @param e
	 * 
	 */
	@FXML
	private void search(ActionEvent e) throws Exception {
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
				
				r=sql.nonStaticGet(statement);
				
				s += String.format("%s %30s %10s %15s %10s %7s %7s %10s %7s %22s\n", r.getInt("book.resourceID"),
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
					Resource re = new Resource(r.getInt("DVD.resourceID"));
					
					statement = "select * from DVD,resource where DVD.resourceID = resource.resourceID and DVD.resourceID ='"
							+ resourceID.getText() + "';";
					
					r=sql.nonStaticGet(statement);
			    	String s = "ResourceID      Title       Director       Language          Subtitle      Runtime    Year    AvailableCopies\n";

					s += String.format("%s %20s %10s %18s (%20s) %16s %18s %s20\n",r.getInt("resourceID"), r.getString("title"), r.getString("director"), r.getString("_language"), r.getString("subs"),
		        			r.getInt("runtime"), r.getInt("year"), re.getAvCopies());

					resultText.setText(s);

					thumbnIlImage.setImage(getImage(r.getString("image")));

				} else {
					statement = "select resourceID from laptop where resourceID ='" + resourceID.getText() + "';";

					r = sql.nonStaticGet(statement);
					if (r.next()) {
						// this is a laptop
						
						
				}else {
					System.out.println("This resourceID is not in the database");
				}

				} 
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	
}
