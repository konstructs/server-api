package konstructs.api.messages;

import konstructs.api.Stack;

/**
 * A message to hand over responsibility of a {@link Stack} to another actor/plugin. This message is used
 * as a response to many other messages like {@link PutStackIntoSlot}, {@link PutViewStack}, {@link RemoveStackFromSlot},
 * {@link RemoveViewStack} and others.
 *
 * The sender must stop using the stack and the receiver can consider the stack received fully owned.
 */
public class ReceiveStack {
    private final Stack stack;

    /**
     * Create an immutable message that hands over a stack
     * @param stack The stack that was handed over
     */
    public ReceiveStack(Stack stack) {
        this.stack = stack;
    }

    /**
     * Returns the stack that was handed over
     * @return The stack that was handed over
     */
    public Stack getStack() {
        return stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiveStack that = (ReceiveStack) o;

        return stack != null ? stack.equals(that.stack) : that.stack == null;

    }

    @Override
    public int hashCode() {
        return stack != null ? stack.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ReceiveStack(" +
                "stack=" + stack +
                ')';
    }
}
