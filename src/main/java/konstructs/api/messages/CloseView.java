package konstructs.api.messages;

/**
 * Created by petter on 2017-07-30.
 */
public class CloseView {
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
