package sample;

import java.util.ArrayList;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
//import java.util.*;

public class Bank {

    String name; // Name of the Bank
    String address;
    int contactNumber;
    ArrayList<Customer> customers;
    ArrayList<Account> accounts;
    ArrayList<AccountCredentials> credentials;
    ArrayList<Active_Account> activeAccounts;
    ArrayList<Pending_Account> pendingAccounts;
    ArrayList<transaction> transactions;
    ArrayList<Loan> loans;
    policy bankPolicy;
    FileRead file;
    PersistanceHandler record;

    Bank() {
        name = "FAST - Bank";
        address = "Islamabad, Pakistan";
        contactNumber = 1234567890;
        customers = new ArrayList<Customer>();
        accounts = new ArrayList<Account>();
        credentials = new ArrayList<AccountCredentials>();
        activeAccounts = new ArrayList<Active_Account>();
        pendingAccounts = new ArrayList<Pending_Account>();
        transactions = new ArrayList<transaction>();
        loans = new ArrayList<Loan>();
        bankPolicy = new policy();
        file = new FileRead();
        record = (PersistanceHandler) new FileHandler();


        readData();

    }

    void readData() {

        try {
            file.ReadCustomers("Customers.txt", customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file.ReadAccounts("accounts.txt", accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file.ReadCredentials("credentials.txt", credentials);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file.ReadActiveAccounts("active_accounts.txt", activeAccounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file.ReadTransactions("MoneyTransfers.txt", "BillPayments.txt", transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // try {
        // file.ReadLoans("loans.txt", loans);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }



        // // ADD READ LOANS AND ACCOUNTS AND CREDENTIALS TO CUSTOMERS
        // add accounts and loans
        for (Customer customer : customers) {

            for (Account account : accounts) {
                if (account.customerID.equals(customer.customerID)) {
                    customer.customerAccount.add(account);
                }
            }

            for (Loan loan : loans) {
                if (loan.customerID.equals(customer.customerID)) {
                    customer.customerLoan.add(loan);
                }
            }

            for (AccountCredentials credential : credentials) {
                if (credential.customerID.equals(customer.customerID)) {
                    customer.credentials = credential;
                }
            }
        }
    }

    String LogIN(String id, String pass)
    {
        for(AccountCredentials credential : credentials ) {
            if (credential.customerID.equals(id) && credential.password.equals(pass)) {
                System.out.println("true");
                return "Successful";
            }
            System.out.println("here1");
        }
        return "Unsuccessful Login";
    }

    void openAccount(String name, String address, String cnic, int contact_no, int age, String email_address,
                     int noOfAccounts, String incomeSource, String AccountType, String securityquestion) {
        //PersistanceHandler record = (PersistanceHandler) new FileHandler();

        if (bankPolicy.isEligibilityCriteriaFulfilled(age)) {// is customer eligible
            System.out.println("Criteria fulfilled");

            boolean newCustomer = isItANewCustomer(cnic);

            if (newCustomer) {// is customer new
                Customer C = new Customer(Integer.toString(customers.size() + 1), cnic, name, address, contact_no,
                        email_address, incomeSource, securityquestion);
                // Print and ASK FOR SECURITY QUESTION
                System.out.println("Its a new Customer");
                System.out.println("Password: " + C.credentials.password);
                // add customer to arraylist
                customers.add(C);
                // save new customer to database
                record.SaveCustomer(C);
                // save customer credentials to database
                record.SaveCredentials(C.credentials, C.customerID);

            }
            int index = -1;
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).CNIC.equals(cnic))
                    index = i;
            }

            Account A = new Account(String.valueOf(accounts.size() + 1), getDateFormat(), AccountType,
                    "Pending", customers.get(index).customerID);
            // add account to customer
            accounts.add(A);
            customers.get(index).customerAccount.add(A);
            // save account to database
            record.SaveAccount(A);
        }

    }

    public void applyLoan(String ID, int amount, String type, String purpose) throws IOException {
        if (bankPolicy.isLoanEligible(amount)) {
            //PersistanceHandler record = (PersistanceHandler) new FileHandler();

            Loan loan = new Loan(ID, amount, getDateFormat(), type, purpose);

            for (Customer customer : customers) {
                if (customer.getCustomerID().equals(ID)) { // add loan to customer
                    customer.customerLoan.add(loan);
                    // save loan to database
                    record.SaveLoan(loan);
                }
            }
        } else {
            System.out.println("Loan not elgibile.");
        }

    }

    String changePassword(String id, String securityAnswer) {
        //PersistanceHandler record = (PersistanceHandler) new FileHandler();
        String NewPass = null;
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(id)) {
                System.out.println("Found Customer with Customer ID : " + id);

                if (customer.credentials.verifyAnswer(securityAnswer)) {
                    System.out.println("Valid security answer");
                    // String oldPassword = customer.credentials.password;
                    AccountCredentials oldCredentials = new AccountCredentials();
                    oldCredentials.customerID = customer.credentials.customerID;
                    oldCredentials.password = customer.credentials.password;
                    oldCredentials.securityQuestion = customer.credentials.securityQuestion;
                    oldCredentials.lastChangedPasswordTime = customer.credentials.lastChangedPasswordTime;

                    NewPass =customer.credentials.updatePassword();
                    // update in database
                    record.UpdateCredentials(customer.credentials, oldCredentials);
                    System.out.println("New password: " + customer.credentials.password);
                } else {
                    NewPass = "Invalid Security Answer";
                    System.out.println("Invalid security answer");
                }
            }
        }
        return NewPass;
    }

