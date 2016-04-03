package konstructs.utils;

public class ProbabilisticProduction {
    private final int probability;
    private final String successor;

    public ProbabilisticProduction(int probability, String successor) {
        this.probability = probability;
        this.successor = successor;
    }

    public int getProbability() {
        return probability;
    }

    public String getSuccessor() {
        return successor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProbabilisticProduction that = (ProbabilisticProduction) o;

        if (probability != that.probability) return false;
        return successor.equals(that.successor);

    }

    @Override
    public int hashCode() {
        int result = probability;
        result = 31 * result + successor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProbabilisticProduction(" +
                "probability=" + probability +
                ", successor='" + successor + '\'' +
                ')';
    }
}
