module patientregister{
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    opens idata2001.mappe2.patientregister to javafx.fxml;
    exports idata2001.mappe2.patientregister;
        }