package sample;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class transaction {

    int trans_id;
    String trans_type;
    String dateAndTime;
    double amount;
    String accountID;
    String customerID;

    public transaction() {
        trans_id = 0;
        trans_type = "";
        dateAndTime = "";
        amount = 0;
        accountID = "";
        customerID = "";

    }

    public transaction(double a, String type, String accId, String custId) {
        Random rand = new Random();
        trans_id = rand.nextInt(10000);
        trans_type = type;
        amount = a;
        accountID = accId;
        customerID = custId;
        dateAndTime = getDateFormat();
    }

    public Boolean startTransaction(String s, String r, ArrayList<Active_Account> active_accounts) throws IOException {
        MoneyTransfer transfer = new MoneyTransfer(s, r);
        Boolean trans = transfer.AddDeductMoney(amount, active_accounts, Integer.toString(trans_id));
        // PersistanceHandler record = (PersistanceHandler) new FileHandler();
        // record.SaveTransaction(this);
        return trans;
    }

    public void RecordTransaction() {
        PersistanceHandler record = (PersistanceHandler) new FileHandler();
        record.SaveTransaction(this);
    }

    public Boolean StartBillTransaction(String accNo, String id, String util, ArrayList<Active_Account> active_accounts)
            throws IOException {
        ConsumerBill bill = new ConsumerBill(accNo, id);
        Boolean trans = bill.PayBill(util, amount, active_accounts);
        return trans;
    }

    public void RecordBillTransaction(transaction t) {
        PersistanceHandler record = (PersistanceHandler) new FileHandler();
        record.SaveBillTransaction(t);
    }

    public String getDateFormat()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd_hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }
}
