package konstructs.api.messages;

import akka.actor.ActorRef;
import konstructs.api.View;

/**
 * A message to set up a view (HUD) session with a player. This message will open the client UI (HUD) fully
 * in the client. It can be used to allow the player to e.g. interact with blocks like inventories, machines or crafting.
 */
public class ConnectView {
    private final ActorRef manager;
    private final View view;

    /**
     * Create an immutable message
     * @param manager The actor that should manage (receive messages related to user interaction) the session
     * @param view An initial view to display to the player, can be {@link View#EMPTY empty}.
     */
    public ConnectView(ActorRef manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    /**
     * Returns the ActorRef that will manage the session
     * @return the ActorRef that will manage the session
     */
    public ActorRef getManager() {
        return manager;
    }

    /**
     * Returns the initial view to display to the player
     * @return The initial view to display to the player
     */
    public View getView() {
        return view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectView that = (ConnectView) o;

        if (!manager.equals(that.manager)) return false;
        return view.equals(that.view);

    }

    @Override
    public int hashCode() {
        int result = manager.hashCode();
        result = 31 * result + view.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ConnectView(" +
                "manager=" + manager +
                ", view=" + view +
                ')';
    }

}
