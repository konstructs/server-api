package konstructs.api.messages;

import konstructs.api.Stack;

/**
 * A message received when the player tries to put a stack into a {@link konstructs.api.InventoryView} that
 * was added to a {@link konstructs.api.View} that was sent to the client via either the {@link ConnectView} or
 * {@link UpdateView} message. The player actor gives up control of the stack when douing this. If the stack is not
 * used or if any part of the stack is not used, the stack needs to be returned to the sender via a {@link ReceiveStack}
 * message.
 */
public class PutViewStack {
    private final Stack stack;
    private final int position;

    /**
     * Create an immutable message to send a stack from the player to a connected view manager
     * @param stack The stack to be put
     * @param position The position into which the stack should be put
     */
    public PutViewStack(Stack stack, int position) {
        this.stack = stack;
        this.position = position;
    }

    /**
     * Returns the stack to be put
     * @return the stack to be put
     */
    public Stack getStack() {
        return stack;
    }

    /**
     * Returns the position in the {@link konstructs.api.View} that was put into.
     * Use {@link konstructs.api.InventoryView#contains(int)} to see if this position is inside an inventory view or
     * {@link konstructs.api.InventoryView#translate(int)} to translate it into a slot of an inventory.
     * @return The position in the {@link konstructs.api.View} that was put into
     */
    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PutViewStack that = (PutViewStack) o;

        if (position != that.position) return false;
        return stack.equals(that.stack);

    }

    @Override
    public int hashCode() {
        int result = stack.hashCode();
        result = 31 * result + position;
        return result;
    }

    @Override
    public String toString() {
        return "PutViewStack(" +
                "stack=" + stack +
                ", position=" + position +
                ')';
    }
}
