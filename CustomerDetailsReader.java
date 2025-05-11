package reader;
import model.CustomerDetails;
import java.io.*;
import java.util.*;
public class CustomerDetailsReader {
    public static List<CustomerDetails> read(String filePath) {
        List<CustomerDetails> validRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                try {
                    CustomerDetails customer = new CustomerDetails(line);
                    validRecords.add(customer);
                } catch (Exception e) {
                    System.err.println("Line " + lineNumber + " skipped: " + e.getMessage());
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.err.println("File read error: " + e.getMessage());
        }
        return validRecords;
    }
}
