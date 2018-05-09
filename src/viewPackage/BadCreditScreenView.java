package viewPackage;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BadCreditScreenView 
{
	private BadCreditScreenController theController;
	
	public Scene createLoginScene(BadCreditScreenController controller)
	{
		theController=controller;
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(25, 25, 25, 25));
	
		
		//add username and textfield
		Label warningLabel = new Label("Kunde er registreret som dårlig betaler, lånetilbud er afvist");
		root.getChildren().add(warningLabel);

		Button acceptButton = new Button("Acceptér");
		acceptButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.warningAccepted();
		    }
		});
		
		
		root.getChildren().add(acceptButton);
		
		//add cancel button
		Scene scene= new Scene(root, 400, 200);
		
		return scene;
	}
}
