package konstructs.utils;

public class DeterministicProductionRule extends ProductionRule {
    private final String predecessor;
    private final String successor;

    public DeterministicProductionRule(String predecessor, String successor) {
        this.predecessor = predecessor;
        this.successor = successor;
    }

    @Override
    public String getPredecessor() {
        return predecessor;
    }

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
