package idata2001.mappe2.patientregister;

import static org.junit.jupiter.api.Assertions.*;

import idata2001.mappe2.patientregister.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PatientTest {

    @BeforeEach
    public void setUp() {
    }

    /**
     * Tests that a person is constructed properly.
     */
    @Test
    @DisplayName("Patient should be able to be constructed with regular information filled in.")
    public void testPatientGetInfo() {
        String name = "Name";
        String surname = "Surname";
        String doctor = "Doctor";
        String ssn = "01234567890";

        Patient patient = new Patient(name, surname, doctor, ssn);

        assertEquals(name, patient.getFirstName());
        assertEquals(surname, patient.getLastName());
        assertEquals(doctor, patient.getGeneralPractitioner());
        assertEquals(ssn, patient.getSocialSecurityNumber());
        assertNull(patient.getDiagnosis());
    }
}