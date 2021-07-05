package sample;
//import java.io.FileWriter;
//import java.io.IOException;

public class Loan {
    // -----FIELDS-----
    String customerID, type, purpose, loanApplicationTime;
    int amount;
    // -----METHODS-----

    public Loan() {
        type = "";
        purpose = "";
        amount = 0;
        loanApplicationTime = "";
    }

    public Loan(String ID, int amount, String time, String type, String purpose) {
        this.customerID = ID;
        this.type = type;
        this.purpose = purpose;
        this.amount = amount;
        this.loanApplicationTime = time;
    }

    // public void addLoanToDatabase(int amount,String type, String ID) throws
    // IOException
    // {
    // // open file in writer mode
    // FileWriter fileWriter = new FileWriter("loans.txt");
    // String str = ID + " " + type + " " + amount;
    // // write str into file character by character
    // for (int i = 0; i < str.length(); i++) { fileWriter.write(str.charAt(i)); }
    // fileWriter.close();
    // }

}
