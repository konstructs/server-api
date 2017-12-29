package konstructs.api.messages;

import konstructs.api.View;

/**
 * A message that updates the view connected to a player using the {@link ConnectView}. The view
 * displayed the player will be fully replaced by the view sent in this message.
 */
public class UpdateView {
    private final View view;

    /**
     * Create an immutable message to update the view of the player
     * @param view The view that the player will see
     */
    public UpdateView(View view) {
        this.view = view;
    }

    /**
     * Returns the view that the player will see
     * @return The view that the player will see
     */
    public View getView() {
        return view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateView that = (UpdateView) o;

        return view.equals(that.view);

    }

    @Override
    public int hashCode() {
        return view.hashCode();
    }

    @Override
    public String toString() {
        return "UpdateView(" +
                "view=" + view +
                ')';
    }
}
