package sample;
import java.util.*;

public class Customer {

    String customerID;
    String CNIC;
    String fullName;
    String address;
    int contact_number;
    String email_address;
    int noOfAccounts;
    String sourceOfIncome;
    ArrayList<Account> customerAccount;
    ArrayList<Loan> customerLoan;
    AccountCredentials credentials;
    FileRead file;

    public Customer() {
        this.customerID = "";
        this.CNIC = "";
        this.address = "";
        this.fullName = "";
        this.contact_number = 0;
        this.email_address = "";
        this.noOfAccounts = 0;
        this.sourceOfIncome = "";
        customerAccount = new ArrayList<Account>();
        customerLoan = new ArrayList<Loan>();
        credentials = new AccountCredentials();

    }

    // void readData() {
    // try {
    // file.ReadCredentials("credentials.txt", credentials);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public Customer(String ID, String cnic, String name, String address, int contact, String email, String income,
            String question) {
        this.customerID = ID;
        this.CNIC = cnic;
        this.address = address;
        this.fullName = name;
        this.contact_number = contact;
        this.email_address = email;
        this.noOfAccounts = 0;
        this.sourceOfIncome = income;

        customerAccount = new ArrayList<Account>();
        credentials = new AccountCredentials();
        this.credentials.customerID = customerID;
        this.credentials.generatePassword();
        this.credentials.securityQuestion = question;

    }

    Customer(String cid, String cnic, String name, String address, int contact, String email, int no, String income,
            String question) {
        this.customerID = cid;
        this.CNIC = cnic;
        this.address = address;
        this.fullName = name;
        this.contact_number = contact;
        this.email_address = email;
        this.noOfAccounts = no;
        this.sourceOfIncome = income;
        customerAccount = new ArrayList<Account>();
        credentials = new AccountCredentials();
        this.credentials.generatePassword();
        this.credentials.securityQuestion = question;

    }

    void addCustomerAccount(Account acc) {
        customerAccount.add(acc);
    }

    // void setCredentials() {

    // }

    String getCustomerCnic() {
        return CNIC;
    }

    Customer getCustomer() {
        return this;
    }

    String getCustomerID() {
        return this.customerID;
    }

}
