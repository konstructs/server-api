package konstructs.api.messages;

/**
 * A singleton message to close an open view (HUD) session.
 *
 * To open a session use {@link ConnectView} message.
 */
public class CloseView {
    /**
     * The singleton message.
     */
    public static final CloseView MESSAGE = new CloseView();
    private CloseView(){
    }
    @Override
    public boolean equals(Object other) {
        return other instanceof CloseView;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
