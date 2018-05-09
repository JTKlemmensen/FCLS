package viewPackage;

import java.time.LocalDate;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CreateLoanAggrementView
{
	private CreateLoanAggrementController theController;
	
	public CreateLoanAggrementView(CreateLoanAggrementController controller)
	{
		theController=controller;
	}
	
	public StackPane getSceneGUI()
	{
		StackPane root = new StackPane();
		root.setStyle("-fx-background-color: #bdc7cc; -fx-border-color: #828889; -fx-border-width: 2;");
		root.setPadding(new Insets(8));
		
		VBox containerBox=new VBox();
		root.getChildren().add(containerBox);
		
		//create customerinfoGrid
		GridPane customerInformationGrid=new GridPane();
		containerBox.getChildren().add(customerInformationGrid);
		
		Label customerInformationHeader=new Label("Kundeinformation :");
		customerInformationHeader.setId("part_header_label");
		
		Label customerNameHeader=new Label("Navn");
		customerNameHeader.setId("header_label");
		
		Label customerNameLabel=new Label();
		customerNameLabel.textProperty().bind(theController.getCustomer().customerNameProperty());
		
		Label customerAdressHeader=new Label("Addresse");
		customerAdressHeader.setId("header_label");
		
		Label customerAdressLabel=new Label();
		customerAdressLabel.textProperty().bind(theController.getCustomer().customerAddressProperty());
		
		Label customerTlfHeader=new Label("Tlf. nr");
		customerTlfHeader.setId("header_label");
		
		Label customerTlfLabel=new Label();
		customerTlfLabel.textProperty().bind(theController.getCustomer().customerPhoneProperty());
		
		Label customerCPRHeader=new Label("CPR nr.");
		customerCPRHeader.setId("header_label");
		
		Label customerCPRLabel=new Label();
		customerCPRLabel.textProperty().bind(theController.getCustomer().customerCPRProperty());
		
		customerInformationGrid.add(customerInformationHeader, 0, 0);
		customerInformationGrid.add(customerNameHeader, 0, 1);
		customerInformationGrid.add(customerNameLabel, 0, 2);
		customerInformationGrid.add(customerAdressHeader, 1, 1);
		customerInformationGrid.add(customerAdressLabel, 1, 2);
		customerInformationGrid.add(customerTlfHeader, 0, 3);
		customerInformationGrid.add(customerTlfLabel, 0, 4);
		customerInformationGrid.add(customerCPRHeader, 1, 3);
		customerInformationGrid.add(customerCPRLabel, 1, 4);
		
		//create loaninfoPart
		GridPane loanInformationGrid=new GridPane();
		containerBox.getChildren().add(loanInformationGrid);
		containerBox.setMargin(loanInformationGrid, new Insets(6, 0, 6, 0));
		
		Label loanInfoLabel=new Label("låneinfo :");
		loanInfoLabel.setId("part_header_label");
		
		Label carPriceHeader = new Label("købspris");
		carPriceHeader.setId("header_label");
		
		TextField carPriceField = new TextField();
		carPriceField.textProperty().bindBidirectional(theController.carPriceProperty());
		
		Label downPaymentHeader = new Label("kunde udbetaling");
		downPaymentHeader.setId("header_label");
		
		TextField downPaymentField = new TextField();
		downPaymentField.textProperty().bindBidirectional(theController.downPaymentProperty());
		
		
		loanInformationGrid.add(loanInfoLabel, 0, 0);
		loanInformationGrid.add(carPriceHeader, 0, 1);
		loanInformationGrid.add(carPriceField, 0, 2);
		loanInformationGrid.add(downPaymentHeader, 1, 1);
		loanInformationGrid.add(downPaymentField, 1, 2);
		
		//loan duration
		HBox loanContainer= new HBox();
		containerBox.getChildren().add(loanContainer);
		
		VBox dateContainer=new VBox();
		loanContainer.getChildren().add(dateContainer);
		
		Label startDateHeader=new Label("Start Dato");
		startDateHeader.setId("header_label");
		
		DatePicker datePicker=new DatePicker();
		datePicker.setOnAction(event -> {
		    LocalDate date = datePicker.getValue();
		});
		
		dateContainer.getChildren().add(startDateHeader);
		dateContainer.getChildren().add(datePicker);
		
		VBox periodContainer=new VBox();
		
		Label loanPeriodHeader=new Label("Lånets løbetid");
		loanPeriodHeader.setId("header_label");
		
		Slider periodSlider=new Slider();
		periodSlider.setMin(2);
		periodSlider.setMax(10);
		periodSlider.setValue(5);
		periodSlider.setMajorTickUnit(4);
		periodSlider.setMinorTickCount(4);
		periodSlider.setBlockIncrement(1);
		periodSlider.setShowTickLabels(true);
		periodSlider.setSnapToTicks(true);
		periodSlider.setShowTickMarks(true);
		
		periodContainer.getChildren().add(loanPeriodHeader);
		periodContainer.getChildren().add(periodSlider);
		
		loanContainer.getChildren().add(periodContainer);
		//car part
		GridPane carInformationGrid=new GridPane();
		containerBox.getChildren().add(carInformationGrid);

		Label carInformationHeader= new Label("Bil ID");
		carInformationHeader.setId("header_label");
		
		TextField carIDNumberField= new TextField();
		
		carIDNumberField.textProperty().bindBidirectional(theController.carIDProperty());
		
		Button findCarButton=new Button("Find Bil");
		
		Label carInformationLabel=new Label("Bilinformation");
		
		carInformationGrid.add(carInformationHeader, 0, 0);
		carInformationGrid.add(carIDNumberField, 0, 1);
		carInformationGrid.add(findCarButton, 1, 1);
		carInformationGrid.add(carInformationLabel, 2, 1);
		
		//buttons
		HBox buttonHolder=new HBox();
		Button calculateAggrementButton = new Button("Beregn låneaftale");
		calculateAggrementButton.setDisable(true);
		
		calculateAggrementButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.createLoanAggrement();
		    }
		});
		
		Button cancelButton = new Button("Annuller");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.createLoanAggrement();
		    }
		});
		
		buttonHolder.getChildren().add(calculateAggrementButton);
		buttonHolder.getChildren().add(cancelButton);
		
		//setup listener
				Label waitingLabel= new Label("Indhenter kundens kreditvurdering og daglig rente...");
				
				theController.getHandler().canReturnLoanAgreementProperty().addListener(new ChangeListener() {
			        @Override public void changed(ObservableValue o,Object oldVal, 
			                 Object newVal)
			        {
			             if((Boolean)newVal==true)
			             {
			            	 waitingLabel.setText("");
			            	 calculateAggrementButton.setDisable(false);
			             }
			             else
			             {
			            	 waitingLabel.setText("Indhenter kundens kreditvurdering og daglig rente...");
			            	 calculateAggrementButton.setDisable(true);
			             }
			        }
			      });
		
		containerBox.getChildren().add(buttonHolder);
		containerBox.getChildren().add(waitingLabel);
		return root;
	}
}
