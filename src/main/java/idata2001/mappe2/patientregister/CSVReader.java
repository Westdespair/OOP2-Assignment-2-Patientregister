package idata2001.mappe2.patientregister;

import java.io.*;
import java.util.ArrayList;

/**
 * Reads .CSV files with patient info.
 * These files can then be processed and changed to an arrayList with instances of Patient.
 */
public class CSVReader {
    BufferedReader reader;

    public CSVReader(){
        }

    /**
     *
     * @param fileName The absolute path to a file.
     * @return CSVLineList An arrayList of strings, each being a line of a CSV-file.
     * @throws Exception IOException
     */
        public ArrayList<String> readFile(String fileName) throws IOException {
        ArrayList CSVLineList = new ArrayList<String>();

        //Attempts to find the file based on the filename input.

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

        } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found!");
            }

            //Reads and skips the first line on its own because we won't be using the first line at this point.
            String lineString = reader.readLine();
            while (lineString != null) {
                lineString = reader.readLine();
                System.out.println(lineString);

                if (lineString != null) {
                    CSVLineList.add(lineString);
                }
            }
            return CSVLineList;
        }

    /**
     * Transforms a list of CSV lines with patient info into an arrayList with patient objects.
     * @param CSVLineList An arrayList of strings that have information separated by semicolons (;).
     *                   Must be formatted as follows:
     *                   firstName;lastName;generalPractitioner;socialSecurityNumber
     * @return patientList an arrayList filled with persons.
     */
    public ArrayList<Patient> buildPatientListFromCSVList(ArrayList<String> CSVLineList) {
        ArrayList<Patient> patientList = new ArrayList<>();

        //Produces a patient from each individual line of the .CSV file.
        for (String CSVLine : CSVLineList) {
            patientList.add(producePatientFromCSVLine(CSVLine));
        }
        return patientList;
        }

    /**
     * Produces one patient from one line with information separated by semicolons.
     * @param CSVLine String a line taken from a CSV-file. This will be a string separated by semicolons.
     * @return readPatient Patient a new patient based on the information read from the param line.
     */
    public Patient producePatientFromCSVLine (String CSVLine) {
        int listLengthWithDiagnosis = 5;

        //Splits strings at the semicolon.
        String[] splitStringList = CSVLine.split(";");

        Patient readPatient = new Patient(splitStringList[0],splitStringList[1] , splitStringList[2], splitStringList[3]);

        //If the length of the list is 5, that means the file also contains a diagnosis field.
        //This needs to be accounted for.
        if (splitStringList.length == listLengthWithDiagnosis) {
            readPatient.setDiagnosis(splitStringList[4]);
        }
        return readPatient;
        }



}

