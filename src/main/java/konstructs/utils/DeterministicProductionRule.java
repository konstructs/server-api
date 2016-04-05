package konstructs.utils;

/**
 * DeterministicProductionRule is a class that defines a simple deterministic rule for an LSystem. If its
 * predecessor is matched it stores a single successor to be used.
 * @see LSystem
 */
public class DeterministicProductionRule extends ProductionRule {
    private final String predecessor;
    private final String successor;

    /**
     * Constructs a immutable DeterministicProductionRule
     * @param predecessor The predecessor to be matched
     * @param successor The successor to return
     */
    public DeterministicProductionRule(String predecessor, String successor) {
        this.predecessor = predecessor;
        this.successor = successor;
    }

    @Override
    public String getPredecessor() {
        return predecessor;
    }

    /**
     * Get the successor of this rule
     * @return The successor
     */
    @Override
    public String getSuccessor() {
        return successor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeterministicProductionRule that = (DeterministicProductionRule) o;

        if (!predecessor.equals(that.predecessor)) return false;
        return successor.equals(that.successor);

    }

    @Override
    public int hashCode() {
        int result = predecessor.hashCode();
        result = 31 * result + successor.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DeterministicProductionRule(" +
                "predecessor='" + predecessor + '\'' +
                ", successor='" + successor + '\'' +
                ')';
    }
}
