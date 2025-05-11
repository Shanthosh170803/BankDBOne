package reader;
import model.TransactionDetails;
import java.io.*;
import java.util.*;
public class TransactionDetailsReader {
    public static List<TransactionDetails> read(String filePath) {
        List<TransactionDetails> validRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                try {
                    TransactionDetails tx = new TransactionDetails(line);
                    validRecords.add(tx);
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
