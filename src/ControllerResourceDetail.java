
/**
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

	@FXML // fx:id="resultText"
	private TextArea resultText; // Value injected by FXMLLoader

	@FXML // fx:id="thumbnIlImage"
	private ImageView thumbnIlImage; // Value injected by FXMLLoader

	@FXML // fx:id="resourceID"
	private TextField resourceID; // Value injected by FXMLLoader

	@FXML // fx:id="searchButton"
	private Button searchButton; // Value injected by FXMLLoader

	@FXML // fx:id="searchButton"
	private Button backButton; // Value injected by FXMLLoader

	private Image getImage(String s) {
		String location = s;
		File file = new File(location);
		Image image = new Image(file.toURI().toString());
		return image;
	}

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void backAction(ActionEvent event) {

	}

}
