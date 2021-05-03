package idata2001.mappe2.patientregister;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public CSVWriter() throws IOException {
    }

    public File convertPatientArrayToCSVFile(PatientList patientList, String path) throws IOException {
        File CSVFile = new File(path);
        FileWriter writer = new FileWriter(CSVFile);

        writer.write("firstName;lastName;generalPractitioner;socialSecurityNumber;diagnosis" + "\n");

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

