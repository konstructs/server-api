package konstructs.utils;

/**
 * ProductionRule is the base class for all LSystem rules. It has a predecessor and a successor,
 * it is ordered by the length of the predecessor.
 * @see LSystem
 */
public abstract class ProductionRule implements Comparable<ProductionRule> {
    /**
     * Return the predecessor to be matched by the LSystem
     * @return The predecessor
     */
    public abstract String getPredecessor();
    public abstract String getSuccessor();

    @Override
    public int compareTo(ProductionRule o) {
        return getPredecessor().length() - o.getPredecessor().length();
    }

}
