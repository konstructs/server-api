package konstructs.api.messages;

import konstructs.api.StackAmount;

/**
 * Created by petter on 2017-07-30.
 */
public class RemoveViewStack {
    private final StackAmount amount;
    private final int position;

    public RemoveViewStack(StackAmount amount, int position) {
        this.amount = amount;
        this.position = position;
    }

    public StackAmount getAmount() {
        return amount;
    }

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
