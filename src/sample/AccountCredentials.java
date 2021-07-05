package sample;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class AccountCredentials {

    String customerID;
    String password;
    String lastChangedPasswordTime; // look for Date class in java
    String securityQuestion;

    public AccountCredentials() {
        customerID = "";
        password = "";
        securityQuestion = "";
        lastChangedPasswordTime = "";
        updatePassword();
    }

    String generatePassword() {
        // ASCII range - alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder strpassword = new StringBuilder();

        for (int i = 0; i < 8; i++) { // generate password of 8 characters
            int randomIndex = random.nextInt(chars.length());
            strpassword.append(chars.charAt(randomIndex));
        }
        //long millis = System.currentTimeMillis();
        //java.util.Date date = new java.util.Date(millis);
        lastChangedPasswordTime = getDateFormat();

        return strpassword.toString();
    }

    String updatePassword() {

        this.password = generatePassword();
        // long millis = System.currentTimeMillis();
        // java.util.Date date = new java.util.Date(millis);
        // lastChangedPasswordTime = date;
        lastChangedPasswordTime = getDateFormat();
        return password;
    }

    boolean verifyAnswer(String answer) {
        if (this.securityQuestion.equals(answer))
            return true;
        else
            return false;
    }

    AccountCredentials getCredentials() {
        return this;
    }

    public String getDateFormat()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd_hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

}
