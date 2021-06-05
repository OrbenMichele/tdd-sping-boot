package morben.springframewok;

/**
 * Created by micheleorben on 5/30/21
 */

public class Sum implements Expression {
    final Expression augmend;
    final Expression addmend;

    public Sum(Expression augmend, Expression addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    @Override
    public Money reduce(Bank bank, String to) {
        double amount = augmend.reduce(bank, to).amount
                + addmend.reduce(bank, to).amount;
        return new Money(amount, to);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);//addmend
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augmend.times(multiplier), addmend.times(multiplier));
    }
}
