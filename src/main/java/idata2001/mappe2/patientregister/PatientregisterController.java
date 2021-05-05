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

/**
 * Controls the functionality of the user interface.
 * Allows for functionality based on editing, importing, exporting .CSV files containing information about Patient instances.
 */
public class PatientregisterController {

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


    private ObservableList<Patient> observablePatientList;
    private final String appVersion = " FIX ME LATER";
    private String currentPath;
    private PatientList appPatientList;
    private CSVReader csvReader;
    private CSVWriter csvWriter;
    private PatientAddEditOrInfoDialogue addEditOrInfoDialogue;
    private boolean recentlySaved;


    /**
     * Initializes the patientregistercontroller.
     */
    public void initialize() throws IOException {
        appPatientList = new PatientList();
        csvReader = new CSVReader();
        csvWriter = new CSVWriter();
        addEditOrInfoDialogue = new PatientAddEditOrInfoDialogue();
        currentPath = null;
        validateRecentlySavedStatus();

        //Initialize columns in the tableview.
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        socialSecurityNumberColumn.setCellValueFactory(new PropertyValueFactory<>("socialSecurityNumber"));

        //Test tableview functionality
        //TODO: Remove this method call in the final update.
        appPatientList.fillPatientListWithTestPatients();

        //Sets the list for the tableview as the internal list of the controller.
        this.observablePatientList =
                FXCollections.observableArrayList(this.appPatientList.getPatientList());
        this.patientTableView.setItems(this.observablePatientList);

        //Adds functionality for double-click selections.
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
            invalidateRecentlySavedStatus();
        }
      refreshTables();
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

            //Removes the selected patient if OK is pressed.
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    appPatientList.getPatientList().remove(deletePatient);
                    invalidateRecentlySavedStatus();
                }
            });
        }
        refreshTables();
    }

    /**
     * Closes the application if the current file has been saved recently.
     * If not, asks the customer to either save, go back, or acknowledge that the data will be lost on exit.
     * @return isCancelled Boolean tells if the user pressed cancel or not.
     */
    @FXML
    public boolean closeApplication() throws IOException {
        boolean isCancelled = false;
        Stage stage = (Stage) editPatientButton.getScene().getWindow();
        if (isRecentlySaved()) {
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save on exit?");
            alert.setHeaderText("You have unsaved changes in your register!");
            alert.setContentText("Do you want to save your progress before exiting?");

            //Establishes the different buttons of the dialogue
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

            //Opens the dialogue and waits for a button to be selected.
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                //If the user selects the yes button, save the file.
                if (result.get() == buttonTypeYes) {
                    saveFile();
                }

                //If the user selects anything but cancel, the program will close at this point.
                if(result.get() == buttonTypeCancel) {
                    isCancelled = true;
                } else {
                    stage.close();
                }
            }
        }
        return isCancelled;
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
     * Shows a dialogue allowing edits to the selected patient.
     * If there is no selection, show a dialogue explaining this.
     */
    @FXML
    public void showEditPatientDialogue() {
        PatientAddEditOrInfoDialogue dialogue = new PatientAddEditOrInfoDialogue();
        Patient editPatient = getSelectedPatient();
        if (editPatient == null) {
            showNoSelectionDialogue();

        } else {
            dialogue.showAddEditOrInfoDialogue(PatientAddEditOrInfoDialogue.Mode.EDIT, editPatient);
            invalidateRecentlySavedStatus();
            refreshTables();
        }
    }

    /**
     * Shows all the information available on the patient in an uneditable dialogue.
     */
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
     * ALlows the user to choose a .CSV file through file browser and returns the files path.
     * If no file is selected, do nothing.
     * * @return File the chosen file.
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
     * Allows the user to select a .CSV file through the file browser.
     * Imports the file to the tableview of the application.
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
            refreshTables();
        }
    }

    /**
     * Allows the user to choose a file location and save the contents of their table to a .CSV file on said location.
     */
    @FXML
        public void saveFileAtSelectedPath() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".CSV file", "*.csv"));
        fileChooser.setTitle("Select export location");
        fileChooser.setInitialFileName("patientList");

        File newFilePath = fileChooser.showSaveDialog((Stage) editPatientButton.getScene().getWindow());
            System.out.println(newFilePath);
            currentPath = newFilePath.getAbsolutePath();

            //The file needs to end with .csv to be written properly.
            if (!currentPath.endsWith(".csv")) {
                showWrongFileTypeDialogue();
                currentPath = null;

            } else {
                csvWriter.convertPatientArrayToCSVFile(appPatientList, newFilePath.getAbsolutePath());
                validateRecentlySavedStatus();
            }
    }

    /**
     * Saves the file to the already established file path.
     * If there is none, the path has to be selected manually through the filebrowser
     * @throws IOException
     */
    @FXML
    public void saveFile() throws IOException {
        if (currentPath != null) {
            csvWriter.convertPatientArrayToCSVFile(appPatientList, currentPath);
        } else {
            saveFileAtSelectedPath();
        }
    }

    /**
     * Refreshes the tableView in the user interface.
     */
    public void refreshTables() {
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

    /**
     * Shows a dialogue informing that no selection has been made.
     */
    public void showNoSelectionDialogue() {
        Alert noSelectionAlert = new Alert(Alert.AlertType.INFORMATION);
        noSelectionAlert.setTitle("No patient selected");
        noSelectionAlert.setHeaderText("Patient information");
        noSelectionAlert.setContentText("You need to select a patient by highlighting them in the table.");
        noSelectionAlert.show();
    }

    /**
     * Displays a warning to the user that the filetype must be .csv.
     */
    public void showWrongFileTypeDialogue() {
        Alert wrongFileTypeAlert = new Alert(Alert.AlertType.WARNING);
        wrongFileTypeAlert.setTitle("Wrong file type used");
        wrongFileTypeAlert.setHeaderText("The file type of the saved file must be .csv!");
        wrongFileTypeAlert.setContentText("" +
                " To ensure the filetype is correct, press \"Save File As...\" again, and write \".csv\" at the end of your file name.");
        wrongFileTypeAlert.show();
    }

    /**
     * Invalidates that the file has been recently saved.
     */
    public void invalidateRecentlySavedStatus() {
        this.recentlySaved = false;
    }

    /**
     * Validates that the file has been recently saved.
     */
    public void validateRecentlySavedStatus() {
        this.recentlySaved = true;
    }

    /**
     * Returns whether or not the file has been saved recently.
     * @return recentlySaved Boolean whether or not the file has recently been saved.
     */
    public boolean isRecentlySaved() {
        return this.recentlySaved;
    }
}
