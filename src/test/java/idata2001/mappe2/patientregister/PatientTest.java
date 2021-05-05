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
    public void testAddRegularPatientNamesInFields() {
        Patient patient = new Patient("Sverre"
                , "Snorre","Støre","1234");

        assertEquals("Sverre", patient.getFirstName());

    }
}