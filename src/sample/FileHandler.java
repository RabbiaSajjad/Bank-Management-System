package sample;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileHandler extends PersistanceHandler {
  @Override
  public void SaveTransaction(transaction T) {

    try {
      FileWriter myWriter = new FileWriter("MoneyTransfers.txt", true);
      myWriter.write(T.trans_id + " " + T.trans_type + " " + T.accountID /*+ " " + T.customerID */
      + " " + T.dateAndTime + " " + T.amount + "\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  @Override
  public void SaveBillTransaction(transaction T) {

    try {
      FileWriter myWriter = new FileWriter("BillPayments.txt", true);
      myWriter.write(T.trans_id + " " + T.accountID + " " + T.trans_type + " "  + T.dateAndTime 
      + " " + T.amount + "\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  @Override
  public void SaveCustomer(Customer C) {
    try {
      FileWriter myWriter = new FileWriter("Customers.txt", true);
      myWriter.write(C.customerID + " " + C.CNIC + " " + C.fullName + " " + C.address);
      myWriter.write(" " + C.contact_number + " " + C.email_address);
      myWriter.write(" " + C.noOfAccounts + " " + C.sourceOfIncome + "\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  @Override
  public void SaveAccount(Account A) {
    try {
      FileWriter myWriter = new FileWriter("accounts.txt", true);
      myWriter.write(A.accountID + " " + A.accountCreationDate + " " + A.accountType);
      myWriter.write(" " + A.customerID + " " + A.accountStatus + "\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  @Override
  public void SaveActiveAccount(Account oldAccount, Active_Account newActiveAccount) {
    try {
      FileWriter myWriter = new FileWriter("active_accounts.txt", true);
      myWriter.write(newActiveAccount.accountID + " " + newActiveAccount.balance + " " +
      newActiveAccount.transaction_limit + " " + newActiveAccount.activation_date + "\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
//----------------------------------------------------
    try {
      File file = new File("accounts.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = "", oldtext = "";
      while ((line = reader.readLine()) != null) {
        oldtext += line + "\r\n";
      }
      reader.close();
      String newtext = oldtext.replaceFirst(oldAccount.accountID + " " + oldAccount.accountCreationDate
      + " " + oldAccount.accountType + " " + oldAccount.customerID + " " + oldAccount.accountStatus,
      newActiveAccount.accountID + " " + newActiveAccount.accountCreationDate + " " + newActiveAccount.accountType 
      + " " + newActiveAccount.customerID + " " + newActiveAccount.accountStatus);
      FileWriter writer = new FileWriter("accounts.txt");
      writer.write(newtext);
      writer.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public void SaveLoan(Loan L) {
    try {
      FileWriter myWriter = new FileWriter("loans.txt", true);
      myWriter.write(L.customerID + " " + L.amount + " " + L.loanApplicationTime + " " + L.type);
      myWriter.write(" " + L.purpose + "\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  @Override
  public void SaveCredentials(AccountCredentials AC, String customerID) {
    try {
      FileWriter myWriter = new FileWriter("credentials.txt", true);
      myWriter.write(AC.customerID + " " + AC.password + " " + AC.lastChangedPasswordTime + " ");
      myWriter.write(AC.securityQuestion + "\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  @Override
  public void UpdateCredentials(AccountCredentials newCredentials, AccountCredentials oldCredentials) {
    try {
      File file = new File("credentials.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = "", oldtext = "";
      while ((line = reader.readLine()) != null) {
        oldtext += line + "\r\n";
      }
      reader.close();
      String newtext = oldtext.replaceFirst(oldCredentials.password + " " + oldCredentials.lastChangedPasswordTime,
      newCredentials.password + " " + newCredentials.lastChangedPasswordTime);
      FileWriter writer = new FileWriter("credentials.txt");
      writer.write(newtext);
      writer.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public void UpdateMoneyTransfer(String senderAccountID, String receiverAccountID, String oldSenderBalance,
  String oldReceiverBalance, String newSenderBalance, String newReceiverBalance, String amount, String trans_id){
    
    // UPDATE ACCOUNT BALANCE
    try {
      File file = new File("active_accounts.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = "", oldtext = "";
      while ((line = reader.readLine()) != null) {
        oldtext += line + "\r\n";
      }
      reader.close();
      String newtext1 = oldtext.replaceFirst(senderAccountID + " " + oldSenderBalance, senderAccountID + " " +
      newSenderBalance);
      String newtext2 = newtext1.replaceFirst(receiverAccountID + " " + oldReceiverBalance, receiverAccountID + " " +
      newReceiverBalance);
      FileWriter writer = new FileWriter("active_accounts.txt");
      writer.write(newtext2);
      writer.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Override
  public void UpdatePayBill(String id, String oldAmount, String newAmount)
  {
    // UPDATE ACCOUNT BALANCE
    try {
      File file = new File("active_accounts.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = "", oldtext = "";
      while ((line = reader.readLine()) != null) {
        oldtext += line + "\r\n";
      }
      reader.close();
      String newtext = oldtext.replaceFirst(id + " " + oldAmount, id + " " + newAmount);
      FileWriter writer = new FileWriter("active_accounts.txt");
      writer.write(newtext);
      writer.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

}
