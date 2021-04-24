package idata2001.mappe2.patientregister;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PatientAddEditOrInfoDialogue extends Dialog<Patient>{

    //NEW, EDIT, or INFO
    private Mode mode;
    private Patient selectedPatient = null;


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
            getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20,150,10,10));

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

            grid.add(new Label("First name:"),0,0);
            grid.add(firstName,1,0);

            grid.add(new Label("Last name:"),0,1);
            grid.add(lastName,1,1);

            grid.add(new Label("Social security number:"),0,2);
            grid.add(socialSecurityNumber,1,2);

            grid.add(new Label("General practitioner:"),0,3);
            grid.add(generalPractitioner,1,3);

            grid.add(new Label("Diagnosis:"),0,4);
            grid.add(diagnosis,1,4);
            diagnosis.setMinHeight(250);

            getDialogPane().setContent(grid);


            //Alters the dialogue based on which type of dialogue is requested.
            switch(dialogueMode) {
                case INFO:
                    case EDIT:
                        firstName.setText(selectedPatient.getFirstName());
                        lastName.setText(selectedPatient.getLastName());
                        socialSecurityNumber.setText(selectedPatient.getSocialSecurityNumber());
                        generalPractitioner.setText(selectedPatient.getGeneralPractitioner());
                        diagnosis.setText(selectedPatient.getDiagnosis());

                        if(dialogueMode == Mode.INFO) {
                            firstName.setEditable(false);
                            lastName.setEditable(false);
                            socialSecurityNumber.setEditable(false);
                            generalPractitioner.setEditable(false);
                            diagnosis.setEditable(false);
                        }
                    break;

                case NEW:
                    Patient addedPatient = new Patient("","","","");
                  //  return addedPatient;
                    break;

                        }
                    }







            /**
             * Sets the current patient the dialogue will work with.
             */
        public void setPatient(Patient patient) {
            this.selectedPatient = patient;
        }
    }

