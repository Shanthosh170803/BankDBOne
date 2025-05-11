package model;

public class LoanDetails {
    private String customerId;
    private String customerName;
    private String loanId;
    private String loanType;
    private String loanAmount;
    private String loanDate;
//    private String duration;

    public LoanDetails(String line) throws Exception {
        this.customerId = line.substring(0, 5).trim();
        this.customerName = line.substring(5, 25).trim();
        this.loanId = line.substring(25, 31).trim();
        this.loanAmount = line.substring(31, 43).trim();
        this.loanType = line.substring(43, 63).trim();
        this.loanDate = line.substring(63).trim();

        validate();
    }

    private void validate() throws Exception {
        if (!customerId.matches("\\d{5}"))
            throw new Exception("Invalid Customer ID: " + customerId);

        if (customerName.length() > 20)
            throw new Exception("Customer Name exceeds 20 characters: " + customerName);

        if (!loanId.matches("\\d{6}"))
            throw new Exception("Invalid Loan ID: " + loanId);

        if (!loanType.matches("(?i)Home Loan|Educational Loan|Personal Loan|Business Loan"))
            throw new Exception("Invalid Loan Type: " + loanType);

        if (!loanAmount.matches("\\d+(\\.\\d{1,2})?"))
            throw new Exception("Invalid Loan Amount: " + loanAmount);

        if (!loanDate.matches("\\d{2}-\\d{2}-\\d{4}"))
            throw new Exception("Invalid Loan Date format (DD-MM-YYYY): " + loanDate);
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getLoanId() { return loanId; }
    public String getLoanType() { return loanType; }
    public String getLoanAmount() { return loanAmount; }
    public String getLoanDate() { return loanDate; }
//    public String getDuration() { return duration; }
}
