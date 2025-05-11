package reader;
import model.LoanDetails;
import java.io.*;
import java.util.*;
public class LoanDetailsReader {
    public static List<LoanDetails> read(String filePath) {
        List<LoanDetails> validRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                try {
                    LoanDetails loan = new LoanDetails(line);
                    validRecords.add(loan);
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
