package konstructs.api;

import java.util.Arrays;

/**
 * Inventory is a class that manages a inventory.
 * An inventory is an array of stacks. Each position in
 * the array is called a slot. A slot can either be null
 * or contain a stack. A stack is always of at least size 1.
 *
 * A inventory does not have dimensions. To give an Inventory
 * dimension InventoryView is used.
 *
 * @see InventoryView
 */
public final class Inventory {
    /**
     * This method converts an inventory loaded from pre 0.1 JSON format
     * to the 0.1 format.
     * @param inventory The inventory to convert
     * @return A new Inventory converted from the argument
     */
    public static Inventory convertPre0_1(Inventory inventory) {
        Stack stacks[] = new Stack[inventory.getStacks().length];

        for(int i = 0; i < inventory.getStacks().length; i++) {
            stacks[i] = Stack.convertPre0_1(inventory.getStack(i));
        }
        return new Inventory(stacks);
    }

    /**
     * Factory method that creates an empty Inventory of a certain size.
     * @param size The size (number of slots) of the Inventory
     * @return The new Inventory of the given size
     */
    public static Inventory createEmpty(int size) {
        return new Inventory(new Stack[size]);
    }

    /**
     * Factory method that creates an empty Inventory of a certain size.
     * @param rows The number of rows
     * @param columns The number of columns
     * @return The new Inventory of the given size
     */
    public static Inventory createEmpty(int rows, int columns) {
        return new Inventory(new Stack[rows*columns]);
    }

    private final Stack[] stacks;

    /**
     * Construct an immutable inventory
     * @param stacks The array of Stacks that represents the inventories slots
     */
    public Inventory(Stack[] stacks) {
        this.stacks = stacks;
    }

    /**
     * Get the stacks that represent the inventories slots
     * @return The array of stacks
     */
    public Stack[] getStacks() {
        return stacks;
    }

    /**
     * Check if the inventory is empty (e.g. all slots are null)
     * @return True if all slots are null
     */
    public boolean isEmpty() {
        for(Stack s: stacks) {
            if(s != null) return false;
        }
        return true;
    }

    /**
     * Returns a copy of this inventory without the stack in the specified slot
     * @param slot The index of the slot to exclude
     * @return The new inventory without the given slot
     */
    public Inventory withoutSlot(int slot) {
        Stack[] newStacks = Arrays.copyOf(stacks, stacks.length);
        newStacks[slot] = null;
        return new Inventory(newStacks);
    }

    /**
     * Returns a copy of this inventory with the stack on the given slot replaced
     * with the stack provided
     * @param slot The slot of the stack to be replaced
     * @param stack The stack to put at the given slot
     * @return The new inventory with the given stack at the given slot
     */
    public Inventory withSlot(int slot, Stack stack) {
        Stack[] newStacks = Arrays.copyOf(stacks, stacks.length);
        newStacks[slot] = stack;
        return new Inventory(newStacks);
    }

    /**
     * Get the stack at the given slot or null if the slot is empty.
     * @param slot The slot to get the stack from
     * @return The stack at the given slot or null if empty
     */
    public Stack getStack(int slot) {
        return stacks[slot];
    }

    /**
     * Return the Block that is in the front (head) position of the
     * stack identified by slot.
     * @param slot The slot of the stack for which the head should be provided
     * @return The block in the head position of the stack or null if stack is empty
     */
    public Block stackHead(int slot) {
        Stack s = stacks[slot];
        if(s == null) return null;
        return s.getHead();
    }

    /**
     * Return a new Inventory that contains the tail (all elements except the first one)
     * of the stack at the given slot
     * @param slot The slot for which the tail of the stack should be used
     * @return A new inventory without the head block in the stack at the given slot
     * or the unmodified inventory if the slot is empty
     */
    public Inventory stackTail(int slot) {
        Stack stack = stacks[slot];
        if(stack == null) return this;
        return withSlot(slot, stack.getTail());
    }

