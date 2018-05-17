package view;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.sellerDataModel;

public class FCLS 
{
	private FCLSController theController;
	private BorderPane root;
	private Label currentUserLabel;
	
	public FCLS(FCLSController controller)
	{
		theController=controller;
	}
	
	public Scene createMainScreenScene()
	{
		root = new BorderPane();
		
		//create top part
		HBox topMenuContainer= new HBox();
		topMenuContainer.setId("top_bar");
		currentUserLabel=new Label("Sælger profil:");
		currentUserLabel.setId("dark_label");
		topMenuContainer.getChildren().add(currentUserLabel);
		
		Hyperlink changeLoginLink=new Hyperlink("Skift sælger");
		changeLoginLink.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) 
		    {
		        //open login screen
		    	theController.startLoginScreen();
		    }
		});
		
		topMenuContainer.getChildren().add(changeLoginLink);
		topMenuContainer.setPrefHeight(26);
		topMenuContainer.setAlignment(Pos.CENTER_RIGHT);
		
		//create bottom part
		HBox bottomPartContainer= new HBox();
		bottomPartContainer.setId("bottom_bar");
		bottomPartContainer.setPrefHeight(26);
		
		//create left actionMenu
		VBox actionMenu= new VBox();
		actionMenu.setPrefWidth(180);
		actionMenu.setId("menu_backdrop");
		actionMenu.setAlignment(Pos.TOP_CENTER);
		actionMenu.setPadding(new Insets(12, 0 ,0 ,0));
		
		//create actionbuttons
		Button searchCustomerButton= new Button("Find Kunde");
		searchCustomerButton.setId("menu_button");
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
	
	public void setView(Pane newPane)
	{
		root.setCenter(newPane);
	}
	
	public void setCurrentUserField(sellerDataModel newSalesPerson)
	{
		currentUserLabel.setText("Sælger profil: "+newSalesPerson.getSalesPersonUsername());
	}
}
