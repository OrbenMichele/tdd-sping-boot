package morben.springframewok;

/**
 * Created by micheleorben on 5/30/21
 */

public interface Expression {

    Money reduce(Bank bank, String to);

    Expression plus(Expression addend);

    Expression times(int multiplier);


}
