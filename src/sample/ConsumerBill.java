package sample;
import java.io.IOException;
import java.util.ArrayList;

public class ConsumerBill {
    String CustomerAccount;
    String ConsumerID;
    ArrayList<Utility> utilities;

    public ConsumerBill() {

        CustomerAccount = "";
        ConsumerID = "";
        utilities = new ArrayList<Utility>();
        utilities.add(new Utility("Water", "Islamabad Water Company"));
        utilities.add(new Utility("Gas", "Sui Northeren Private Gas Limited"));
        utilities.add(new Utility("Electricity", "IESCO, Islamabad Electric Supply Company"));

    }

    ConsumerBill(String acc, String id) {
        CustomerAccount = acc;
        ConsumerID = id;
    }

    public Boolean PayBill(String util, double amount, ArrayList<Active_Account> active_accounts) throws IOException {
        
        double oldAmount = 0;
        double newAmount = 0;
        
        for (Active_Account AA : active_accounts) {
            if (AA.accountID.equals(CustomerAccount)) {
                oldAmount += AA.balance;
                AA.balance = AA.balance - amount;
                newAmount += AA.balance;
                PersistanceHandler record = (PersistanceHandler) new FileHandler();
                record.UpdatePayBill(AA.accountID, Double.toString(oldAmount), Double.toString(newAmount));
                System.out.println("Bill Payed");
                return true;
            }
        }
        return false;
    }
}
