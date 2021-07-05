package sample;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws NullPointerException, IOException {

        System.out.println("CHECKKKK");
        Branch service = new Branch();

        // service.openAccount("test125", "Islamabad", "78383993-85273", 1234567890, 20,
        // "i180424@nu.edu.pk", 0, "-", "Student", "Happy");
        // service.changePassword("1", "Happy");
        // service.authenticateAccount("3");
        // service.authenticateAccount("2");
        // service.bank.display();
        // service.transferMoney("1", "3", 200);
        // service.payBill("1", "123", 100, "Electricity");
        service.applyLoan("1", 5001, "type", "purpose");

        // service.bank.display();
        service.displayBalance("3");
    }
}