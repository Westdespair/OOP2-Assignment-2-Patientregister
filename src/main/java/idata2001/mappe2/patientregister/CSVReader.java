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

        public String readFile(String fileName) throws IOException {

            try {
                reader = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found!");
            }
            System.out.println(reader.readLine());

            return reader.readLine();

        }

        public ArrayList<Patient> buildPatientListFromCSV() {
        ArrayList<Patient> patientList = new ArrayList<>();

        //Temp return statement to make it shut up.
        return patientList;
        }


    }

