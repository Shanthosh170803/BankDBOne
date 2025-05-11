package model;

public class AccountDetails {
    private String customerId;
    private String customerName;
    private String accountNumber;
    private String accountType;
    private String currentBalance;

    public AccountDetails(String line) throws Exception {
        this.customerId = line.substring(0, 5).trim();
        this.customerName = line.substring(5, 25).trim();
        this.accountNumber = line.substring(25, 36).trim();
        this.accountType = line.substring(36, 46).trim();
        this.currentBalance = line.substring(46).trim();

        validate();
    }

    private void validate() throws Exception {
        if (!customerId.matches("\\d{5}"))
            throw new Exception("Invalid Customer ID: " + customerId);

        if (customerName.length() > 20)
            throw new Exception("Customer Name too long: " + customerName);

        if (!accountNumber.matches("\\d{11}"))
            throw new Exception("Invalid Account Number: " + accountNumber);

//        if (!accountType.matches("(?i)Savings|Current"))
//            throw new Exception("Invalid Account Type: " + accountType);
        if (!accountType.equals("Savings") && !accountType.equals("Current")) {
            throw new Exception("Invalid Account Type: " + accountType);
        }

        if (!currentBalance.matches("\\d{1,9}\\.\\d{2}"))
            throw new Exception("Invalid Current Balance: " + currentBalance);
    }

    public org.bson.Document toDocument() {
        return new org.bson.Document("customerId", customerId)
                .append("customerName", customerName)
                .append("accountNumber", accountNumber)
                .append("accountType", accountType)
                .append("currentBalance", Double.parseDouble(currentBalance));
    }
}
