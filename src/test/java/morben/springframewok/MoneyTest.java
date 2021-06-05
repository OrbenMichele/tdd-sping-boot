package morben.springframewok;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by micheleorben on 5/30/21
 */

class MoneyTest {

    @Test
    void testMultiplicationMoney(){
        Money dollar = Money.dollar(5);
        assertEquals(Money.dollar(15), dollar.times(3));

        Money franc = Money.franc(5);
        assertEquals(Money.franc(10), franc.times(2));
    }

    @Test
    void testEquality(){
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.dollar(5), Money.dollar(8));
        assertNotEquals(Money.franc(5), Money.dollar(5));
    }

    @Test
    void testCurrency(){
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    void testSimpleAddition(){
        Money money = Money.dollar(5);
        Expression sum = money.plus(money);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnSum(){
        Money money = Money.dollar(5);
        Expression result = money.plus(money);
        Sum sum = (Sum) result;
        assertEquals(money, sum.augmend);
        assertEquals(money, sum.addmend);
    }

    @Test
    void testReduceSum(){
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney(){
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testReduceMoneyDifferentCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentityRate(){
        assertEquals(1, new Bank().rate("USD", "USD"));
        assertEquals(1, new Bank().rate("CHF", "CHF"));
    }

    @Test
    void testMixedAddition(){
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    void testSumPlusMoney(){
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }

    @Test
    void testSumTimes(){
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }

}