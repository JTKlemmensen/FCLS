package viewPackage;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import jdk.nashorn.internal.runtime.regexp.joni.Warnings;

public class CreateLoanAggrementView
{
	private CreateLoanAggrementController theController;
	private VBox warningContainer;
	
	public CreateLoanAggrementView(CreateLoanAggrementController controller)
	{
		theController=controller;
	}
	
	public StackPane getSceneGUI()
	{
		StackPane root = new StackPane();
		root.setId("view_screen");
		root.setPadding(new Insets(14));
		
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
		GridPane customerInformationGrid=new GridPane();
		
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
		
		return customerInformationGrid;
	}
	
	private GridPane createLoanInfoGrid()
	{
		GridPane loanInformationGrid=new GridPane();
		loanInformationGrid.setPadding(new Insets(8, 0, 0, 0));
		
		Label loanInfoLabel=new Label("Låneinfo :");
		loanInfoLabel.setId("part_header_label");
		
		Label carPriceHeader = new Label("Købspris");
		carPriceHeader.setId("header_label");
		
		//TODO
		//somehow change , to . so bigdecimals will eat it
		TextField carPriceField = new TextField();
		carPriceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}([\\,\\.]\\d{0,2})?")) {
                	carPriceField.setText(oldValue);
                }
                theController.setCarPrice(carPriceField.getText());
            }
        });
		
		Label downPaymentHeader = new Label("Kunde udbetaling");
		downPaymentHeader.setId("header_label");
		
		TextField downPaymentField = new TextField();
		downPaymentField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,13}([\\,\\.]\\d{0,2})?")) {
                	downPaymentField.setText(oldValue);
                }
                theController.setDownPayment(downPaymentField.getText());
            }
        });
		
		loanInformationGrid.add(loanInfoLabel, 0, 0);
		loanInformationGrid.add(carPriceHeader, 0, 1);
		loanInformationGrid.add(carPriceField, 0, 2);
		loanInformationGrid.add(downPaymentHeader, 1, 1);
		loanInformationGrid.add(downPaymentField, 1, 2);
		
		loanInformationGrid.setMargin(carPriceField, new Insets(0, 16, 0, 0));
		
		return loanInformationGrid;
	}
	
	private HBox createLoanPeriodContainer()
	{
		HBox loanPeriodContainer= new HBox();
		
		VBox dateContainer=new VBox();
		loanPeriodContainer.getChildren().add(dateContainer);
		
		Label startDateHeader=new Label("Start Dato");
		startDateHeader.setId("header_label");
		
		DatePicker datePicker=new DatePicker();
		
		
		datePicker.setOnAction(event -> {
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
		    theController.setStartDate(date);
		});
		
		dateContainer.getChildren().add(startDateHeader);
		dateContainer.getChildren().add(datePicker);
		
		VBox periodContainer=new VBox();
		
		Label loanPeriodHeader=new Label("Lånets løbetid");
		loanPeriodHeader.setId("header_label");
		
		Slider periodSlider=new Slider(2, 10, 6);
		periodSlider.setMajorTickUnit(2);
		periodSlider.setMinorTickCount(0);
		periodSlider.setBlockIncrement(1);
		periodSlider.setShowTickLabels(true);
		periodSlider.setSnapToTicks(true);
		periodSlider.setShowTickMarks(true);
		Bindings.bindBidirectional(theController.loanDurationProperty(), periodSlider.valueProperty(), new NumberStringConverter());
		
		periodContainer.getChildren().add(loanPeriodHeader);
		periodContainer.getChildren().add(periodSlider);
		
		loanPeriodContainer.getChildren().add(periodContainer);
		
		return loanPeriodContainer;
	}
	
	private GridPane createCarInfoGrid()
	{
		GridPane carInformationGrid=new GridPane();

		Label carInformationHeader= new Label("Bil ID");
		carInformationHeader.setId("header_label");
		
		TextField carIDNumberField= new TextField();
		carIDNumberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,8}")) {
                	carIDNumberField.setText(oldValue);
                }
                theController.setCarID(carIDNumberField.getText());
            }
        });
		
		Button findCarButton=new Button("Find Bil");
		findCarButton.setId("view_button");
		
		Label carInformationLabel=new Label("Bilinformation : Ferrari 488 Pista - 2018, udstillingsmodel");
		
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
		
		Button calculateAggrementButton = new Button("Beregn låneaftale");
		calculateAggrementButton.setId("view_button");
		calculateAggrementButton.setDisable(true);
		
		calculateAggrementButton.setOnAction(new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent e) 
		    {
		    	warningContainer.getChildren().clear();
		    	theController.createLoanAggrement();
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
		
		buttonHolder.getChildren().add(calculateAggrementButton);
		buttonHolder.getChildren().add(cancelButton);
		buttonHolder.setMargin(calculateAggrementButton, new Insets(6));
		buttonHolder.setMargin(cancelButton, new Insets(6));
		
		warningContainer = new VBox();
		buttonContainer.getChildren().add(warningContainer);
		//setup listener
				Label waitingLabel= new Label("Indhenter kundens kreditvurdering og daglig rente...");
				buttonContainer.getChildren().add(waitingLabel);
				
				ProgressIndicator progress=new ProgressIndicator();
				buttonContainer.getChildren().add(progress);
				
				theController.getHandler().canReturnLoanAgreementProperty().addListener(new ChangeListener() {
			        @Override public void changed(ObservableValue o,Object oldVal, 
			                 Object newVal)
			        {
			             if((Boolean)newVal==true)
			             {
			            	 waitingLabel.setText("");
			            	 calculateAggrementButton.setDisable(false);
			            	 progress.setVisible(false);
			             }
			             else
			             {
			            	 waitingLabel.setText("Indhenter kundens kreditvurdering og daglig rente...");
			            	 calculateAggrementButton.setDisable(true);
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
	
}
