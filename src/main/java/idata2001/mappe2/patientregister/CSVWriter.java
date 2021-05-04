package idata2001.mappe2.patientregister;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Treats lists of patientdata and writes the data to a .CSV file on a given file path.
 */
public class CSVWriter {

    public CSVWriter() throws IOException {
    }

    /**
     *
     * @param patientList The list the patients should be retrieved from.
     * @param path The full path to where the file should be.
     * @return CSVFile a new .csv file containing patient information in the following format:
     * firstName;lastName;SSN;generalPractitioner;diagnosis
     * @throws IOException If the file can not be read properly.
     */
    public File convertPatientArrayToCSVFile(PatientList patientList, String path) throws IOException {
        File CSVFile = new File(path);
        FileWriter writer = new FileWriter(CSVFile);

        //Writes the first line which will always be the same.
        //It is identical to the file received with the assignment, except for the addition of a diagnosis value.
        writer.write("firstName;lastName;generalPractitioner;socialSecurityNumber;diagnosis" + "\n");

        //Writes a single line with patient information for each patient in the given list.
        patientList.getPatientList().forEach(patient -> {
            try {
                writer.write(patient.getFirstName()
                        + ";" + patient.getLastName()
                        + ";" + patient.getSocialSecurityNumber()
                        + ";" + patient.getGeneralPractitioner()
                        + ";" + patient.getDiagnosis() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();

        CSVFile.createNewFile();
        return CSVFile;

    }

}

