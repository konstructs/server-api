package konstructs.api.messages;

import konstructs.api.View;

/**
 * Created by petter on 2017-07-30.
 */
public class UpdateView {
    private final View view;

    public UpdateView(View view) {
        this.view = view;
    }

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
