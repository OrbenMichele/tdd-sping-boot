package morben.springframewok;

import java.util.HashMap;

/**
 * Created by micheleorben on 5/30/21
 */

class Bank {

    private final HashMap<Pair, Integer> rateMap = new HashMap<>();

    public Money reduce(Expression source, String toCurrency) {
        return source.reduce(this, toCurrency);
    }

    public void addRate(String from, String to, int rate) {
        rateMap.put(new Pair(from, to), rate);

    }

    public int rate(String from, String to) {
        if (from.equals(to)){
            return 1;
        }
        return rateMap.get(new Pair(from, to));
    }


}
