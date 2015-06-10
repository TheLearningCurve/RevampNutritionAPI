//	Created by Kyle Wolff and Brandon VanderMey 6/9/2015

package nutritionAPIV2_view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	public static void main(String[] args) 
	{
        launch(args);
    }

	@Override
	public void start(Stage stage) 
	{
		try 
		{
			Parent parent = FXMLLoader.load(Main.class.getResource("Frame.fxml"));
			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("Frame.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
