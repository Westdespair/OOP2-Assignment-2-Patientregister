package idata2001.mappe2.patientregister;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class PatientAddEditOrInfoDialogue extends Dialog<Patient>{

    //NEW, EDIT, or INFO
    private Mode mode;
    private Patient selectedPatient = null;

    public enum Mode {
        NEW, EDIT, INFO
    }

    public PatientAddEditOrInfoDialogue() {
        mode = null;
    }

    /**
     * Shows the dialogue box for adding, editing, or displaying the information of a patient.
     * @param dialogueMode Enum INFO, EDIT, or NEW.
     *                     NEW: Makes a new patient.
     *                     INFO: Only shows an infobox of the patients information.
     *                     EDIT: Shows an editable infobox of the patients information.
     */
        public void showAddEditOrInfoDialogue(Mode dialogueMode, Patient patient) {
            setPatient(patient);

            //Starts building the dialogue.
            setTitle("Patient information");

            //The information dialogue only needs the ok button, as there is no action to cancel.
            getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            if (dialogueMode != Mode.INFO) {
                getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
            }

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField firstName = new TextField();
            firstName.setPromptText("First name");

            TextField lastName = new TextField();
            lastName.setPromptText("Last name");

            TextField socialSecurityNumber = new TextField();
            socialSecurityNumber.setPromptText("Social security number");

            TextField generalPractitioner = new TextField();
            generalPractitioner.setPromptText("General practitioner");

            TextField diagnosis = new TextField();
            diagnosis.setPromptText("Diagnosis");

            grid.add(new Label("First name:"), 0, 0);
            grid.add(firstName, 1, 0);

            grid.add(new Label("Last name:"), 0, 1);
            grid.add(lastName, 1, 1);

            grid.add(new Label("Social security number:"), 0, 2);
            grid.add(socialSecurityNumber, 1, 2);

            grid.add(new Label("General practitioner:"), 0, 3);
            grid.add(generalPractitioner, 1, 3);

            grid.add(new Label("Diagnosis:"), 0, 4);
            grid.add(diagnosis, 1, 4);
//            diagnosis.setMinHeight(250);

            getDialogPane().setContent(grid);


            //Alters the dialogue based on which type of dialogue mode is requested.
            if (dialogueMode == Mode.INFO || dialogueMode == Mode.EDIT) {
                firstName.setText(selectedPatient.getFirstName());
                lastName.setText(selectedPatient.getLastName());
                socialSecurityNumber.setText(selectedPatient.getSocialSecurityNumber());
                generalPractitioner.setText(selectedPatient.getGeneralPractitioner());
                diagnosis.setText(selectedPatient.getDiagnosis());
            }

            if (dialogueMode == Mode.INFO) {
                firstName.setEditable(false);
                lastName.setEditable(false);
                socialSecurityNumber.setEditable(false);
                generalPractitioner.setEditable(false);
                diagnosis.setEditable(false);

            }

            if (dialogueMode == Mode.EDIT || dialogueMode == Mode.NEW) {
                firstName.textProperty().addListener((observable, oldValue, newValue) -> {
                    firstName.setText(newValue);
                });

                lastName.textProperty().addListener((observable, oldValue, newValue) -> {
                    lastName.setText(newValue);
                });

                socialSecurityNumber.textProperty().addListener((observable, oldValue, newValue) -> {
                    socialSecurityNumber.setText(newValue);
                });

                generalPractitioner.textProperty().addListener((observable, oldValue, newValue) -> {
                    generalPractitioner.setText(newValue);
                });

                diagnosis.textProperty().addListener((observable, oldValue, newValue) -> {
                    diagnosis.setText(newValue);
                    diagnosis.autosize();

                });
            }


            setResultConverter((ButtonType button) -> {
                Patient result = null;
                if (button == ButtonType.OK) {

                    System.out.println("User pressed OK");

                    if (mode == Mode.EDIT) {
                        result = selectedPatient;
                        System.out.println("User pressed edit and OK");

                    } else if (mode == Mode.NEW) {
                        result = new Patient("", "", "", "");
                        System.out.println("User pressed new and OK");

                    }

                    selectedPatient.setFirstName(firstName.getText());
                    selectedPatient.setLastName(lastName.getText());
                    selectedPatient.setSocialSecurityNumber(socialSecurityNumber.getText());
                    selectedPatient.setGeneralPractitioner(generalPractitioner.getText());
                    selectedPatient.setDiagnosis(diagnosis.getText());

                }

                return result;

            });
            showAndWait();

        }

        /**
         * Sets the current patient the dialogue will work with.
         * @Param Patient the new selectedpatient
         */
        public void setPatient(Patient patient) {
            this.selectedPatient = patient;
        }

    /**
     * Returns the patient currently selected by the dialogue.
     * @return Patient the patient the dialogue is currently holding.
     */
    public Patient getPatient() {
            return this.selectedPatient;
        }
    }

