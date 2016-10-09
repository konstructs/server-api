package konstructs.api.messages;

import konstructs.api.BoxShape;

import java.io.Serializable;
/**
 * BoxShapeQuery is a message to query the world for a rectangular volume of
 * blocks. The volume is defined by the BoxShape class. Please see the documentation
 * of the {@link konstructs.api.Box} class for details on how it works.
 * @see konstructs.api.Box
 */
public class BoxShapeQuery implements Serializable {
    private final BoxShape box;

    /**
     * Create a new immutable BoxShapeQuery
     * @param box The box shape to be queried
     */
    public BoxShapeQuery(BoxShape box) {
        this.box = box;
    }

    /**
     * Returns the BoxShape that is queried
     * @return The box shape of this query
     */
    public BoxShape getBox() {
        return box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoxShapeQuery that = (BoxShapeQuery) o;

        return box.equals(that.box);

    }

    @Override
    public int hashCode() {
        return box.hashCode();
    }

    @Override
    public String toString() {
        return "BoxShapeQuery(" +
                "box=" + box +
                ')';
    }
}
