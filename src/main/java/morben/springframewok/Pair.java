package morben.springframewok;

import java.util.Objects;

/**
 * Created by micheleorben on 5/30/21
 */

class Pair {
    
    private final String from;
    private final String to;

    public Pair(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return from.equals(pair.from) &&
                to.equals(pair.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
