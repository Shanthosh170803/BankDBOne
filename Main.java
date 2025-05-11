package org.example;
import model.*;
import reader.*;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String filePath = "CustomerDetails.txt"; // Adjust path if needed

        List<CustomerDetails> customers = CustomerDetailsReader.read(filePath);

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("BankDBOne");
            MongoCollection<Document> collection = database.getCollection("CustomerDetails");

            for (CustomerDetails customer : customers) {
                collection.insertOne(customer.toDocument());
            }
            System.out.println("Inserted " + customers.size() + " valid records into MongoDB.");

            String accFile = "AccountDetails.txt";
            List<AccountDetails> accounts = AccountDetailsReader.read(accFile);
            MongoCollection<Document> accountCollection = database.getCollection("AccountDetails");

            for (AccountDetails acc : accounts) {
                accountCollection.insertOne(acc.toDocument());
            }
            System.out.println("Inserted " + accounts.size() + " valid account records.");

            // Process TransactionDetails
            String transactionFilePath = "TransactionDetails.txt";
            List<TransactionDetails> transactions = TransactionDetailsReader.read(transactionFilePath);
            MongoCollection<Document> transactionCollection = database.getCollection("TransactionDetails");
            for (TransactionDetails tx : transactions) {
                Document doc = new Document("customerId", tx.getCustomerId())
                        .append("customerName", tx.getCustomerName())
                        .append("transactionId", tx.getTransactionId())
                        .append("accountNumber", tx.getAccountNumber())
                        .append("transactionType", tx.getTransactionType())
                        .append("transactionAmount", tx.getTransactionAmount())
                        .append("transactionDateTime", tx.getTransactionDateTime());
                transactionCollection.insertOne(doc);
            }
            System.out.println("Inserted " + transactions.size() + " valid transaction records.");

            // Process LoanDetails
            String loanFilePath = "LoanDetails.txt";
            List<LoanDetails> loans = LoanDetailsReader.read(loanFilePath);
            MongoCollection<Document> loanCollection = database.getCollection("LoanDetails");
            for (LoanDetails loan : loans) {
                Document doc = new Document("customerId", loan.getCustomerId())
                        .append("customerName", loan.getCustomerName())
                        .append("loanId", loan.getLoanId())
                        .append("loanType", loan.getLoanType())
                        .append("loanAmount", loan.getLoanAmount())
                        .append("loanDate", loan.getLoanDate());
//                        .append("duration", loan.getDuration());
                loanCollection.insertOne(doc);
            }
            System.out.println("Inserted " + loans.size() + " valid loan records.");


        } catch (Exception e) {
            System.err.println("MongoDB error: " + e.getMessage());
        }
    }
}