    /**
     * Check whether this Inventory can accept the given block. Accept here means if
     * there is a stack in the inventory that can accommodate the block or if there is
     * an empty slot into which the block can be put.
     * @param b The block to be checked
     * @return True if the block can be accepted by this inventory
     * @see #accept(Block)
     * @deprecated Since 0.2.1, please use {@link #canAccept(Block)} instead
     */
    @Deprecated
    public boolean accepts(Block b) {
        return canAccept(b);
    }

    /**
     * Check whether this Inventory can accept the given block. Accept here means if
     * there is a stack in the inventory that can accommodate the block or if there is
     * an empty slot into which the block can be put.
     * @param b The block to be checked
     * @return True if the block can be accepted by this inventory
     * @see #accept(Block)
     */
    public boolean canAccept(Block b) {
        for(int i = 0; i < stacks.length; i++) {
            Stack s = stacks[i];
            if(s == null || s.canAccept(b)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Accept a block into this inventory
     * @param block The block to be accepted
     * @return A new inventory with the block in on of its stacks or the unmodified
     *         inventory if the block couldn't be accepted
     * @see #accepts(Block)
     */
    public Inventory accept(Block block) {
       for (int i = 0; i < stacks.length; i++) {
           Stack s = stacks[i];
           if (s != null && s.canAccept(block)) {
               return withSlot(i, s.accept(block));
           }
       }
       for (int i = 0; i < stacks.length; i++) {
           Stack s = stacks[i];
           if (s == null) {
               return withSlot(i, Stack.createFromBlock(block));
           }
       }
       return this;
    }

    /**
     * Check whether this inventory can accept part of (or all of) the blocks
     * in a stack.
     * @param stack The stack to be checked
     * @return True if this inventory can accept parts of the given stack
     * @deprecated Since 0.2.1, please use {@link #canAcceptPartOf(Stack)} instead
     */
    @Deprecated
    public boolean acceptsPartOf(Stack stack) {
        return canAcceptPartOf(stack);
    }


    /**
     * Check whether this inventory can accept part of (or all of) the blocks
     * in a stack.
     * @param stack The stack to be checked
     * @return True if this inventory can accept parts of the given stack
     */
    public boolean canAcceptPartOf(Stack stack) {
        for(int i = 0; i < stacks.length; i++) {
            Stack s = stacks[i];
            if(s == null || s.canAcceptPartOf(stack)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Accept part of (or all of) the blocks in a stack.
     * @param stack The stack to be accepted
     * @return The AcceptResult that contains any part of the given stack
     * that could not be fitted within the inventory as well as a new inventory
     * with the blocks of the given stack added.
     * @see AcceptResult
     */
    public AcceptResult<Inventory> acceptPartOf(Stack stack) {
        Stack left = stack;
        Inventory result = this;
        /* Try to distribute the stack over existing stacks */
        for (int i = 0; i < stacks.length; i++) {
            Stack s = stacks[i];
            if (s != null && s.canAcceptPartOf(left)) {
                AcceptResult<Stack> r = s.acceptPartOf(left);
                if (r.getGiving() == null) {
                    return new AcceptResult<Inventory>(result.withSlot(i, r.getAccepting()), null);
                } else {
                    result = result.withSlot(i, r.getAccepting());
                    left = r.getGiving();
                }
            }
        }
        /* Try to fit any leftovers in an empty stack */
        for (int i = 0; i < stacks.length; i++) {
            Stack s = stacks[i];
            if (s == null) {
                return new AcceptResult<Inventory>(result.withSlot(i, left), null);
            }
        }
        /* Return the leftovers (this may be the complete stack if the inventory is full) */
        return new AcceptResult<Inventory>(result, left);
    }

    /**
     * Remove a certain amount of blocks of a given type. If this inventory does not contain enough
     * blocks, the unmodified inventory is returned.
     * @param blockTypeId The type of block to be removed
     * @param amount The number of blocks to remove
     * @return A new inventory with the number of blocks removed as the
     *         given stack contained or the inventory itself
     */
    public Inventory drop(BlockTypeId blockTypeId, int amount) {
        Stack toRemove = null;
        Inventory result = this;
        for (int i = 0; i < stacks.length; i++) {
            Stack s = stacks[i];
            if (s != null && s.getTypeId().equals(blockTypeId)) {
                if(toRemove == null) {
                    toRemove = s.take(amount);
                    result = withSlot(i, s.drop(toRemove.size()));
                } else {
                    int n = amount - toRemove.size();
                    AcceptResult<Stack> r = toRemove.acceptPartOf(s.take(n));
                    result = withSlot(i, s.drop(n));
                    toRemove = r.getAccepting();
                }
                if(toRemove.size() == amount) {
                    return result;
                }
            }
        }

        /* Not enough blocks in inventory to fully provide stack */
        return this;
    }

    /**
     * Take an amount of blocks of the same BlockTypeId from this inventory and return as a stack. If the inventory
     * does not contain enough blocks, null is returned
     * @param blockTypeId The type of block to be removed
     * @param amount The number of blocks to remove
     * @return A stack with block of the given type with size == amount or null
     */
    public Stack take(BlockTypeId blockTypeId, int amount) {
        Stack toRemove = null;
        for (int i = 0; i < stacks.length; i++) {
            Stack s = stacks[i];
            if (s != null && s.getTypeId().equals(blockTypeId)) {
                if(toRemove == null) {
                    toRemove = s.take(amount);
                } else {
                    int n = amount - toRemove.size();
                    AcceptResult<Stack> r = toRemove.acceptPartOf(s.take(n));
                    toRemove = r.getAccepting();
                }
                if(toRemove.size() == amount) {
                    return toRemove;
                }
            }
        }

        /* Not enough blocks in inventory to fully provide stack */
        return null;
    }

    /**
     * Switch the contents of two slots with each other
     * @param s1 The first stack in the swap
     * @param s2 The second stack in the swap
     * @return A new inventory with the two slots swapped
     */
    public Inventory swapSlot(int s1, int s2) {
        Stack s1Stack = stacks[s1];
        Stack s2Stack = stacks[s2];
        return withSlot(s1, s2Stack).withSlot(s2, s1Stack);
    }

    private static class PatternFrame {
        private final int firstColumn;
        private final int lastColumn;
        private final int firstRow;
        private final int lastRow;

        public PatternFrame(int firstColumn, int lastColumn, int firstRow, int lastRow) {
            this.firstColumn = firstColumn;
            this.lastColumn = lastColumn;
            this.firstRow = firstRow;
            this.lastRow = lastRow;
        }

        public int getFirstColumn() {
            return firstColumn;
        }

        public int getLastColumn() {
            return lastColumn;
        }

        public int getFirstRow() {
            return firstRow;
        }

        public int getLastRow() {
            return lastRow;
        }

        public int getNumberOfStacks() {
            int columns = lastColumn - firstColumn;
            int rows = lastRow - firstRow;
            return columns *rows;
        }

        public int getColumns() {
            return lastColumn - firstColumn;
        }

        @Override
        public String toString() {
            return "PatternFrame(" +
                    "firstColumn=" + firstColumn +
                    ", lastColumn=" + lastColumn +
                    ", firstRow=" + firstRow +
                    ", lastRow=" + lastRow +
                    ')';
        }
    }

    private boolean isRowEmpty(int row, InventoryView view) {
        for(int c = 0; c < view.getColumns(); c++) {
            if(stacks[row * view.getColumns() + c] != null) return false;
        }
        return true;
    }

    private boolean isColumnEmpty(int column, InventoryView view) {
        for(int r = 0; r < view.getRows(); r++) {
            if(stacks[r * view.getColumns() + column] != null) return false;
        }
        return true;
    }

    private PatternFrame getPatternFrame(InventoryView view) {
        int firstRow = 0;
        int lastRow = view.getRows();
        int firstColumn = 0;
        int lastColumn = view.getColumns();

        for(int r = 0; r < view.getRows(); r++) {
            if(isRowEmpty(r, view))
                firstRow = r + 1;
            else
                break;
        }

        for(int r = view.getRows() - 1; r >= 0 ; r--) {
            if(isRowEmpty(r, view))
                lastRow = r;
            else
                break;
        }

        for(int c = 0; c < view.getColumns(); c++) {
            if(isColumnEmpty(c, view))
                firstColumn = c + 1;
            else
                break;
        }

        for(int c = view.getColumns() - 1; c >= 0; c--) {
            if(isColumnEmpty(c, view))
                lastColumn = c;
            else
                break;
        }

        return new PatternFrame(firstColumn, lastColumn, firstRow, lastRow);
    }

    /**
     * Return the smallest area of this inventory that can be seen as a pattern.
     * This is the smallest rectangular area where each row and column at least
     * contains one none-empty slot. Since this is dependent on the layout of a
     * inventory an inventory view describing the layout is required.
     * @param view The InventoryView that describes the layout of the inventory
     * @return The smallest possible pattern or null if inventory is empty
     * @see Pattern
     */
    public Pattern getPattern(InventoryView view) {
        if(isEmpty()) {
            return null;
        } else {
            PatternFrame frame = getPatternFrame(view);
            Stack[] patternStacks = new Stack[frame.getNumberOfStacks()];
            for(int row = frame.getFirstRow(); row < frame.getLastRow(); row++) {
                for(int column = frame.getFirstColumn(); column < frame.getLastColumn(); column++) {
                    Stack s = stacks[row * view.getColumns() + column];
                    patternStacks[(row - frame.getFirstRow()) * frame.getColumns() + column - frame.getFirstColumn()] = s;
                }
            }
            return new Pattern(patternStacks, frame.getLastRow() - frame.getFirstRow(), frame.getLastColumn() - frame.getFirstColumn());
        }
    }

    /**
     * Remove all blocks in a pattern
     * @param pattern The pattern for which blocks should be removed
     * @param factory The block factory that is used to match class IDs
     * @param number The number of times the pattern should be removed
     * @return A new inventory with the pattern removed or if not possible null
     */
    public Inventory remove(PatternTemplate pattern, BlockFactory factory, int number) {
        if(pattern.size() > stacks.length) {
            /* Impossible, invetory is just too small */
            return null;
        } else {
            Stack[] newStacks = new Stack[stacks.length];
            int j = 0;
            for(int i = 0; i < stacks.length; i++) {

                /* if there are no stacks left in pattern,
                 * all stacks of inventory must match null */
                StackTemplate other = null;
                if(j < pattern.size()) {
                    /* Only set other if there are stacks left in pattern */
                    other = pattern.getStacks()[j];
                }

                Stack self = stacks[i];
                if(self == null && other == null) {
                    /* Both are null, nothing to do, check next stack in template */
                    j++;
                } else if(self == null) {
                    /* Self is null, continue to check against next stack in inventory */
                } else if(self.contains(other, factory) >= number) {
                    /* The stack in the inventory contains enough block to match the pattern.
                     * Remove them from the stack and check nect stack in template */
                    j++;
                    newStacks[i] = self.drop(other.getSize() * number);
                } else {
                    /* Stack doesn't match stack template from pattern. This means the pattern
                     * can not match the inventory. Fail!
                     */
                    return null;
                }
            }
            return new Inventory(newStacks);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory inventory = (Inventory) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(stacks, inventory.stacks);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(stacks);
    }

    @Override
    public String toString() {
        return "Inventory(" +
                "stacks=" + Arrays.toString(stacks) +
                ')';
    }
}
