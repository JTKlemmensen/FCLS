package view;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.CustomerDataModel;
import logic.LoanAgreementDataModel;

public class CreateLoanAgreementView implements View
{
	private CreateLoanAgreementController theController;
	private VBox warningContainer;
	
	public CreateLoanAgreementView(CreateLoanAgreementController controller)
	{
		theController=controller;
	}
	
	public GridPane getViewContent()
	{
		GridPane root = new GridPane();
		root.setId("view_screen");
		root.setAlignment(Pos.CENTER);

		VBox containerBox=new VBox();
		root.getChildren().add(containerBox);
		containerBox.getChildren().add(createCustomerInfoGrid());
		containerBox.getChildren().add(createLoanInfoGrid());
		containerBox.getChildren().add(createLoanPeriodContainer());
		containerBox.getChildren().add(createCarInfoGrid());
		containerBox.getChildren().add(createButtonContainer());
		
		return root;
	}
	
	private GridPane createCustomerInfoGrid()
	{
		CustomerDataModel customer=theController.getHandler().getLoanAgreementDataModel().getCustomer();
		GridPane customerInformationGrid=new GridPane();
		
		Label customerInformationHeader=new Label("Kundeinformation :");
		customerInformationHeader.setId("part_header_label");
		
		Label customerNameHeader=new Label("Navn");
		customerNameHeader.setId("header_label");
		
		Label customerNameLabel=new Label();
		customerNameLabel.textProperty().bind(Bindings.concat(customer.firstNameProperty(), " ", customer.lastNameProperty()));
		
		Label customerAdressHeader=new Label("Addresse");
		customerAdressHeader.setId("header_label");
		
		Label customerAdressLabel=new Label();
		customerAdressLabel.textProperty().bind(customer.addressProperty());
		
		Label customerTlfHeader=new Label("Tlf. nr");
		customerTlfHeader.setId("header_label");
		
		Label customerTlfLabel=new Label();
		customerTlfLabel.textProperty().bind(customer.phoneProperty());
		
		Label customerCPRHeader=new Label("CPR nr.");
		customerCPRHeader.setId("header_label");
		
		Label customerCPRLabel=new Label();
		customerCPRLabel.textProperty().bind(customer.CPRProperty());
		
		customerInformationGrid.add(customerInformationHeader, 0, 0);
		customerInformationGrid.add(customerNameHeader, 0, 1);
		customerInformationGrid.add(customerNameLabel, 0, 2);
		customerInformationGrid.add(customerAdressHeader, 1, 1);
		customerInformationGrid.add(customerAdressLabel, 1, 2);
		customerInformationGrid.add(customerTlfHeader, 0, 3);
		customerInformationGrid.add(customerTlfLabel, 0, 4);
		customerInformationGrid.add(customerCPRHeader, 1, 3);
		customerInformationGrid.add(customerCPRLabel, 1, 4);
		
		return customerInformationGrid;
	}
	
