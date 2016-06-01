package konstructs.api;

/**
 * BlockClassId is a class that holds the <code>namespace</code> and
 * <code>name</code> of a class. This information is very static
 * and should never change after a new block class has been introduced
 * into the game. The <code>namespace</code> should preferable be
 * something unique for the plugin, like a domain name or the name of
 * the plugin. Avoid generic namespaces like "stones", "forest". Use
 * namespaces like "your/domain/stones" where are in control of
 * "your.domain" or "steampunk/metal" where "steampunk" is the name of
 * your unique plugin. Since the <code>name</code> is always prefixed
 * by <code>namespace</code> it must only be unique within your
 * plugins namespace. The <code>name</code> of a class always starts
 * with an upper case letter. It is immutable and serializable.
 */
public class BlockClassId {
    /**
     * Create a new immutable BlockClassId from a name string. A name
     * string consist of the namespace appended with a / and then the
     * name, e.g. namespace "org/konstructs" and name "Burnable" would
     * become "org/konstructs/Burnable". The name of a Class must begin
     * with a upper case letter.
     * @param id the block id to be parsed for
     * @return a new immutable BlockClassId
     */
    public static BlockClassId fromString(String id) {
        int lastSlash = id.lastIndexOf('/');
        String namespace = id.substring(0, lastSlash);
        String name = id.substring(lastSlash + 1);
        return new BlockClassId(namespace, name);
    }

    private final String namespace;
    private final String name;

    /**
     * Constructs an immutable BlockClassId.
     * @param namespace the namespace of this BlockClassId
     * @param name the name of this BlockClassId, must begin with an upper case letter
     * @see BlockClassId
     */
    public BlockClassId(String namespace, String name) {
        if(name.length() == 0 || namespace.length() == 0) {
            throw new IllegalArgumentException("Name and namespace must not be empty string");
        }
        if(!Character.isUpperCase(name.charAt(0))) {
            throw new IllegalArgumentException("First character of class name must be upper case");
        }
        this.namespace = namespace;
        this.name = name;
    }

    /**
     * Returns the namespace of this <code>BlockClassId</code>
     * @return the namespace
     * @see BlockClassId
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Returns the name of this <code>BlockClassId</code>
     * @return the name
     * @see BlockClassId
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockClassId that = (BlockClassId) o;

        if (!namespace.equals(that.namespace)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = namespace.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BlockClassId(" +
                "namespace='" + namespace + '\'' +
                ", name='" + name + '\'' +
                ')';
    }
}
