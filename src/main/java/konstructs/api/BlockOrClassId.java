package konstructs.api;

public class BlockOrClassId {

    public static BlockOrClassId fromString(String blockOrClassId) {
        int lastSlash = blockOrClassId.lastIndexOf('/');
        String namespace = blockOrClassId.substring(0, lastSlash);
        String name = blockOrClassId.substring(lastSlash + 1);
        return new BlockOrClassId(namespace, name);
    }

    private final BlockTypeId blockTypeId;
    private final BlockClassId blockClassId;

    public BlockOrClassId(String namespace, String name) {
        if(name.length() == 0) {
            throw new IllegalArgumentException("Name must be at least one character");
        }
        if(Character.isUpperCase(name.charAt(0))) {
            this.blockTypeId = null;
            this.blockClassId = new BlockClassId(namespace, name);
        } else {
            this.blockTypeId = new BlockTypeId(namespace, name);
            this.blockClassId = null;
        }
    }

    public BlockOrClassId(BlockTypeId blockTypeId) {
        this.blockTypeId = blockTypeId;
        this.blockClassId = null;
    }

    public BlockOrClassId(BlockClassId blockClassId) {
        this.blockTypeId = null;
        this.blockClassId = blockClassId;
    }

    public BlockTypeId getBlockTypeId() {
        return blockTypeId;
    }

    public BlockClassId getBlockClassId() {
        return blockClassId;
    }

    public boolean isBlocKTypeId() {
        return blockTypeId !=null;
    }

    public boolean isBlockClassId() {
        return blockClassId != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockOrClassId that = (BlockOrClassId) o;

        if (blockTypeId != null ? !blockTypeId.equals(that.blockTypeId) : that.blockTypeId != null) return false;
        return blockClassId != null ? blockClassId.equals(that.blockClassId) : that.blockClassId == null;

    }

    @Override
    public int hashCode() {
        int result = blockTypeId != null ? blockTypeId.hashCode() : 0;
        result = 31 * result + (blockClassId != null ? blockClassId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockOrClassId(" +
                "blockTypeId=" + blockTypeId +
                ", blockClassId=" + blockClassId +
                ')';
    }
}
