package konstructs.utils;

/**
 * ProbabilisticProduction is a class that is used to store successors of a
 * {@link ProbabilisticProductionRule ProbabilisticProductionRule}. It also stores an associated
 * probability with which this successor is chosen. The sum of the probabilities of all successors
 * must be 100 in a given ProbabilisticProductionRule.
 * @see ProbabilisticProductionRule
 * @see LSystem
 */
public class ProbabilisticProduction {
    private final int probability;
    private final String successor;

    /**
     * Constructs an immutable ProbabilisticProduction
     * @param probability The probability with which this production is selected
     * @param successor The successor to be used
     */
    public ProbabilisticProduction(int probability, String successor) {
        this.probability = probability;
        this.successor = successor;
    }

    /**
     * Get the probability of this production
     * @return The probability of this production
     */
    public int getProbability() {
        return probability;
    }

    /**
     * Get the successor associated with this production
     * @return The successor
     */
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
