package idata2001.mappe2.patientregister;

import static org.junit.jupiter.api.Assertions.*;

import idata2001.mappe2.patientregister.CSVReader;
import idata2001.mappe2.patientregister.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CSVReaderTest {
    private CSVReader csvReader;

    @BeforeEach
    public void setUp() throws Exception {
        csvReader = new CSVReader();
    }

    @Test
    @DisplayName("The CSVReader should be able to produce a patient from any string written with comma separated values")
    public void testProducePatientFromLineString() {
        String name = "Name";
        String surname = "Surname";
        String doctor = "Doctor";
        String ssn = "01234567890";
        String patientLineString = name + ";" + surname + ";" + doctor + ";" + ssn;

        Patient testPatient = csvReader.producePatientFromCSVLine(patientLineString);

        assertEquals(testPatient.getFirstName(), name);
        assertEquals(testPatient.getLastName(), surname);
        assertEquals(testPatient.getGeneralPractitioner(), doctor);
        assertEquals(testPatient.getSocialSecurityNumber(), ssn);
    }

    @Test
    @DisplayName("The CSVReader should be able to produce a patient from a string with diagnosis")
    public void testProducePatientWithDiagnosisFromLineString() {
        String name = "Name";
        String surname = "Surname";
        String doctor = "Doctor";
        String ssn = "01234567890";
        String diagnosis = "diagnosis";
        String patientLineString = name + ";" + surname + ";" + doctor + ";" + ssn + ";" + diagnosis;

        Patient testPatient = csvReader.producePatientFromCSVLine(patientLineString);

        assertEquals(testPatient.getFirstName(), name);
        assertEquals(testPatient.getLastName(), surname);
        assertEquals(testPatient.getGeneralPractitioner(), doctor);
        assertEquals(testPatient.getSocialSecurityNumber(), ssn);
        assertEquals(testPatient.getDiagnosis(), diagnosis);
    }

    @Test
    @DisplayName("The CSVReader should be able to read a line with too many values, and discard the last ones.")
    public void testProducePatientWithTooManyValues() {
        String name = "Name";
        String surname = "Surname";
        String doctor = "Doctor";
        String ssn = "01234567890";
        String diagnosis = "diagnosis";
        String extraValue = "Don't read me";
        String patientLineString = name + ";" + surname + ";" + doctor + ";" + ssn + ";" + diagnosis + ";" + extraValue;

        Patient testPatient = csvReader.producePatientFromCSVLine(patientLineString);

        assertEquals(name, testPatient.getFirstName());
        assertEquals(surname, testPatient.getLastName());
        assertEquals(doctor, testPatient.getGeneralPractitioner());
        assertEquals(ssn, testPatient.getSocialSecurityNumber());
        assertEquals(diagnosis, testPatient.getDiagnosis());
    }
}