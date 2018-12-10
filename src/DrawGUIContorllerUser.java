import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class DrawGUIContorllerUser {

	@FXML
	private Button drawCircleButton;
	@FXML
	private Button drawStraightLineButton;
	@FXML
	private Button drawLineButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button clearDrawingButton;
	@FXML
	private ColorPicker changeColorMenuButton;

	@FXML
	private Canvas drawCanvas;

	WritableImage wim = new WritableImage(500, 500);
	Color color;

	/**
	 * This allow user to draw a circle
	 * 
	 * @param e
	 *            when button pressed
	 */
	@FXML
	private void handleDrawCircleEvent(ActionEvent e) {

		drawCircle();
	}

	/**
	 * This allow user to draw straight lines
	 * 
	 * @param e
	 *            when button pressed
	 */
	@FXML
	private void handleDrawStraightLineEvent(ActionEvent e) {
		drawLine();
	}

	/**
	 * This allow user to draw straight lines
	 * 
	 * @param e
	 *            when button pressed
	 */
	@FXML
	private void handleDrawLineEvent(ActionEvent e) {
		drawParticalTrace();
	}

	/**
	 * This allow user to save the event
	 * 
	 * @param e
	 *            when button pressed
	 */
	@FXML
		private void handleSaveEventUser(ActionEvent e) throws SQLException {
			save();
			
		
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("usergui.fxml"));
					 Parent root = (Parent)fxmlLoader.load();
					 Scene scene= new Scene(root); 
					 Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
					 window.setScene(scene);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		
		}
		

	/**
	 * This allow user to clear a canvas
	 * 
	 * @param e
	 *            when button pressed
	 */
	@FXML
	private void handleClearEvent(ActionEvent e) {
		double height = drawCanvas.getHeight();
		double width = drawCanvas.getWidth();
		GraphicsContext gc = drawCanvas.getGraphicsContext2D();

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, height, width);

	}

	/**
	 * change the colour of the trace.
	 * @param e
	 */
	@FXML
	private void handleChangeColorEvent(ActionEvent e) {
		color = changeColorMenuButton.getValue();

	}

	/**
	 * draw line between two point
	 */
	public void drawLine() {

		GraphicsContext gc = drawCanvas.getGraphicsContext2D();
		gc.setStroke(color);
		gc.setLineWidth(2);

		// Start and draw a line when mouse is pressed
		drawCanvas.setOnMousePressed(e -> {
			gc.beginPath();
			gc.lineTo(e.getX(), e.getY());
			gc.stroke();

		});

		drawCanvas.setOnMouseDragged(e -> {

		});

		drawCanvas.setOnMouseReleased(e -> {

			gc.lineTo(e.getX(), e.getY());
			gc.stroke();
			gc.closePath();
			gc.beginPath();
		});

	}

	/**
	 * draw particle trace
	 */
	private void drawParticalTrace() {
		GraphicsContext gc = drawCanvas.getGraphicsContext2D();
		gc.setStroke(color);
		gc.setLineWidth(1);

		drawCanvas.setOnMousePressed(e -> {
			gc.beginPath();
			gc.lineTo(e.getX(), e.getY());
		});

		drawCanvas.setOnMouseReleased(e -> {
			gc.lineTo(e.getX(), e.getY());
			gc.stroke();
			gc.closePath();
		});

		drawCanvas.setOnMouseDragged(e -> {
			gc.lineTo(e.getX(), e.getY());
			gc.stroke();

		});
	}

	/**
	 * draw  circle on the press point.
	 */
	private void drawCircle() {
		GraphicsContext g = drawCanvas.getGraphicsContext2D();
		// gc.setStroke(Color.RED);
		g.setFill(color);

		drawCanvas.setOnMousePressed(e -> {

			g.fillOval(e.getX(), e.getY(), 30, 30);

		});

		drawCanvas.setOnMouseDragged(e -> {

			// g.fillOval(e.getX(), e.getY(), 10, 10);

		});
		drawCanvas.setOnMouseReleased(e -> {

			g.fillOval(e.getX(), e.getY(), 30, 30);

		});

	}

	/**
	 * this store the image in the system with unique path
	 * @throws SQLException
	 */
	private void save() throws SQLException {

		//generate the unique number(name)
		SQLHandle sql = new SQLHandle();
		ResultSet r = sql.nonStaticGet("Select max(image) from user_;");
		int number = 0;
		while (r.next()) {
			number = r.getInt("max(image)") + 1;
		}

		Storage.storeNum(number);

		//store pic from drawing sub program.
		drawCanvas.snapshot(null, wim);
		String filePath = "userPhoto/" + String.valueOf(number) + ".png";
		System.out.println(filePath);
		File file = new File(filePath);

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
		} catch (Exception s) {

		}

	}
}




