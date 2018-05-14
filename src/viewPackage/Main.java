package viewPackage;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setScene(FCLSController.INSTANCE.getView().createMainScreenScene());
			setupStage(primaryStage);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void setupStage(Stage primaryStage)
	{
		primaryStage.setTitle("FCLS");
		Image image= new Image(getClass().getResourceAsStream("ferrariLogo.png"));
		primaryStage.getIcons().add(image);
	}
}
