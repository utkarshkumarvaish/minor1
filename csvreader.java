//package viewall; // Corrected the package declaration

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.util.*;
import java.io.FileReader;
import java.io.IOException; // Added the import for IOException

public class csvreader { // Renamed the class to follow Java naming conventions
    public static void main(String[] args) throws CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader("criminal updated.csv"))) {
            String[] nextLine;
            Scanner scn = new Scanner(System.in);
            boolean crimeFound = false; // Track if the area is found
            System.out.println("enter the type of crime you want to search");
            String targetType = scn.nextLine();
            System.out.println("ID\tName\tArea\tState\tType of Crime\tDate of Case Registration\tLawyer Assigned\tAge\tCase Status\tCase ID\tDecision\r\n" + //
                                "");
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0) {
                    // Assuming the type of crime is in the fourth column (adjust the index if needed)
                    String typeOfCrime = nextLine[4];
                    if (typeOfCrime.equalsIgnoreCase(targetType)) {
                        // Type of crime found, display the entire row
                        String row = String.join(",", nextLine);
                        System.out.println(row);
                        crimeFound = true;
                    }
                }
            }

            // If the loop completes and no match is found
            if (!crimeFound) {
                System.out.println("No records found for type of crime " + targetType + ".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }       
        catch(CsvValidationException c){
            c.printStackTrace();
            }
    }
}