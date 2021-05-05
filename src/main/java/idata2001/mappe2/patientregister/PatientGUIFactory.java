package idata2001.mappe2.patientregister;

import javafx.scene.Node;

//A specific factory creating a GUI for a patientregistry
public class PatientGUIFactory
 {
     public static void main(String[] args) {
         GUIfactory guiFactory = new GUIfactory();

         //Shows an example of how the factory might build a GUI.
         Node patientTableView = guiFactory.TableView();
         Node vBox = guiFactory.VBox();
         Node menuBar = guiFactory.MenuBar();
     }

}

