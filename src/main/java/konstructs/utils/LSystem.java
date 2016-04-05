package konstructs.utils;

import java.util.Arrays;
import java.util.Collections;

/**
 * LSystem is a class that represents a <a href="https://en.wikipedia.org/wiki/L-system">Lindenmayer system</a>
 * named after the Hungarian biologist Aristid Lindenmayer. In short, one can say that an LSystem is a way to
 * generate something that evolves over generations (called iterations in the LSystem). In an LSystem what is
 * generated is String. It is generated from an initial string and a set of rules that changes the string each
 * iteration. Wikipedia
 * <a href="https://en.wikipedia.org/wiki/L-system#Example_1:_Algae">has a good example of a simple L-System</a>.
 * In Konstructs it is mainly used to generate text strings that are programs for the
 * {@link BlockMachine BlockMachine} class. One very good example of this is the
 * <a href="https://github.com/konstructs/server-plugin-forest">forest plugin</a> that
 * uses LSystem together with the BlockMachine class to generate trees that evolve over several generations.
 * <p>
 *     This class implements an L-System in the following manner. It has a list of {@link ProductionRule rules}
 *     that are math against the input string. The longest matching rule is used. To know which is the longest
 *     matching rule we need to understand what a rule is. A rule has two values, The <code>predecessor</code>
 *     and the <code>successor</code>. The predecessor is the part of the rule that is matched against the
 *     input and the successor is the part of the rule that will be written to the output. The output of the LSystem
 *     is the next generation. Let's have a look at a simple example:
 * </p>
 * <pre>
 *      {@code
 *     Input string:
 *         AB
 *     Rules:
 *         A -> B
 *         B -> BB
 *         BB -> C
 *     }
 * </pre>
 * <p>
 * Running this system for one iteration would take the input string <code>AB</code> and transform it to
 * <code>BBB</code>. Why? The LSystem tries to find a rule that matches the beginning of the input. There is only one
 * rule that matches {@code A -> B}. It will then write the successor of this rule to the output, i.e. "B".
 * It now moves forward in the input as many characters as the matched rules predecessor, in this case it was "A" so
 * it moves forward only one character. It then tries to match the input again, starting at the new position. This time
 * it only matches the {@code B -> BB} rule so it writes to the output the successor of this rules,
 * <code>BB</code>. Thus the output now contains <code>BBB</code> which is the second generation. If we now run the
 * same system again over the output (that means that the input in the example above is changed to <code>BBB</code>),
 * we get the next iteration. Using the same methodology we see that first the rule {@code BB -> C} matches, since
 * it is the longest of the two matching rules {@code B -> BB} and {@code BB -> C}. Then the we move two
 * characters forward in the input and now only the {@code B -> BB} rules matches. This means that the output
 * of this iteration (the third generation) is <code>CBB</code>. If iterating once more we reached a final generation
 * since only the {@code BB -> C} matches and the output is then <code>CC</code> which can never match any of the
 * rules again. It is also possible (and common) that the rules of an LSystem can continue indefinitely. So that for
 * each successor there will be a predecessor that can match.
 * </p>
 * <p>
 *     How are rules represented when used with this class? There is a base class {@link ProductionRule ProductionRule}
 *     and two concrete rule types. {@link DeterministicProductionRule DeterministicProductionRule} work just like in
 *     the example and {@link ProbabilisticProductionRule ProbabilisticProductionRule} have a set of weighted
 *     successors from which it will randomly choose one (probability is based on the weight). A simple example:
 * </p>
 * <pre>
 *     {@code
 *     ProbabilisticProduction randomProductions[] = {
 *         new ProbabilisticProduction(50, "c"),
 *         new ProbabilisticProduction(50, "ca")
 *     };
 *     ProductionRule rules[] = {
 *         new DeterministicProductionRule("a", "b"),
 *         new ProbabilisticProductionRule("b", randomProductions)
 *     };
 *     LSystem system = new LSystem(rules);
 *     String firstGeneration = system.iterate("a"); // Returns "b"
 *     system.iterate(firstGeneration); // Returns "c" or "ca"
 *     }
 * </pre>
 * */
public class LSystem {

    private static boolean startsWith(StringBuilder builder, String str) {
        final int length = str.length();
        if(builder.length() < length)
            return false;
        for(int i = 0; i < length; i++) {
            if(builder.charAt(i) != str.charAt(i))
                return false;
        }
        return true;
    }

    private final ProductionRule[] rules;

    /**
     * Constructs a new immutable LSystem
     * @param rules The rules that dicates the behaviour of this LSystem
     */
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

    /**
     * Run the LSysytem on a string and produce the next generation of this string.
     * @param generation The current generation of the string
     * @return The next generation of the string as determined by the LSystem rules
     */
    public String iterate(String generation) {
        StringBuilder current = new StringBuilder();
        StringBuilder production = new StringBuilder(generation);
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
