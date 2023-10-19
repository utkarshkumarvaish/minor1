import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreIDsFromCSV {
    public static void main(String[] args) {
        // Specify the path to your CSV file
        String csvFilePath = "criminal updated.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            List<String> idList = new ArrayList<>();

            // Read and store the IDs from the CSV file
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0) {
                    // Assuming the ID is in the first column (adjust the index if needed)
                    String id = nextLine[0];
                    idList.add(id);
                }
            }

            // Display the stored IDs
            System.out.println("Stored IDs:");
            for (String id : idList) {
                System.out.println(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
