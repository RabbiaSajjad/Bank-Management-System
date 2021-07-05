package sample;
public abstract class PersistanceHandler {

    public abstract void SaveTransaction(transaction T);

    public abstract void SaveBillTransaction(transaction T);

    public abstract void SaveCustomer(Customer C);

    public abstract void SaveAccount(Account A);

    public abstract void SaveActiveAccount(Account A, Active_Account AA);

    public abstract void SaveLoan(Loan L);

    public abstract void SaveCredentials(AccountCredentials AC, String customerID);

    public abstract void UpdateCredentials(AccountCredentials AC, AccountCredentials oldCredentials);

    public abstract void UpdateMoneyTransfer(String senderAccountID, String receiverAccountID, String oldSenderBalance,
    String oldReceiverBalance, String newSenderBalance, String newReceiverBalance, String amount, String trans_id);

    public abstract void UpdatePayBill(String id, String amount, String newAmount);
}