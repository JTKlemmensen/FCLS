package viewPackage;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LoginView 
{
	private LoginController theController;
	private TextField userTextField;
	private PasswordField passwordField;
	private GridPane root;
	
	public Scene getSceneGUI(LoginController controller)
	{
		theController=controller;
		root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25, 25, 25, 25));
	
		//add username and textfield
		Label userNameLabel = new Label("User Name:");
		root.add(userNameLabel, 0, 0);

		userTextField = new TextField();
		root.add(userTextField, 1, 0);

		//add password and password field
		Label passwordLabel = new Label("Password:");
		root.add(passwordLabel, 0, 1);

		passwordField = new PasswordField();
		root.add(passwordField, 1, 1);
		
		//add login button
		Button loginButton = new Button("Login");
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) 
		    {
		       if(theController.login(userTextField.getText(), passwordField.getText())==false)
		       {
		    	Label warningLabel = new Label("Incorrect Username or Password");
		   		root.add(warningLabel, 0, 4, 2,1);
		       }
		    }
		});
		//add cancel button
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.cancelLogin();
		    }
		});
		
		HBox hbButtonHolder = new HBox(10);
		hbButtonHolder.setAlignment(Pos.BOTTOM_RIGHT);
		hbButtonHolder.getChildren().add(loginButton);
		hbButtonHolder.getChildren().add(cancelButton);
		root.add(hbButtonHolder, 1, 3);
		
		//add cancel button
		Scene scene= new Scene(root, 400, 200);
		scene.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		        loginButton.fire();
		    }
		});
		
		return scene;
	}
}