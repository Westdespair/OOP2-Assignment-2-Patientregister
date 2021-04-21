package idata2001.mappe2.patientregister;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class PatientregisterController {

    private ObservableList<Patient> observablePatientList;
    private final String appVersion = " FIX ME LATER";
    private PatientList appPatientList;
    private CSVReader csvReader;

    @FXML
    private Button editPatientButton;

    @FXML
    private Button removePatientButton;

    @FXML
    private Button addPatientButton;

    @FXML
    private MenuItem aboutButton;

    @FXML
    private TableView<Patient> patientTableView;

    @FXML
    private TableColumn<Patient, String> firstNameColumn;

    @FXML
    private TableColumn<Patient, String> lastNameColumn;

    @FXML
    private TableColumn<String, String> socialSecurityNumberColumn;

    /**
     * Initializes the patientregistercontroller.
     */
    public void initialize() {

        appPatientList = new PatientList();
        csvReader = new CSVReader();
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        socialSecurityNumberColumn.setCellValueFactory(new PropertyValueFactory<>("socialSecurityNumber"));

        //Test tableview functionality
        //appPatientList.fillPatientListWithTestPatients();


        this.observablePatientList =
                FXCollections.observableArrayList(this.appPatientList.getPatientList());
        this.patientTableView.setItems(this.observablePatientList);
    }

    /**
     * Creates a dialogue pane that allows us to fill in information and add a new patient to the table.
     * TODO: Add functionality for actually receiving the inputs.
     */
    @FXML
    public void showAddPatientDialogue() {


        TextInputDialog firstNameDialog = new TextInputDialog("First name");
        firstNameDialog.setContentText("First name: ");

        TextInputDialog lastNameDialog = new TextInputDialog("Last name");
        lastNameDialog.setContentText("Last name: ");

        TextInputDialog socialSecurityNumberNameDialog = new TextInputDialog("Social security number");
        socialSecurityNumberNameDialog.setContentText("Social security number");

        //Commented out example code from javafx dialogs official from code.makery.ch
        //result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    /**
     * Shows a dialogue asking if the user is sure about deleting a patient.
     * TODO: Add functionality for choosing OK/Cancel
     */
    @FXML
    public void showDeletePatientDialogue() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete patient confirmation");
        alert.setHeaderText("Delete confirmation");
        alert.setContentText("Are you sure you want to delete this patient?");

        // if (result.get() == ButtonType.OK) {Do the thing }
        // Else () { Don't do the thing}
        alert.show();
    }

    /**
     * Starts a confirmation dialogue.
     * Exits the application if OK is selected, does nothing if CANCEL is selected.
     * TODO: Only ask for exit confirmation if there is progress to lose on the file.
     */
    @FXML
    public void showCloseApplicationDialogue() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit application");
        alert.setHeaderText("Exit confirmation");
        alert.setContentText("Are you sure you want to exit? Unexported progress will be lost.");
        Stage stage = (Stage) editPatientButton.getScene().getWindow();

        //Shows the alert and waits for a response.
        //If the OK button is pressed, close the application. If anything else is pressed, do nothing.
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                //CLOSE IT DOWN
                stage.close();
            }
        });
    }

    /**
     * Shows information about the patientregister app.
     * TODO: Make the method show current build version and build release date.
     */
    @FXML
    public void showProgramInformationDialogue() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("About this application");
        info.setHeaderText("Patientregister"
                + "Version " + appVersion );

        info.setContentText("Created for IDATA2001/NTNU by \n"
                + "Sindre Skorpen | @westdespair\n"
                + "2021");

        info.showAndWait();
    }

    /**
     * Shows a dialogue allowing edits to a selected patient.
     * TODO: Add a dialogue and start functionality on this method.
     */
    @FXML
    public void showEditPatientDialogue() {
    }



    /**
     * ALlows the user to choose a .CSV file with the file browser, and returns the files path.
     * Successfully returns the file at this point. TODO: Open the file and put it in the table on import.
     * @return FILE the chosen file.
     */
    @FXML
    public String chooseFile() {
        //Uses one of the buttons hooked up to the scene to get the scene.
        Scene mainScene = editPatientButton.getScene();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select .CSV file");

        //Creates a new filter only consisting of .CSV files. This program will only work on files of that type.
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".CSV files", "*.csv"));

        //Opens up the fileChooser as a dialog.
        File selectedFile = fileChooser.showOpenDialog(mainScene.getWindow());
        String selectedFilePath = "";

        //Prints error statement if no file is selected, or gets the files absolute path if it is.
        if (selectedFile == null) {
            System.out.println("No files were selected in the filechooser.");
        } else {
            selectedFilePath = selectedFile.getAbsolutePath();
        }

        System.out.println(selectedFilePath);
            return selectedFilePath;
    }

    /**
     * Allows the user to choose a file through the filechooser. Displays the file in the programs tableview.
     */
    @FXML
    public void importFile() throws IOException {
        appPatientList.setPatientList(csvReader.buildPatientListFromCSVList(csvReader.readFile(chooseFile())));
        showTables();


    }

    /**
     * Refreshes the tableView.
     */
    public void showTables() {
        this.observablePatientList = FXCollections.observableArrayList(this.appPatientList.getPatientList());
        this.patientTableView.setItems(this.observablePatientList);

    }


}
