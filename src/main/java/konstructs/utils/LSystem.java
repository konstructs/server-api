package konstructs.utils;

import java.util.Arrays;
import java.util.Collections;

public class LSystem {

    private static boolean startsWith(StringBuilder builder, String str) {
        final int length = str.length();
        for(int i = 0; i < length; i++) {
            if(builder.charAt(i) != str.charAt(i))
                return false;
        }
        return true;
    }

    private final ProductionRule[] rules;

    public LSystem(ProductionRule[] rules) {
        Arrays.sort(rules);
        Collections.reverse(Arrays.asList(rules));
        this.rules = rules;
    }

    /**
     * Find the first rule that matches the given production. This means that the production
     * starts with the predecessor of the rule. Since the rules are sorted by the longest predecessor
     * first this is guaranteed to be the longest matching rule.
     * @param production The production to be searched for a matching rule
     * @return The matching rule or null if none was found
     */
    private ProductionRule longestMatch(StringBuilder production) {
        for(ProductionRule rule: rules) {
            if(startsWith(production, rule.getPredecessor())) {
                return rule;
            }
        }
        return null;
    }

    public String iterate(String init) {
        StringBuilder current = new StringBuilder();
        StringBuilder production = new StringBuilder(init);
        /* Iterate throught the whole production */
        while(production.length() != 0) {
            ProductionRule match = longestMatch(production);
            if(match != null) {
                /* If there is a matching rule, use its successor, and remove its
                 * predecessor from the production
                 */
                current.append(match.getSuccessor());
                production.delete(0, match.getPredecessor().length());
            } else {
                /* if there is no matching rule, copy the initial character to the result
                 * and remove it from the production
                 */
                current.append(production.charAt(0));
                production = production.deleteCharAt(0);
            }
        }
        return current.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LSystem lSystem = (LSystem) o;

        return Arrays.equals(rules, lSystem.rules);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rules);
    }

    @Override
    public String toString() {
        return "LSystem(" +
                "rules=" + Arrays.toString(rules) +
                ')';
    }
}
