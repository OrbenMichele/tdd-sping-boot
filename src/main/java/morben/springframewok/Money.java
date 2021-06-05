package morben.springframewok;

/**
 * Created by micheleorben on 5/30/21
 */

public class Money implements Expression {

    final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static  Money dollar(double amount){
        return new Money(amount, "USD");
    }

    public static  Money franc(double amount){
        return new Money(amount, "CHF");
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }

    String currency() {
        return currency;
    }

    @Override
    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount
                && this.currency.equals(((Money) object).currency);
    }

    @Override
    public Money reduce(Bank bank, String to){
        //int rate =  (currency.equals("CHF") && to.equals("USD")) ? 2 : 1;
        return new Money(amount / bank.rate(this.currency, to), to);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }
}
