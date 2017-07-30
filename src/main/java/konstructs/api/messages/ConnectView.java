package konstructs.api.messages;

import akka.actor.ActorRef;
import konstructs.api.View;

/**
 * Created by petter on 2017-07-30.
 */
public class ConnectView {
    private final ActorRef manager;
    private final View view;

    public ConnectView(ActorRef manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    public ActorRef getManager() {
        return manager;
    }

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
