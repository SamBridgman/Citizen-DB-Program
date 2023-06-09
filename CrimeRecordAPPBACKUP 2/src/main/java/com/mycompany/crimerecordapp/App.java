package com.mycompany.crimerecordapp;

import java.io.IOException;
import java.text.NumberFormat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.NumberFormat;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class App extends Application {
    //uses the interface so criminals and person can both be in
    private static TableView<PersonRecord> table = new TableView<PersonRecord>();
    
    @Override
    public void start(Stage primaryStage) {
        table.setPlaceholder(new Label("Refresh Table")); // when table has not been refreshed
        //setting up the window
        primaryStage.setTitle("Masschusettes Resident Database");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(0, 0, 30, 0));

        Scene scene = new Scene(grid);
        
        //hbox title stuff
        final HBox hboxTitle = new HBox();
        hboxTitle.setPadding(new Insets(20,20,20,20));
        hboxTitle.setStyle("-fx-background-color: BLUE;");
        Label title = new Label("Massachusettes Resident Record Database");
        hboxTitle.getChildren().add(title);
        //hboxTitle.setMinWidth(800);
        hboxTitle.setAlignment(Pos.CENTER);
        grid.setColumnSpan(hboxTitle,2);
        grid.add(hboxTitle, 0, 0);
        
        title.setStyle("-fx-font: 24 Verdana");
        title.setTextFill(Color.WHITE);
       
        //title that says criminal case index
        Label titleIndex = new Label("Resident Case Index");
        titleIndex.setStyle("-fx-font: 20 Verdana");
        titleIndex.setTextFill(Color.BLUE);
        titleIndex.setAlignment(Pos.TOP_LEFT);
        
        final HBox indexhBox = new HBox();
        indexhBox.setSpacing(5);
        indexhBox.setPadding(new Insets(10, 10, 0, 10));
        indexhBox.getChildren().addAll(titleIndex);
        grid.add(indexhBox, 0, 1);
        
        //search filter title and refresh title
        Label titleFilter = new Label("Search Filter");
        Label warnFilter = new Label("* Refresh After Each Submission");
        warnFilter.setPadding(new Insets(10,0,0,578));
        titleFilter.setStyle("-fx-font:12 Verdana");
        titleFilter.setTextFill(Color.BLACK);
        titleFilter.setAlignment(Pos.TOP_LEFT);
        titleFilter.setPadding(new Insets(10,0,0,10));
        grid.add(titleFilter, 0, 2);
        grid.add(warnFilter, 0, 2);

        //textfields for search
        Label searchFirstNameTitle = new Label("First Name: ");
        TextField searchFName = new TextField();
        searchFName.setPrefWidth(200);
        searchFName.setMaxWidth(200);
        
        Label searchLastNameTitle = new Label("Last Name: ");
        TextField searchLName = new TextField();
        searchLName.setPrefWidth(200);
        searchLName.setMaxWidth(200);
        
        //-------------------------
        //buttons that include search textfields and search and exit buttons
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> searchButtonClicked(searchFName, searchLName));
        Button refreshTable = new Button("Refresh");
        //the refresh button calls  the read database method from controller class
        refreshTable.setOnAction(event -> table = PersonController.readDatabase(table));
        
        

        Button exitButton = new Button("Exit");
        //exit button uses the exitbuttonclicked method
        exitButton.setOnAction(event -> exitButtonClicked());
        //box with all the buttons in the beginning row
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(searchLastNameTitle);
        buttonBox.getChildren().add(searchLName);
        buttonBox.getChildren().add(searchFirstNameTitle);
        buttonBox.getChildren().add(searchFName);
        buttonBox.getChildren().add(searchButton);
        buttonBox.getChildren().add(exitButton);
        buttonBox.getChildren().add(refreshTable);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, 4, 1, 1);
        buttonBox.setPadding(new Insets(0,20,0,10));
        buttonBox.setSpacing(10);
        
        //-------------------------
        //table of persons
        TableColumn<PersonRecord,String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(400);
        TableColumn chargeCol = new TableColumn("Charges if Applicable");
        chargeCol.setMinWidth(400);
 
        table.getColumns().addAll(nameCol, chargeCol);
        //table.setPadding(new Insets(10,0,0,0));
        grid.setColumnSpan(table,2);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 0, 10));
        vbox.getChildren().addAll(table);
        //table.getItems().add(new Person("John", "Doe"));
        //table.getItems().add(new Criminal("Sam", "Bridgman", new Charge("Felony Larceny", "Stole a car")));
        grid.setColumnSpan(vbox, 2);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        chargeCol.setCellValueFactory(new PropertyValueFactory<>("charge"));
        grid.add(vbox, 0, 5);
        //-------------------------
        
        
        //-------------------------
        // for entry button
        Button entryIndex = new Button("Add Entry");
        entryIndex.setStyle("-fx-font: 20 Verdana");
        entryIndex.setTextFill(Color.BLUE);
        entryIndex.setAlignment(Pos.CENTER);
        
       
        final HBox entryhBox = new HBox();
        entryhBox.setSpacing(5);
        entryhBox.setPadding(new Insets(10, 10, 0, 10));
        entryhBox.getChildren().addAll(entryIndex);
        grid.add(entryhBox, 0, 6);
        grid.setColumnSpan(entryhBox, 2);
        entryhBox.setAlignment(Pos.CENTER);
        entryIndex.setOnAction(event -> entryButtonClicked());
        //-------------------------
        primaryStage.setScene(scene);
        primaryStage.show();
        

    }
    //------------ENTRY FORM----------------
    private void entryButtonClicked() {
            //entry page setup
            GridPane grid = new GridPane();
            Stage stage = new Stage();
            stage.setTitle("Entry Page");
            stage.setScene(new Scene(grid,300, 400));
            grid.setPadding(new Insets(0,0,0,10));
            //------------------------------------------------
            
            //entry page title
            Label entryLabel = new Label("ENTRY FORM");
            entryLabel.setStyle("-fx-font: 20 Verdana");
            entryLabel.setTextFill(Color.BLUE);
            entryLabel.setPadding(new Insets(10, 10, 0, 0));
            grid.add(entryLabel,0,0);
            //first name field
            TextField fName = new TextField();
            Label nameTitle = new Label("First Name: ");
            grid.add(nameTitle, 0, 1);
            final HBox fNameBox = new HBox();
            fNameBox.setPadding(new Insets(10, 10, 10, 0));
            fNameBox.getChildren().addAll(fName);
            grid.add(fNameBox,0,2);
            
            //last name field
            TextField lName = new TextField();
            Label nameLastTitle = new Label("Last Name: ");
            grid.add(nameLastTitle, 0, 3);
            final HBox lNameBox = new HBox();
            lNameBox.setPadding(new Insets(10, 10, 10, 0));
            lNameBox.getChildren().addAll(lName);
            grid.add(lNameBox,0,4);
            //charged checkmark
            CheckBox chargeCheck = new CheckBox("Charged? ");
            grid.add(chargeCheck,0,5);
            chargeCheck.setPadding(new Insets(10, 10, 10, 0));
            //if charged is yes, then these are added from the checkboxmarked method
            TextField charge = new TextField();
            final HBox chargedBox = new HBox();
            chargedBox.setPadding(new Insets(10, 10, 10, 0));
            chargedBox.getChildren().addAll(charge);
            //for desc
            TextField desc = new TextField();
            final HBox descBox = new HBox();
            descBox.setPadding(new Insets(10, 10, 10, 0));
            descBox.getChildren().addAll(desc);
            //the action that uses the checkbox marked method
            chargeCheck.selectedProperty().addListener((observable, oldValue, newValue) -> checkBoxMarked(grid, chargedBox, descBox));
            
            
            
            //for the submit button
            Button submitEntry = new Button("Submit");
            submitEntry.setStyle("-fx-font: 15 Verdana");
            submitEntry.setTextFill(Color.BLUE);
            submitEntry.setMinWidth(100);
            submitEntry.setMaxWidth(100);
            submitEntry.setMinHeight(30);
            submitEntry.setMaxHeight(30);
            submitEntry.setPadding(new Insets(0, 10, 0, 0));
            submitEntry.setAlignment(Pos.CENTER);
            
            //submit box
            final HBox submitBox = new HBox();
            submitBox.setPadding(new Insets(10, 10, 10, 0));
            submitBox.getChildren().addAll(submitEntry);
            grid.add(submitBox,0,10);
            
            //submit entry button uses the addToDatabase controller method
            
            submitEntry.setOnAction(event -> PersonController.addToDatabase(fName,lName,charge,desc));
            stage.show();
    }
    // this method adds the extra fields for when the check mark is clicked when adding to database
    public void checkBoxMarked(GridPane grid, HBox chargedBox, HBox descBox) {
            Label descTitle = new Label("Description: ");
            grid.add(descTitle, 0, 8);
            grid.add(descBox,0,9);
            Label chargeTitle = new Label("Charge: ");
            grid.add(chargeTitle, 0, 6);
            grid.add(chargedBox,0,7);
            
            
    }
    //exits program
    private void exitButtonClicked() {
        System.exit(0);   // 0 indicates a normal exit
    }
    //--------------------------------THIS IS THE SEARCH RESULT PAGE-------------------------------------
    private void searchButtonClicked(TextField fName, TextField lName){
        try{
            //searches through the database for the name and adds to arr
            PersonRecord arr[] = PersonController.searchDatabase(fName, lName);
            //System.out.println(arr[0].getFName());
            //checks length of values, less than 1 indicates does not exist
            if(arr[0].getFName().length() < 1 || arr[0].getLName().length() < 1) 
            {
                throw new IOException(); // throw exception
            }
            //otherwise do this
            else
            {
                //sets up search result page
                GridPane grid = new GridPane();
                Stage stage = new Stage();
                stage.setTitle("Search Result");
                stage.setScene(new Scene(grid,300, 400));
                grid.setPadding(new Insets(0,0,0,10));
                //label that used from the interface .getProfile() method
                Label getProfile = new Label(arr[0].getProfile());
                
                //hbox for get profule
                HBox nameDisplay = new HBox();
                nameDisplay.setPadding(new Insets(10, 10, 10, 0));
                nameDisplay.getChildren().addAll(getProfile);
                nameDisplay.setStyle("-fx-font: 15 Verdana");
                //nameDisplay.setTextFill(Color.BLUE);
                grid.add(nameDisplay,0,0);
                
                //for the edit and delete buttons
                Button edit = new Button("Edit");
                Button delete = new Button("Delete");
                //calls the controller deletefromdatabase method with the first and last name
                delete.setOnAction(eh -> PersonController.deleteFromDatabase(fName.getText(), lName.getText()));
                //calls the editbuttonclicked method
                edit.setOnAction(eh -> editButtonClicked(fName, lName));
                HBox modifyBtns = new HBox();
                modifyBtns.getChildren().addAll(edit,delete);
                modifyBtns.setSpacing(10);
                grid.add(modifyBtns,0,2);
                
                
                
                stage.show();
            }
        }
        catch(Exception e) {
            //error popup
            Alert notFound = new Alert(AlertType.WARNING);
            notFound.setContentText(" INVALID DATA: \n Unable to find person");
            notFound.show();
            notFound.showAndWait();
        }
        
    }
    
    //------------------THIS IS THE EDIT PERSON PAGE-------------------------------------
    private void editButtonClicked(TextField sFName, TextField sLName) {
            //sets up page
            GridPane grid = new GridPane();
            Stage stage = new Stage();
            stage.setTitle("Update Page");
            stage.setScene(new Scene(grid,300, 400));
            grid.setPadding(new Insets(0,0,0,10));
            
            //title stuff
            Label entryLabel = new Label("UPDATE FORM");
            Label noteLabel = new Label("Note: Leave entry blank for no change.");
            noteLabel.setTextFill(Color.BLUE);
            entryLabel.setStyle("-fx-font: 20 Verdana");
            entryLabel.setTextFill(Color.BLUE);
            entryLabel.setPadding(new Insets(10, 10, 0, 0));
            grid.add(entryLabel,0,1);
            grid.add(noteLabel, 0, 2);
            
            //new first name entry
            TextField fName = new TextField();
            Label nameTitle = new Label("New First Name: ");
            grid.add(nameTitle, 0, 3);
            final HBox fNameBox = new HBox();
            fNameBox.setPadding(new Insets(10, 10, 10, 0));
            fNameBox.getChildren().addAll(fName);
            grid.add(fNameBox,0,4);
            
            //new last name entry
            TextField lName = new TextField();
            Label nameLastTitle = new Label("New Last Name: ");
            grid.add(nameLastTitle, 0, 5);
            final HBox lNameBox = new HBox();
            lNameBox.setPadding(new Insets(10, 10, 10, 0));
            lNameBox.getChildren().addAll(lName);
            grid.add(lNameBox,0,6);
            
            //new charge entry
            TextField charge = new TextField();
            Label chargeTitle = new Label("New Charge (if applicable): ");
            grid.add(chargeTitle, 0, 7);
            final HBox chargedBox = new HBox();
            chargedBox.setPadding(new Insets(10, 10, 10, 0));
            chargedBox.getChildren().addAll(charge);
            grid.add(chargedBox, 0, 8);
            
            //new description entry
            TextField desc = new TextField();
            Label descTitle = new Label("New Description of charge (if applicable): ");
            grid.add(descTitle, 0, 9);
            final HBox descBox = new HBox();
            descBox.setPadding(new Insets(10, 10, 10, 0));
            descBox.getChildren().addAll(desc);
            grid.add(descBox,0,10);
            
            //the submit button
            Button submitEntry = new Button("Submit");
            submitEntry.setStyle("-fx-font: 15 Verdana");
            submitEntry.setTextFill(Color.BLUE);
            submitEntry.setMinWidth(100);
            submitEntry.setMaxWidth(100);
            submitEntry.setMinHeight(30);
            submitEntry.setMaxHeight(30);
            submitEntry.setPadding(new Insets(0, 10, 0, 0));
            submitEntry.setAlignment(Pos.CENTER);
            
            //hbox for submit button
            final HBox submitBox = new HBox();
            submitBox.setPadding(new Insets(10, 10, 10, 0));
            submitBox.getChildren().addAll(submitEntry);
            grid.add(submitBox,0,11);
            //submit button calls the editDatabse method from the controller class with the name to edit
            submitEntry.setOnAction(eh -> PersonController.editDatabase(fName.getText(), lName.getText(), charge.getText(), desc.getText(), sFName.getText(), sLName.getText()));
            stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }    
}