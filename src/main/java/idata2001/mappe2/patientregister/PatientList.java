package idata2001.mappe2.patientregister;

import java.util.ArrayList;

/**
 * Holds instances of the Patient class.
 */
public class PatientList {
    private ArrayList<Patient> patientList;

    public PatientList() {
        patientList = new ArrayList<>();
    }

    /**
     *Returns the current list of patients the PatientList holds.
     * @return patientList The current list of patients the instance of PatientList holds.
     */
    public ArrayList<Patient> getPatientList() {
    return this.patientList;
}

    /**
     * Takes in an arrayList, and makes that the new PatientList.
     * @param newPatientList An ArrayList holding instances of the Patient class to replace the current ArrayList.
     */
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
