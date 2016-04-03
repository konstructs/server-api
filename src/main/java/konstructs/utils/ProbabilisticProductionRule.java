package konstructs.utils;

import java.util.concurrent.ThreadLocalRandom;

public class ProbabilisticProductionRule extends ProductionRule {
    private final String predecessor;
    private final ProbabilisticProduction successors[];

    public ProbabilisticProductionRule(String predecessor, ProbabilisticProduction[] successors) {
        this.predecessor = predecessor;
        this.successors = successors;
    }

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
