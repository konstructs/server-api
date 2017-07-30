package konstructs.api.messages;

import konstructs.api.Stack;

/**
 * Created by petter on 2017-07-30.
 */
public class PutViewStack {
    private final Stack stack;
    private final int position;

    public PutViewStack(Stack stack, int position) {
        this.stack = stack;
        this.position = position;
    }

    public Stack getStack() {
        return stack;
    }

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
