package konstructs.api.messages;

import konstructs.api.StackAmount;


/**
 * A message received when the player tries to remove a stack from a {@link konstructs.api.InventoryView} that
 * was added to a {@link konstructs.api.View} that was sent to the client via either the {@link ConnectView} or
 * {@link UpdateView} message. The receiving plugin/actor should send a {@link ReceiveStack} with either a stack
 * or null depending on if the clicked position contains/generates a stack.
 */
public class RemoveViewStack {
    private final StackAmount amount;
    private final int position;

    /**
     *
     * @param amount The {@link StackAmount amount of blocks} the player wants
     * @param position The position from which the stack should be removed
     */
    public RemoveViewStack(StackAmount amount, int position) {
        this.amount = amount;
        this.position = position;
    }

    /**
     * Returns the {@link StackAmount amount of blocks} the player wants
     * @return The {@link StackAmount amount of blocks} the player wants
     */
    public StackAmount getAmount() {
        return amount;
    }

    /**
     * Returns the position from which the stack should be removed.
     * Use {@link konstructs.api.InventoryView#contains(int)} to see if this position is inside an inventory view or
     * {@link konstructs.api.InventoryView#translate(int)} to translate it into a slot of an inventory.
     * @return The position from which the stack should be removed
     */
    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoveViewStack that = (RemoveViewStack) o;

        if (position != that.position) return false;
        return amount == that.amount;

    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + position;
        return result;
    }

    @Override
    public String toString() {
        return "RemoveViewStack(" +
                "amount=" + amount +
                ", position=" + position +
                ')';
    }
}
