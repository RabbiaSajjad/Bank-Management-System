package sample;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//import jdk.internal.org.objectweb.asm.util.TraceSignatureVisitor;

public class FileRead {

    Account acc_array;
    Active_Account act_array;
    Customer cus_array;
    transaction transaction_array;
    Loan loan_array;
    AccountCredentials credentials_array;

    public FileRead() {
        acc_array = new Account();
        act_array = new Active_Account();
        cus_array = new Customer();
        loan_array = new Loan();
        credentials_array = new AccountCredentials();
    }

    public void ReadCustomers(String filename, ArrayList<Customer> array) 
    throws IOException, FileNotFoundException {

        Scanner in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            cus_array = new Customer();
            String currentLine = in.nextLine();
            String[] words = currentLine.split(" "); 
            cus_array.customerID = words[0];
            cus_array.CNIC = words[1];
            cus_array.fullName = words[2];
            cus_array.address = words[3];
            cus_array.contact_number = Integer.parseInt(words[4]);
            cus_array.email_address = words[5];
            cus_array.noOfAccounts = Integer.parseInt(words[6]);
            cus_array.sourceOfIncome = words[7];
            array.add(cus_array);
        }
        in.close();
    }

    public void ReadAccounts(String filename, ArrayList<Account> array) 
    throws IOException, FileNotFoundException {

        Scanner in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            acc_array = new Account();
            String currentLine = in.nextLine();
            String[] words = currentLine.split(" "); 
            acc_array.accountID = words[0];
            acc_array.accountCreationDate = words[1];
            acc_array.accountType = words[2];
            acc_array.customerID = words[3];
            acc_array.accountStatus = words[4];
            array.add(acc_array);
        }
        in.close();
    }

    public void ReadCredentials(String filename, ArrayList<AccountCredentials> array)
            throws IOException, FileNotFoundException {

        Scanner in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            credentials_array = new AccountCredentials();
            String currentLine = in.nextLine();
            String[] words = currentLine.split(" "); 
            credentials_array.customerID = words[0];
            credentials_array.password = words[1];
            credentials_array.lastChangedPasswordTime = words[2];
            credentials_array.securityQuestion = words[3];
            array.add(credentials_array);
        }
        in.close();
    }

    public void ReadActiveAccounts(String filename, ArrayList<Active_Account> array)
    throws IOException, FileNotFoundException {

        Scanner in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            act_array = new Active_Account();
            String currentLine = in.nextLine();
            String[] words = currentLine.split(" "); 
            act_array.accountID = words[0];
            act_array.balance = Double.parseDouble(words[1]);
            act_array.transaction_limit = Double.parseDouble(words[2]);
            act_array.activation_date = words[3];
            array.add(act_array);
        }
        in.close();
    }

    public void ReadLoans(String filename, ArrayList<Loan> array) 
    throws IOException, FileNotFoundException {

        Scanner in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            loan_array = new Loan();
            String currentLine = in.nextLine();
            String[] words = currentLine.split(" "); 
            loan_array.customerID = words[0];
            loan_array.amount = Integer.parseInt(words[1]);
            loan_array.loanApplicationTime = words[2];
            loan_array.type = words[3];
            loan_array.purpose = words[4];
            array.add(loan_array);
        }
        in.close();
    }

    public void ReadTransactions(String fileName1, String fileName2, ArrayList<transaction> array) 
    throws IOException, FileNotFoundException {

        Scanner in = new Scanner(new File(fileName1));
        while (in.hasNextLine()) {
            transaction_array = new transaction();
            String currentLine = in.nextLine();
            String[] words = currentLine.split(" "); 
            transaction_array.trans_id = Integer.parseInt(words[0]);
            transaction_array.trans_type = words[1];
            transaction_array.accountID = words[2];
            transaction_array.dateAndTime = words[3];
            //transaction_array.customerID = words[4];
            transaction_array.amount = Double.parseDouble(words[4]);
            array.add(transaction_array);
        }
        in.close();

        in = new Scanner(new File(fileName2));
        while (in.hasNextLine()) {
            transaction_array = new transaction();
            String currentLine = in.nextLine();
            String[] words = currentLine.split(" "); 
            transaction_array.trans_id = Integer.parseInt(words[0]);
            transaction_array.trans_type = words[2];
            transaction_array.dateAndTime = words[3];
            transaction_array.accountID = words[1];
            //transaction_array.customerID = words[4];
            transaction_array.amount = Double.parseDouble(words[4]);
            array.add(transaction_array);
        }
        in.close();
    }
}
