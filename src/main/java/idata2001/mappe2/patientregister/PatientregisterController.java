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
import java.util.ArrayList;
import java.util.Optional;

public class PatientregisterController {

    private ObservableList<Patient> observablePatientList;
    private final String appVersion = " FIX ME LATER";
    private String currentPath;
    private PatientList appPatientList;
    private CSVReader csvReader;
    private CSVWriter csvWriter;
    private PatientAddEditOrInfoDialogue addEditOrInfoDialogue;

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
    public void initialize() throws IOException {

        appPatientList = new PatientList();
        csvReader = new CSVReader();
        csvWriter = new CSVWriter();
        addEditOrInfoDialogue = new PatientAddEditOrInfoDialogue();
        currentPath = null;

        //Initialize columns in the tableview.
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        socialSecurityNumberColumn.setCellValueFactory(new PropertyValueFactory<>("socialSecurityNumber"));

        //Test tableview functionality
        appPatientList.fillPatientListWithTestPatients();


        this.observablePatientList =
                FXCollections.observableArrayList(this.appPatientList.getPatientList());
        this.patientTableView.setItems(this.observablePatientList);

        this.patientTableView.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && (mouseEvent.getClickCount() == 2)) {
                this.showPatientInformationDialogue();
            }
        });
    }

    /**
     * Creates a dialogue pane that allows us to fill in information and add a new patient to the table.
     */
    @FXML
    public void showAddPatientDialogue() {
        PatientAddEditOrInfoDialogue dialogue = new PatientAddEditOrInfoDialogue();
        dialogue.showAddEditOrInfoDialogue(PatientAddEditOrInfoDialogue.Mode.NEW, null);

       if(dialogue.getPatient() != null && !dialogue.getPatient().patientIsEmpty()) {
            appPatientList.getPatientList().add(dialogue.getPatient());
        }
      showTables();
    }

    /**
     * Shows a dialogue asking if the user is sure about deleting a patient.
     */
    @FXML
    public void showDeletePatientDialogue() {
        Patient deletePatient = getSelectedPatient();

        if (deletePatient == null) {
            showNoSelectionDialogue();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete patient confirmation");
            alert.setHeaderText("Delete confirmation");
            alert.setContentText("Are you sure you want to delete this patient?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    appPatientList.getPatientList().remove(deletePatient);
                }
            });
        }
        showTables();
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
        PatientAddEditOrInfoDialogue dialogue = new PatientAddEditOrInfoDialogue();
        Patient editPatient = getSelectedPatient();
        if (editPatient == null) {
            showNoSelectionDialogue();

        } else {
            dialogue.showAddEditOrInfoDialogue(PatientAddEditOrInfoDialogue.Mode.EDIT, editPatient);
            showTables();
        }
    }

    @FXML
    public void showPatientInformationDialogue() {
        PatientAddEditOrInfoDialogue dialogue = new PatientAddEditOrInfoDialogue();
        Patient infoPatient = getSelectedPatient();
        if (infoPatient == null) {
            showNoSelectionDialogue();

        } else {
            dialogue.setPatient(infoPatient);
            dialogue.showAddEditOrInfoDialogue(PatientAddEditOrInfoDialogue.Mode.INFO, infoPatient);
        }

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
        // Sets the current list of patients to a list chosen through the filechooser.
        String patientListFile = chooseFile();

        // A valid file needs to be selected.
        // If no .CSV file is selected, either through pressing the "cancel" button or crossing out the dialogue,
        // nothing will happen.
        if (patientListFile != null && !patientListFile.equals("")) {
            //Creates the new "importedPatientList", reads the selected .CSV file, and converts it to a list of patients.
            ArrayList <Patient> importedPatientList = csvReader.buildPatientListFromCSVList(csvReader.readFile(patientListFile));
            appPatientList.setPatientList(importedPatientList);
            currentPath = patientListFile;
            showTables();
        }
        }

    /**
     * Allows the user to choose a file location and save the contents of their table to a .CSV file on said location.
     */
    @FXML
        public void exportFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".CSV file", "*.csv"));
        fileChooser.setTitle("Select export location");
        fileChooser.setInitialFileName("patientList");

        File newFilePath = fileChooser.showSaveDialog((Stage) editPatientButton.getScene().getWindow());
        System.out.println(newFilePath);
        currentPath = newFilePath.getAbsolutePath();

        csvWriter.convertPatientArrayToCSVFile(appPatientList, newFilePath.getAbsolutePath());

    }

    /**
     * Saves the file to the already established file path. If there is none, the path has to be selected manually.
     * @throws IOException
     */
    @FXML
    public void saveFile() throws IOException {
        if (currentPath != null) {
            csvWriter.convertPatientArrayToCSVFile(appPatientList, currentPath);
        } else {
            exportFile();
        }
    }

    /**
     * Refreshes the tableView.
     */
    public void showTables() {
        this.observablePatientList.removeAll(observablePatientList);
        this.observablePatientList = FXCollections.observableArrayList(this.appPatientList.getPatientList());
        this.patientTableView.setItems(this.observablePatientList);

    }

    /**
     * Returns the patient that is currently highlighted in the table.
     */
    public Patient getSelectedPatient() {
       Patient selectedPatient = this.patientTableView.getSelectionModel().getSelectedItem();

       if (selectedPatient == null) {
           System.out.println("No patient selected.");
       }

       return selectedPatient;
    }

    public void showNoSelectionDialogue() {
        Alert noSelectionAlert = new Alert(Alert.AlertType.INFORMATION);
        noSelectionAlert.setTitle("No patient selected");
        noSelectionAlert.setHeaderText("Patient information");
        noSelectionAlert.setContentText("You need to select a patient by highlighting them in the table.");
        noSelectionAlert.show();
    }


}
