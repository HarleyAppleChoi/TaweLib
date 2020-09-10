
/**
 * The  main class that start the program. The class creates a window and display all the information about library system management. 
 * 
 * @author Iestyn Price
 * @version 3.0
 */

import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws SQLException {
		Librarian b = new Librarian();
		b.newLibrarian("admin", "admin", "lib", "lib", 3213, "lib", 2, "10-10-10");

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("logingui.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
