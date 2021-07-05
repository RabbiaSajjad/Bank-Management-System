package sample;
public class policy {

    String policies; // Name of the Bank

    public policy()
    {
        policies = "";
    }
    boolean isEligibilityCriteriaFulfilled(int age) {
        if (age > 18)
            return true;

        else
            return false;
    }

    public boolean isLoanEligible(int amount)
    {
        if(amount > 5000 && amount < 10000) 
            { return true; }
        else 
            { return false; } 
        
    }

}