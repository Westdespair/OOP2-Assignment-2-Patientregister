package idata2001.mappe2.patientregister;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {
    BufferedReader reader;

    {

        }

        public CSVReader(){



        }

    /**
     *
     * @param fileName The absolute path to a file.
     * @return CSVLineList An arrayList of strings, each being a line of a CSV-file.
     * @throws IOException
     */
        public ArrayList<String> readFile(String fileName) throws IOException {
        ArrayList CSVLineList = new ArrayList<String>();

            try {
                reader = new BufferedReader(new FileReader(fileName));
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
                System.out.println(CSVLineList.get(CSVLineList.size()-1));
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

        for (String CSVLine : CSVLineList) {
            patientList.add(producePatientFromCSVLine(CSVLine));
        }

        return patientList;
        }

    /**
     * Produces one patient from one line with information separated by semicolons.
     * @param CSVLine String a line taken from a CSV-file. This will be a string separated by semicolons.
     * @return Patient a new patient based on the information read from the param line.
     */
    public Patient producePatientFromCSVLine (String CSVLine) {
        String[] splitStringList = CSVLine.split(";");

        return new Patient(splitStringList[0],splitStringList[1] , splitStringList[2], splitStringList[3]);
        }


    }

