package idata2001.mappe2.patientregister;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PatientTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    /**
     * ("Patient should be able to be constructed with regular information filled in.")
     */
    @Test
    @DisplayName("Patient should be able to be constructed with regular information filled in.")
    public void testPatientGetNameWithRegularName() {
        String name = "Name";
        String surname = "Surname";
        String doctor = "Doctor";
        String ssn = "12345";

        Patient patient = new Patient(name, surname, doctor, ssn);

        assertEquals("Name", patient.getFirstName());
        assertEquals("Surname", patient.getLastName());
        assertEquals("Doctor", patient.getGeneralPractitioner());
        assertEquals("12345", patient.getSocialSecurityNumber());
        assertNull(patient.getDiagnosis());

    }

    /**
     *
     */
    public void testPatientGetLastNameWithRegularName() {

    }
}