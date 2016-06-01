package konstructs.api;

import java.util.Arrays;

public class PatternTemplate {
    private final StackTemplate[] stacks;
    private final int columns;
    private final int rows;

    public PatternTemplate(StackTemplate[] stacks, int rows, int columns) {
        this.stacks = stacks;
        this.columns = columns;
        this.rows = rows;
    }

    public StackTemplate[] getStacks() {
        return stacks;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int size() {
        return stacks.length;
    }

    /**
     * Get the complexity of this pattern. The complexity is defined as
     * the total number of blocks of this pattern. E.g. a pattern of 1x1
     * with a stack of 1 block has lower complexity than a pattern of 1x1
     * with a stack of two blocks. Complexity is useful in crafting when
     * two patterns match. One can then choose e.g. the more complex pattern
     * giving the user the ability to remove blocks to craft the other pattern.
     * @return The complexity (i.e. the number of blocks) of this pattern
     */
    public int getComplexity() {
        int complexity = 0;
        for(StackTemplate s: stacks) {
            if(s != null)
                complexity += s.getSize();
        }
        return complexity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatternTemplate that = (PatternTemplate) o;

        if (columns != that.columns) return false;
        if (rows != that.rows) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(stacks, that.stacks);

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(stacks);
        result = 31 * result + columns;
        result = 31 * result + rows;
        return result;
    }

    @Override
    public String toString() {
        return "PatternTemplate(" +
                "stacks=" + Arrays.toString(stacks) +
                ", columns=" + columns +
                ", rows=" + rows +
                ')';
    }
}
