package konstructs.api.messages;

import konstructs.api.Stack;

/**
 * Created by petter on 2017-07-27.
 */
public class ReceiveStack {
    private final Stack stack;

    public ReceiveStack(Stack stack) {
        this.stack = stack;
    }

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