    void authenticateAccount(String Accountid) {
        for (Account account : accounts) {
            System.out.println("Account Number : " + account.accountID);

            if (account.getAccountNumber().equals(Accountid)) {
                System.out.println("Found Account with Account Number : " + Accountid);

                if(account.accountStatus.equals("Active")) {
                    System.out.println("Account already active");
                }
                else {
                    Account oldAccount = new Account(account);
                    account.updateAccountStatus();
                    Active_Account newActiveAccount = new Active_Account(account);
                    activeAccounts.add(newActiveAccount);
                    record.SaveActiveAccount(oldAccount, newActiveAccount);
                }
                return;

            }

        }
    }

    boolean isItANewCustomer(String cnic) {

        for (Customer customer : customers) {
            if (customer.getCustomerCnic().equals(cnic)) {
                System.out.println("Found Customer with cnic : " + cnic);
                return false;
            }
        }
        return true;

    }

    void PayCosumerBill(String CustomerAccount, String ConsumerId, double amt, String utility) throws IOException {
        for (int i = 0; i < activeAccounts.size(); i++) {
            if (activeAccounts.get(i).accountID.equals(CustomerAccount)) {
                activeAccounts.get(i).makeTransaction("Consumer_Bill", ConsumerId, amt, utility, activeAccounts);
            }
        }

    }

    public void transfer_money(String sender_acc, String rec_acc, double amt) throws IOException {

        Active_Account senderActiveAccount = new Active_Account();
        boolean senderCheck = false;
        boolean receiverCheck = false;

        for (Active_Account AA : activeAccounts) {
            if (AA.accountID.equals(sender_acc)) {senderCheck = true; senderActiveAccount = AA;}
            if (AA.accountID.equals(rec_acc)) {receiverCheck = true;}
        }

        if (senderCheck == true && receiverCheck == true){
            senderActiveAccount.makeTransaction("Bank_Transfer", rec_acc, amt, this.activeAccounts);
        }
        else if(senderCheck == false){
            System.out.println("Sender account not active");
        }
        else if(receiverCheck == false){
            System.out.println("Receiver account not active");
        }
        else{
            System.out.println("Both accounts inactive");
        }
    }

    public String getDateFormat()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd_hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }


    void display() {
        for (Customer customer : customers) {
            System.out.println(customer.getCustomerID());
            // Iterator<Account> it = customer.customerAccount.iterator();

        }

        for (Account account : accounts) {
            System.out.println(account.accountID);
        }

        for (AccountCredentials credential : credentials) {
            System.out.println(credential.customerID);
        }
    }

    public String displayBalance(String accID){
        for (Active_Account AA : activeAccounts){
            if(AA.accountID.equals(accID)){
                System.out.println("Account: " + AA.accountID + " balance: " + AA.balance);
                String bal = Double.toString(AA.balance);
                return bal;
            }
        }
        return "0";
    }
}
