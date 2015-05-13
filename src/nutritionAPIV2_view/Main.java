package nutritionAPIV2_view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/* 
 * Created by Kyle Wolff May 13 2015
 */
public class Main extends Application
{
	public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }
		

	@Override
	public void start(Stage stage) {
		
		AnchorPane page;
		
		try {
			page = (AnchorPane) FXMLLoader.load(Main.class.getResource("SearchPanel.fxml"));
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("SearchPanel.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
