package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class CustomerDetails {
    private String customerId;
    private String customerName;
    private String gender;
    private String dob;
    private String contactNumber;
    private String emailAddress;
    private String address;

    public CustomerDetails(String line) throws Exception {
        this.customerId = line.substring(0, 5).trim();
        this.customerName = line.substring(5, 25).trim();
        this.gender = line.substring(25, 31).trim();
        this.dob = line.substring(31, 41).trim();
        this.contactNumber = line.substring(41, 51).trim();
        this.emailAddress = line.substring(51, 81).trim();
        this.address = line.substring(81).trim(); // up to end of line

        validate();
    }

    private void validate() throws Exception {
        if (!customerId.matches("\\d{5}"))
            throw new Exception("Invalid Customer ID: " + customerId);

        if (customerName.length() > 20)
            throw new Exception("Customer Name too long: " + customerName);

        if (!gender.matches("(?i)Male|Female|Others"))
            throw new Exception("Invalid Gender: " + gender);

        if (!dob.matches("\\d{2}-\\d{2}-\\d{4}") || !isValidDate(dob))
            throw new Exception("Invalid DOB: " + dob);

        if (!contactNumber.matches("\\d{10}"))
            throw new Exception("Invalid Contact Number: " + contactNumber);

        if (!emailAddress.matches("^[a-zA-Z]+\\d*@gmail\\.com$"))
            throw new Exception("Invalid Email Address: " + emailAddress);
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public org.bson.Document toDocument() {
        return new org.bson.Document("customerId", customerId)
                .append("customerName", customerName)
                .append("gender", gender)
                .append("dob", dob)
                .append("contactNumber", contactNumber)
                .append("emailAddress", emailAddress)
                .append("address", address);
    }
}
