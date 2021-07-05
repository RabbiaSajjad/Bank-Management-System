package sample;
public class Account {
    String accountID;
    String accountCreationDate;
    String accountType;
    String accountStatus;
    String customerID;

    public Account() {
        accountID = new String();
        accountCreationDate = new String();
        accountType = new String();
        accountStatus = new String();
        customerID = new String();
        accountStatus = "Pending";

    }

    public Account(Account A) {
        accountID = A.accountID;
        accountCreationDate = A.accountCreationDate;
        accountType = A.accountType;
        accountStatus = A.accountStatus;
        customerID = A.customerID;
    }

    public Account(String id, String date, String type, String accStatus, String cusid) {
        accountID = id;
        accountCreationDate = date;
        accountType = type;
        accountStatus = accStatus;
        customerID = cusid;
    }

    /*
     * public void CheckAccount(String s, String r, double a,
     * ArrayList<Active_Account> active_accounts) throws IOException {
     * 
     * for (int i = 0; i < active_accounts.size(); i++) { if
     * (active_accounts.get(i).getAccountID().equals(s)) { transaction start = new
     * transaction(a, "Money"); start.startTransaction(s, r, active_accounts);
     * start.RecordTransaction(start);
     * active_accounts.get(i).transactions.add(start); } } }
     */

    /*
     * public void CheckAccount(String CusAcc, String CusId, double amt, String
     * util, ArrayList<Active_Account> active_accounts) throws IOException {
     * 
     * for (int i = 0; i < active_accounts.size(); i++) { if
     * (active_accounts.get(i).getAccountID().equals(CusAcc)) { transaction start =
     * new transaction(amt, "ComsumerBill"); start.StartBillTransaction(CusAcc,
     * CusId, util, active_accounts); start.RecordTransaction(start);
     * active_accounts.get(i).transactions.add(start); } } }
     */

    void createAccount(String accounttype) {

        System.out.println("Account Created");
    }

    String getAccountNumber() {
        return accountID;
    }

    Active_Account updateAccountStatus() {
        this.accountStatus = "Active";
        Active_Account A = new Active_Account(this);
        return A;
    }
}
