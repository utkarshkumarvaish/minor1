import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class CSVExample {
    public static void main(String[] args) {
        // Specify the path to your CSV file
        String csvFilePath = "criminal updated.csv";

        // Create a list of data to write to the CSV file
        List<String[]> data = new ArrayList<>();

        data.add(new String[]{"John", "Doe", "30"});
        data.add(new String[]{"Jane", "Smith", "25"});

        // Initialize the CSVWriter in append mode
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath, true))) {
            // Write the data to the CSV file
            writer.writeAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