	private GridPane createLoanInfoGrid()
	{
		LoanAgreementDataModel loanAgreement=theController.getHandler().getLoanAgreementDataModel();
		
		GridPane loanInformationGrid=new GridPane();
		loanInformationGrid.setPadding(new Insets(8, 0, 0, 0));
		
		Label loanInfoLabel=new Label("Låneinfo :");
		loanInfoLabel.setId("part_header_label");
		
		Label carPriceHeader = new Label("Købspris");
		carPriceHeader.setId("header_label");
		
		TextField carPriceField = new TextField(loanAgreement.getAskingPrice());
		carPriceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}([\\,\\.]\\d{0,2})?")) {
                	carPriceField.setText(oldValue);
                }
                //replace , with . due to danish use of ,
                String replacedText=carPriceField.getText().replaceAll(",",".");
                loanAgreement.setAskingPrice(replacedText);
            }
        });
		
		Label downPaymentHeader = new Label("Kunde udbetaling");
		downPaymentHeader.setId("header_label");
		
		TextField downPaymentField = new TextField(loanAgreement.getDownPayment());
		downPaymentField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}([\\,\\.]\\d{0,2})?")) {
                	downPaymentField.setText(oldValue);
                }
              //replace , with . due to danish use of ,
                String replacedText=downPaymentField.getText().replaceAll(",",".");
                loanAgreement.setDownPayment(replacedText);
            }
        });
		
		loanInformationGrid.add(loanInfoLabel, 0, 0);
		loanInformationGrid.add(carPriceHeader, 0, 1);
		loanInformationGrid.add(carPriceField, 0, 2);
		loanInformationGrid.add(downPaymentHeader, 1, 1);
		loanInformationGrid.add(downPaymentField, 1, 2);
		
		GridPane.setMargin(carPriceField, new Insets(0, 16, 0, 0));
		
		return loanInformationGrid;
	}
	
	private HBox createLoanPeriodContainer()
	{
		LoanAgreementDataModel loanAgreement=theController.getHandler().getLoanAgreementDataModel();
		HBox loanPeriodContainer= new HBox();
		
		VBox dateContainer=new VBox();
		loanPeriodContainer.getChildren().add(dateContainer);
		
		Label startDateHeader=new Label("Start Dato");
		startDateHeader.setId("header_label");
		
		DatePicker datePicker=new DatePicker(loanAgreement.getStartDate());
		
		datePicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//lock date to the first of the month
			    LocalDate date = datePicker.getValue().with(TemporalAdjusters.firstDayOfMonth());
			    //if date is earlier than current day, set to next month
			    LocalDate currentDate=LocalDate.now();
			    if(date.isBefore(currentDate))
			    {
			    	if(currentDate.getDayOfMonth()==1)
			    	{
			    		date=currentDate;
			    	}
			    	else
			    	{
			    		date=currentDate.with(TemporalAdjusters.firstDayOfNextMonth());
			    	}
			    }
			    datePicker.setValue(date);
			    loanAgreement.setStartDate(date);
			}
		});
		
		dateContainer.getChildren().add(startDateHeader);
		dateContainer.getChildren().add(datePicker);
		
		VBox periodContainer=new VBox();
		
		Label loanPeriodHeader=new Label("Lånets løbetid");
		loanPeriodHeader.setId("header_label");
		
		Slider periodSlider=new Slider(2, 10, loanAgreement.getDuration());
		periodSlider.setMajorTickUnit(1);
		periodSlider.setMinorTickCount(0);
		periodSlider.setBlockIncrement(1);
		periodSlider.setShowTickLabels(true);
		periodSlider.setSnapToTicks(true);
		periodSlider.setShowTickMarks(true);
		Bindings.bindBidirectional(loanAgreement.durationProperty(), periodSlider.valueProperty());
		
		periodContainer.getChildren().add(loanPeriodHeader);
		periodContainer.getChildren().add(periodSlider);
		
		loanPeriodContainer.getChildren().add(periodContainer);
		
		return loanPeriodContainer;
	}
	
	private GridPane createCarInfoGrid()
	{
		LoanAgreementDataModel loanAgreement=theController.getHandler().getLoanAgreementDataModel();
		GridPane carInformationGrid=new GridPane();

		Label carInformationHeader= new Label("Bil ID");
		carInformationHeader.setId("header_label");
		
		//TODO Currently findcarbutton just returns premade car, perhaps a search in db will replace this, if time
		TextField carIDNumberField= new TextField();
		carIDNumberField.setEditable(false);
		carIDNumberField.textProperty().bind(loanAgreement.getCar().VINProperty());
		
		Button findCarButton=new Button("Find Bil");
		findCarButton.setId("view_button");
		findCarButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.findCar();
		    }
		});
		
		Label carInformationLabel=new Label();
		carInformationLabel.textProperty().bind(loanAgreement.getCar().carDescriptionProperty());
		
		carInformationGrid.add(carInformationHeader, 0, 0);
		carInformationGrid.add(carIDNumberField, 0, 1);
		carInformationGrid.add(findCarButton, 1, 1);
		carInformationGrid.add(carInformationLabel, 0, 2, 2,1);
		
		return carInformationGrid;
	}
	
	private VBox createButtonContainer()
	{
		VBox buttonContainer= new VBox();
		buttonContainer.setPadding(new Insets(14));
		
		HBox buttonHolder=new HBox();
		buttonContainer.getChildren().add(buttonHolder);
		
		Button calculateAgreementButton = new Button("Beregn låneaftale");
		calculateAgreementButton.setId("view_button");
		calculateAgreementButton.setDisable(true);
		
		calculateAgreementButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	warningContainer.getChildren().clear();
		    	theController.createLoanAgreement();
		    }
		});
		
		Button cancelButton = new Button("Annuller");
		cancelButton.setId("view_button");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	theController.cancelLoanAgreement();
		    }
		});
		
		buttonHolder.getChildren().add(calculateAgreementButton);
		buttonHolder.getChildren().add(cancelButton);
		HBox.setMargin(calculateAgreementButton, new Insets(6));
		HBox.setMargin(cancelButton, new Insets(6));
		
		//TODO clean up code
		warningContainer = new VBox();
		buttonContainer.getChildren().add(warningContainer);
		//setup listener
				Label waitingLabel= new Label("Indhenter kundens kreditvurdering og daglig rente...");
				buttonContainer.getChildren().add(waitingLabel);
				
				ProgressIndicator progress=new ProgressIndicator();
				buttonContainer.getChildren().add(progress);
				
				if(theController.getHandler().canReturnLoanAgreementProperty().get())
				{
					waitingLabel.setText("");
	            	 calculateAgreementButton.setDisable(false);
	            	 progress.setVisible(false);
				}
				
				theController.getHandler().canReturnLoanAgreementProperty().addListener(new ChangeListener<Boolean>() {
			        @Override public void changed(ObservableValue<? extends Boolean> o,Boolean oldVal, 
			        		Boolean newVal)
			        {
			             if((Boolean)newVal==true)
			             {
			            	 waitingLabel.setText("");
			            	 calculateAgreementButton.setDisable(false);
			            	 progress.setVisible(false);
			             }
			             else
			             {
			            	 waitingLabel.setText("Indhenter kundens kreditvurdering og daglig rente...");
			            	 calculateAgreementButton.setDisable(true);
			            	 progress.setVisible(true);
			             }
			        }
			      });
		
		
		return buttonContainer;
	}
	
	public void addWarning(String warning)
	{
		Label warningLabel=new Label(warning);
		warningContainer.getChildren().add(warningLabel);
	}

	@Override
	public boolean onClose()
	{
		if(theController.canClose())
			return true;
		
		Alert alert = new FCLSAlert(AlertType.NONE,"Vil du anullere oprettelsen af Låneaftalen?",ButtonType.OK,new ButtonType("Anuller",ButtonData.CANCEL_CLOSE));
		alert.setTitle("Bekraft Afslutning");
		alert.showAndWait();
		return alert.getResult() == ButtonType.OK;
	}
	
}