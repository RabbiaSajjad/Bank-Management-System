package sample;

import java.io.IOException;

public class Branch {

    Bank bank;
    static String AccountID;

    Branch() {
        bank = new Bank();
        AccountID = null;

    }

    public void openAccount(String name, String address, String cnic, int contact_no, int age, String email_address,
             String incomeSource, String AccountType, String securityQuestion) {
        int noOfAccounts = 0;
        bank.openAccount(name, address, cnic, contact_no, age, email_address, noOfAccounts, incomeSource, AccountType,
                securityQuestion);

    }

    public String changePassword(String id, String securityAnswer) {
        String p=  bank.changePassword(id, securityAnswer);
        return p;
    }

    public void authenticateAccount(String Accountno) {
        bank.authenticateAccount(Accountno);
    }

    public void applyLoan(String ID, int amount, String type, String purpose) throws IOException {
        bank.applyLoan(ID, amount, type, purpose);
    }

    public void transferMoney(String senderAcc, String recAcc, double amt) throws IOException {
        bank.transfer_money(senderAcc, recAcc, amt);

    }

    public void payBill(String CustomerAccount, String ConsumerId, double amt, String utility) throws IOException {
        bank.PayCosumerBill(CustomerAccount, ConsumerId, amt, utility);
    }
    public String LogIn(String consumerID, String Password)
    {
        AccountID = bank.LogIN(consumerID,Password);
        return AccountID;
    }
    public String displayBalance(String accID){

        return bank.displayBalance(accID);

    }

}
