package idata2001.mappe2.patientregister;

import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Pair;

public class PatientregisterController {


    public PatientregisterController() {}

    /**
     * Initializes the patientregistercontroller.
     */
    public void initialize() {
        


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
     * Shows information about the patientregister app.
     * TODO: Make the program show accurate version and build date.
     */
    @FXML
    public void showProgramInformationDialogue() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Information Dialogue - About");
        info.setHeaderText("Patientregister"
                + "Version " + "Put the current version here yeah" );

        info.setContentText("Created for NTNU by \n"
                + "Sindre Skorpen | @westdespair\n"
                + "Put the date here yeah");

        info.showAndWait();

    }

    /**
     * Shows a dialogue allowing edits to a selected patient.
     * TODO: Add a dialogue and start functionality on this method.
     */
    @FXML
    public void showEditPatientDialogue() {


    }

}
