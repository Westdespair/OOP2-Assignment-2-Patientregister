package idata2001.mappe2.patientregister;

import javafx.scene.Node;

public class PatientGUIFactory //implements GUIfactory
 {

     public static void main(String[] args) {
         GUIfactory guiFactory = new GUIfactory();

         Node hBox = guiFactory.HBox();
         Node vBox = guiFactory.VBox();
         Node menuBar = guiFactory.MenuBar();
     }

//
//    @Override
//    public void getScene() {
//
//    }
//
//    @Override
//    public void getTable() {
//
//    }
//
//    @Override
//    public void getButtons() {
//
//    }
//
//    @Override
//    public void get() {
//
//    }

}

