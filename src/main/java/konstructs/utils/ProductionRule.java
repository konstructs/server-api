package konstructs.utils;

public abstract class ProductionRule implements Comparable<ProductionRule> {
    public abstract String getPredecessor();
    public abstract String getSuccessor();

    @Override
    public int compareTo(ProductionRule o) {
        return getPredecessor().length() - o.getPredecessor().length();
    }

}
