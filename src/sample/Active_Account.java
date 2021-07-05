package sample;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Active_Account extends Account {

    double balance;
    double transaction_limit;
    String activation_date;
    public ArrayList<transaction> transactions;

    public Active_Account() {

        balance = 0.0;
        transaction_limit = 10000;
        activation_date = null;
        transactions = new ArrayList<transaction>();
    }

    public Active_Account(Account a) {
        super(a.accountID, a.accountCreationDate, a.accountType, a.accountStatus, a.customerID);
        balance = 500.0;
        transaction_limit = 10000;
        activation_date = getDateFormat();
        transactions = new ArrayList<transaction>();
    }

    public Active_Account(Active_Account a) {
        //super(a.accountID, a.accountCreationDate, a.accountType, a.accountStatus, a.customerID);
        balance = a.balance;
        transaction_limit = a.transaction_limit;
        activation_date = a.activation_date;
        transactions = a.transactions;
    }

    public Active_Account(String aid, String creationDate, String accountType, String accountStatus, String cid,
    double b, double limit, String d) { 
    super(aid, creationDate, accountType, accountStatus, cid);
        
        balance = b;
        transaction_limit = limit;
        activation_date = d;
    }

    public String getAccountID() {
        return accountID;
    }

    public void makeTransaction(String type, String recAcc, double amount,
    ArrayList<Active_Account> active_accounts) throws IOException {
        
        transaction t = new transaction(amount, type, this.getAccountID(), this.customerID);
        boolean trans = false;
        if (type.equals("Bank_Transfer"))
            trans = t.startTransaction(this.getAccountID(), recAcc, active_accounts);
        if (trans == true)
            t.RecordTransaction();
        else
            System.out.println("Transaction Failed\n");
    }

    public void makeTransaction(String type, String ConsumerId, double amount, String util,
    ArrayList<Active_Account> active_accounts) throws IOException {

        transaction t = new transaction(amount, type, this.getAccountID(), customerID);
        boolean trans = false;
        if (type.equals("Consumer_Bill"))
            trans = t.StartBillTransaction(this.getAccountID(), ConsumerId, util, active_accounts);
        if (trans == true)
            t.RecordBillTransaction(t);
        else
            System.out.println("Transaction Failed\n");
    }

    public String getDateFormat()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd_hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
