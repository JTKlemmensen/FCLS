package viewPackage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainScreenView 
{
	private MainScreenController theController;
	private BorderPane root;
	
	public MainScreenView(MainScreenController controller)
	{
		theController=controller;
	}
	
	public Scene createMainScreenScene()
	{
		root = new BorderPane();
		
		//create top part
		HBox topMenuContainer= new HBox();
		topMenuContainer.setStyle("-fx-background-color: #8a909b; -fx-border-color: #93959b; -fx-border-width: 1;");
		topMenuContainer.getChildren().add(new Label("Current user : admin"));
		topMenuContainer.getChildren().add(new Hyperlink("Change User"));
		topMenuContainer.setPrefHeight(26);
		topMenuContainer.setAlignment(Pos.CENTER_RIGHT);
		
		//create bottom part
		HBox bottomPartContainer= new HBox();
		bottomPartContainer.setStyle("-fx-background-color: #8a909b; -fx-border-color: #93959b; -fx-border-width: 1;");
		bottomPartContainer.setPrefHeight(26);
		
		//create left actionMenu
		VBox actionMenu= new VBox();
		actionMenu.setPrefWidth(180);
		actionMenu.setStyle("-fx-background-color: linear-gradient(#939fa5 0%, #6e7573 50%, #939fa5 100%); -fx-border-color: #828889; -fx-border-width: 2;");
		actionMenu.setAlignment(Pos.TOP_CENTER);
		actionMenu.setPadding(new Insets(12, 0 ,0 ,0));
		
		//create actionbuttons
		Button searchCustomerButton= new Button("Find Kunde");
		searchCustomerButton.setPadding(new Insets(4));
		searchCustomerButton.setPrefSize(120, 30);
		
		searchCustomerButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.openSearchCustomer();
		    }
		});
		
		actionMenu.getChildren().add(searchCustomerButton);
		actionMenu.setMargin(searchCustomerButton, new Insets(6, 0, 6, 0));
		
		//set borderParts
		root.setTop(topMenuContainer);
		root.setBottom(bottomPartContainer);
		root.setLeft(actionMenu);
		
		//set scene
		Scene scene = new Scene(root,960,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		return scene;
	}
	
	public void setScene(StackPane newPane)
	{
		root.setCenter(newPane);
	}
}
