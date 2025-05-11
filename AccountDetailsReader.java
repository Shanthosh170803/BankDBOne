package reader;
import model.AccountDetails;
import java.io.*;
import java.util.*;
public class AccountDetailsReader {
    public static List<AccountDetails> read(String filePath) {
        List<AccountDetails> validRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                try {
                    AccountDetails account = new AccountDetails(line);
                    validRecords.add(account);
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
