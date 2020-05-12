public class DebitAccount implements Account{
    private String number;
    private double balance;

    public static final String EMPTY_STRING = "";

    public DebitAccount() {
        this.number = EMPTY_STRING;
        this.balance = 0;
    }

    public DebitAccount(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean checkNumber(String number){
        return this.number.equals(number);
    }

    @Override
    public String toString() {
        return number + " " + balance;
    }
}