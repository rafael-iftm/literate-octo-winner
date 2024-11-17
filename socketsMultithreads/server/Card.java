public class Card {
    private String cardNumber;
    private double balance;

    public Card(String cardNumber, double balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public synchronized boolean deductAmount(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }
}
