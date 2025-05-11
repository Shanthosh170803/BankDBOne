package model;

public class TransactionDetails {
    private String customerId;
    private String customerName;
    private String transactionId;
    private String accountNumber;
    private String transactionType;
    private String transactionAmount;
    private String transactionDateTime;

    public TransactionDetails(String line) throws Exception {
        this.customerId = line.substring(0, 5).trim();
        this.customerName = line.substring(5, 25).trim();
        this.transactionId = line.substring(25, 31).trim();
        this.accountNumber = line.substring(31, 42).trim();
        this.transactionType = line.substring(42, 70).trim(); // handles the long multi-line text field
        this.transactionAmount = line.substring(70, 79).trim();
        this.transactionDateTime = line.substring(79).trim();

        validate();
    }

    private void validate() throws Exception {
        if (!customerId.matches("\\d{5}"))
            throw new Exception("Invalid Customer ID: " + customerId);

        if (customerName.length() > 20)
            throw new Exception("Customer Name exceeds 20 characters: " + customerName);

        if (!transactionId.matches("\\d{6}"))
            throw new Exception("Invalid Transaction ID: " + transactionId);

        if (!accountNumber.matches("\\d{11}"))
            throw new Exception("Invalid Account Number: " + accountNumber);
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getTransactionId() { return transactionId; }
    public String getAccountNumber() { return accountNumber; }
    public String getTransactionType() { return transactionType; }
    public String getTransactionAmount() { return transactionAmount; }
    public String getTransactionDateTime() { return transactionDateTime; }
}
