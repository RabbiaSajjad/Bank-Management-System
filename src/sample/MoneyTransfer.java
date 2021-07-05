package sample;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.logging.FileHandler;

public class MoneyTransfer {

    String senderAccountID;
    String receiverAccountID;

    public MoneyTransfer() {
        senderAccountID = "";
        receiverAccountID = "";
    }

    public MoneyTransfer(String s, String r) {
        senderAccountID = s;
        receiverAccountID = r;
    }

    public Boolean AddDeductMoney(double amount, ArrayList<Active_Account> active_accounts, String trans_id) throws IOException {
        
        double oldSenderBalance = 0;
        double oldReceiverBalance = 0;
        double newSenderBalance = 0;
        double newReceiverBalance = 0;
        boolean balanceCheck = false;

        // update balance
        for (Active_Account AA : active_accounts) {
            if (AA.accountID.equals(senderAccountID)) {
                if(AA.balance > amount) {
                    oldSenderBalance += AA.balance;
                    AA.balance -= amount;
                    newSenderBalance += AA.balance;
                    //System.out.println(Double.toString(AA.balance));
                    balanceCheck = true;
                }
                else{
                    System.out.println("Transaction failed! Sender does not have enough money.");
                    break;
                }
            }
        }
        if(balanceCheck == true){
            for (Active_Account AA : active_accounts) {
                if (AA.accountID.equals(receiverAccountID)) {
                    oldReceiverBalance += AA.balance;
                    AA.balance = AA.balance + amount;
                    newReceiverBalance += AA.balance;
                    PersistanceHandler record = (PersistanceHandler) new FileHandler();
                    record.UpdateMoneyTransfer(senderAccountID, receiverAccountID, Double.toString(oldSenderBalance),
                    Double.toString(oldReceiverBalance), Double.toString(newSenderBalance), 
                    Double.toString(newReceiverBalance), Double.toString(amount), trans_id);
                    System.out.println("Transaction Successful");
                    return true;
                }
            }
        }
        return false;

        // // write in file
        // // attach a file to FileWriter
        // FileWriter fw = new FileWriter("active_accounts.txt");
        // for (int i = 0; i < active_accounts.size(); i++) {
        //     fw.write(active_accounts.get(i).accountID);
        //     fw.write((int) active_accounts.get(i).balance);
        //     fw.write((int) active_accounts.get(i).transaction_limit);
        //     fw.write(active_accounts.get(i).activation_date);
        // }
        // System.out.println("Writing successful");
        // // close the file
        // fw.close();
        // System.out.println("Reciever's Account Not Found!\n");
    }

}
