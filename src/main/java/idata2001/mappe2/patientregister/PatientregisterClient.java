package idata2001.mappe2.patientregister;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PatientregisterClient extends Application {
    private static PatientregisterController controller;

    public static void main(String[] args) {
        launch();
        }

    @Override
    public void start(Stage appStage) throws Exception {
        //Fetches the .FXML file.
                //new PatientregisterController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("patientregisterMainGUI.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController() ;
        appStage.setTitle("Patientregister");

        //Starts the new scene and sets an initial size.
        appStage.setScene(new Scene(root, 565, 385));
        appStage.setResizable(false);
        appStage.show();

        //Opens a dialogue ensuring that the user is aware of their save situation before exiting the application.
        appStage.setOnCloseRequest(e -> {
            try {
                if (controller.closeApplication()){
                    e.consume();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
