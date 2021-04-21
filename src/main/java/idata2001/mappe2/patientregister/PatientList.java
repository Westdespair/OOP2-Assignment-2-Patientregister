package idata2001.mappe2.patientregister;

import java.util.ArrayList;

public class PatientList {
    private ArrayList<Patient> patientList;

    public PatientList() {
        patientList = new ArrayList<>();
    }

public ArrayList<Patient> getPatientList() {
    return this.patientList;
}

public void setPatientList(ArrayList<Patient> newPatientList) {
        this.patientList = newPatientList;
}

    /**
     * Adds some dummy patients to the list for testing purposes.
     */
    public void fillPatientListWithTestPatients() {
        getPatientList().add(new Patient("Filler","Ister"
                ,"Dr.Acula","123456"));

        getPatientList().add(new Patient("Jonas","Alaska"
            ,"Dr.Acula","73452"));

        getPatientList().add(new Patient("Chris","Christodolou"
            ,"Nick Riviera","234662"));

        getPatientList().add(new Patient("Toon","Boom"
            ,"Francis Rohin","1"));


}
}
