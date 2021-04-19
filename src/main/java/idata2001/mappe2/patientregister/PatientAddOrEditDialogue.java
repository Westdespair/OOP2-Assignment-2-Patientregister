package idata2001.mappe2.patientregister;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PatientAddOrEditDialogue extends Application {

        public static void main(String[] args) {
            launch();
        }

        @Override
        public void start(Stage appStage) throws Exception {
            //Fetches the .FXML file of the dialogue.
            Parent root = FXMLLoader.load(getClass().getResource("addOrEditPatientDialogue.fxml"));
            appStage.setTitle("Patientregister");
            //Starts the new scene and sets an initial size.
            appStage.setScene(new Scene(root, 565, 385));
            appStage.setResizable(false);
            appStage.show();

        }
    }

