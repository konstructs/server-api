package konstructs.api;

public class StackTemplate {
    private final BlockOrClassId id;
    private final int size;

    public StackTemplate(BlockOrClassId id, int size) {
        this.id = id;
        this.size = size;
    }

    public BlockOrClassId getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StackTemplate that = (StackTemplate) o;

        if (size != that.size) return false;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "StackTemplate(" +
                "id=" + id +
                ", size=" + size +
                ')';
    }
}
