package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    Branch branch = new Branch();
    static String NewPassword, bal, AccountID;


    @FXML
    TextField fullname = new TextField();
    @FXML
    TextField accid = new TextField();
    @FXML
    TextField cnic = new TextField();
    @FXML
    TextField age = new TextField();
    @FXML
    TextField address = new TextField();
    @FXML
    TextField email = new TextField();
    @FXML
    TextField sourceofincome = new TextField();
    @FXML
    TextField securityquestion = new TextField();
    @FXML
    TextField contactno = new TextField();
    @FXML
    TextField accounttype = new TextField();
    @FXML
    TextField pass = new TextField();
    @FXML
    TextField updatedpass = new TextField();
    @FXML
    TextField CID = new TextField();
    @FXML
    PasswordField Pass = new PasswordField();
    @FXML
    private static Label uppass = new Label();
    @FXML
    TextField recepiantacc = new TextField();
    @FXML
    TextField amount = new TextField();
    @FXML
    TextField checkbalance = new TextField();
    @FXML
    TextField transaction = new TextField();
    @FXML
    TextField utility = new TextField();
    @FXML
    TextField purpose = new TextField();
    @FXML
    TextField type = new TextField();

    @FXML
    public void HomeScene(ActionEvent event) throws IOException {
        Button signup = (Button) event.getSource();
        String id = CID.getText();
        String pass =Pass.getText();
        AccountID = accid.getText();

        String msg = branch.LogIn(id, pass);
        if(msg.equals("Unsuccessful Login"))
        {

            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane anotherRoot = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage SecondStage = (Stage) signup.getScene().getWindow();
            SecondStage.setTitle("Bank of FAST");
            SecondStage.setScene(new Scene(anotherRoot, 660, 500));
            SecondStage.show();
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane anotherRoot = FXMLLoader.load(getClass().getResource("Options.fxml"));
            Stage SecondStage = (Stage) signup.getScene().getWindow();
            SecondStage.setTitle("Bank of FAST");
            SecondStage.setScene(new Scene(anotherRoot, 660, 500));
            SecondStage.show();
        }
    }
    @FXML
    public void SignUp (ActionEvent event) throws IOException
    {
        Button signup = (Button) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("SecondStage.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();
    }

    public void CreateAccount(ActionEvent event) throws IOException {
        Button signup = (Button) event.getSource();
        String FullName = fullname.getText();
        String CNIC = cnic.getText();
        String Age = age.getText();
        String Address = address.getText();
        String Email = email.getText();
        String SecurityQuestion = securityquestion.getText();
        String SourceofIncome = sourceofincome.getText();
        String AccountType = accounttype.getText();
        String ContactNo = contactno.getText();
        Integer int_age = Integer.valueOf(Age);
        Integer int_contact = Integer.valueOf(ContactNo);

        System.out.println(FullName + Address + CNIC + int_contact +" " +int_age + Email + SourceofIncome + AccountType + SecurityQuestion);
        branch.openAccount(FullName, Address, CNIC, int_contact, int_age, Email, SourceofIncome, AccountType, SecurityQuestion);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();
    }
    @FXML
    public void SignIn (ActionEvent event) throws IOException
    {
        Button signup = (Button) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();
    }
    @FXML
    public void MoneyTransferScene (ActionEvent event) throws IOException
    {
        Button signup = (Button) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("MoneyTransfer.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();
    }
    @FXML
    public void MoneyTransfer (ActionEvent event) throws IOException
    {
        String RecepiantAcc = recepiantacc.getText();
        String Amount = amount.getText();
        Double amt = Double.valueOf(Amount);

        branch.transferMoney(AccountID, RecepiantAcc,amt);

    }
    @FXML
    public void BillPaymentScene (ActionEvent event) throws IOException
    {
        Button signup = (Button) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("BillPayment.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();
    }
    @FXML
    public void BillPayment (ActionEvent event) throws IOException
    {
        String RecepiantAcc = CID.getText();
        String Amount = amount.getText();
        String Utility = utility.getText();
        Double amt = Double.valueOf(Amount);

        branch.payBill(AccountID, RecepiantAcc, amt, Utility);

    }
    @FXML
    public void ApplyForLoanScene (ActionEvent event) throws IOException
    {
        Button signup = (Button) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("ApplyForLoan.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();
    }
    @FXML
    public void ApplyForLoan (ActionEvent event) throws IOException
    {
        String Purpose = purpose.getText();
        String Amount = amount.getText();
        String Type = type.getText();
        Integer amt = Integer.valueOf(Amount);

        branch.applyLoan(AccountID, amt, Type, Purpose);

    }
    @FXML
    public void ChangePasswordScene (ActionEvent event) throws IOException
    {
        Button signup = (Button) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();
    }
    @FXML
    public void ChangePassword(ActionEvent event) throws IOException {
        Button signup = (Button) event.getSource();
        String SecurityQuestion = securityquestion.getText();
        String Pass = pass.getText();

        NewPassword = branch.changePassword(Pass, SecurityQuestion);
        System.out.println("Pass:   " + NewPassword);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("NewPassword.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();

    }
    @FXML
    public void handle(ActionEvent event) throws IOException {
        updatedpass.setText(NewPassword);

    }
    @FXML
    public void CheckBalanceScene(ActionEvent event) throws IOException {

        Button signup = (Button) event.getSource();
        bal = branch.displayBalance(AccountID);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("Options.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();

    }
    @FXML
    public void Balancehandle(ActionEvent event) throws IOException {
        System.out.println("I am here");
        checkbalance.setText(bal);

    }
    @FXML
    public void TransactionHistoryScene(ActionEvent event) throws IOException{
        Button signup = (Button) event.getSource();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anotherRoot = FXMLLoader.load(getClass().getResource("TransactionHistory.fxml"));
        Stage SecondStage = (Stage) signup.getScene().getWindow();
        SecondStage.setTitle("Bank of FAST");
        SecondStage.setScene(new Scene(anotherRoot, 660, 500));
        SecondStage.show();
    }
    public  void Transactionhandle(ActionEvent event) throws IOException {
        for(transaction trans : branch.bank.transactions)
            transaction.setText( trans.accountID + trans.trans_type + trans.trans_id + trans.dateAndTime);
    }




}
