package konstructs.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The ProbabilisticProductionRule class is used in LSystems to produce different outcomes when it is matched.
 * The different outcomes are based on a weight. The different weighted outcomes are represented by the
 * {@link ProbabilisticProduction ProbabilisticProduction} class. The total sum of the weights must equal 100.
 */
public class ProbabilisticProductionRule extends ProductionRule {
    private final String predecessor;
    private final ProbabilisticProduction successors[];

    /**
     * Constructs a immutable ProbabilisticProductionRule
     * @param predecessor The predecessor to be matched by the LSystem
     * @param successors The successors from which one will be randomly chosen
     */
    public ProbabilisticProductionRule(String predecessor, ProbabilisticProduction[] successors) {
        int total = 0;
        for(ProbabilisticProduction successor: successors) {
            total += successor.getProbability();
        }
        if(total != 100)
            throw new IllegalArgumentException("The sum of all successors probability must be 100");
        this.predecessor = predecessor;
        this.successors = successors;
    }

    /**
     * Returns a randomly chosen successor
     * @return The randomly chosen successor
     */
    @Override
    public String getSuccessor() {
        int p = ThreadLocalRandom.current().nextInt(100);
        int current = 0;
        for(ProbabilisticProduction production: successors) {
            int probability = production.getProbability();
            if(p < probability + current) {
                return production.getSuccessor();
            } else {
                current += probability;
            }
        }
        throw new IllegalStateException("Probabilistic productions did not sum up to 100!");
    }

    @Override
    public String getPredecessor() {
        return predecessor;
    }
}
