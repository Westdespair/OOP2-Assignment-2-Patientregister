package idata2001.mappe2.patientregister;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.*;
import java.util.Scanner;

public class CSVReader {
    BufferedReader reader;

    {

        }

        public CSVReader(){



        }

        public String findFile(String fileName) throws IOException {
            String filePath = "src/main/resources/idata2001/mappe2/patientregister/patients.csv";
            String file = "patients.csv";


            try {
                reader = new BufferedReader(new FileReader("patients.csv"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found!");
            }

            return reader.readLine();

        }
    }

