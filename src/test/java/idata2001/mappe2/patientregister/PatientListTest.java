package idata2001.mappe2.patientregister;

import static org.junit.jupiter.api.Assertions.*;

import idata2001.mappe2.patientregister.Patient;
import idata2001.mappe2.patientregister.PatientList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PatientListTest {
    PatientList patientList;

    @BeforeEach
    public void setUp() throws Exception {
        patientList = new PatientList();
    }

    /**
     * Tests if a patientList can be converted to a different list
     */
    @Test
    @DisplayName("The list should be able to be converted to a different one.")
    public void testPatientListConversion() {
        ArrayList<Patient> newPatientList = new ArrayList();

        patientList.setPatientList(newPatientList);

        assertEquals(newPatientList, patientList.getPatientList());
    }

    @Test
    @DisplayName("")
    public void testPatientList() {

    }

}