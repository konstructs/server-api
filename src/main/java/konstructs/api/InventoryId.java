package konstructs.api;

/**
 * InventoryId is a class that represents an inventory that resides inside a block. An inventory can be used to store
 * stacks of items and can be of any size. The inventory id has a namespace to be unique and it is important to make
 * sure when creating a new InventoryId that the namespace part is not used by someone else. Please see
 * {@link BlockTypeId} for a better description on how this works.
 *
 * The name part of the inventory id must be all uppercase to differentiate it from {@link BlockTypeId} and
 * {@link BlockClassId}.
 *
 */
public class InventoryId {
    public static final InventoryId STORAGE = fromString("org/konstructs/STORAGE");
    public static final InventoryId INPUT = fromString("org/konstructs/INPUT");
    public static final InventoryId OUTPUT = fromString("org/konstructs/OUTPUT");

    private final String namespace;
    private final String name;

    /**
     * Create a new immutable InventoryId from a name string. A name
     * string consist of the namespace appended with a / and then the
     * name, e.g. namespace "org/konstructs" and name "STORAGE" would
     * become "org/konstructs/STORAGE". The name of an inventory id
     * must contain only uppercase letters.
     * @param id the inventory id to be parsed for
     * @return a new immutable InventoryId
     */
    public static InventoryId fromString(String id) {
        int lastSlash = id.lastIndexOf('/');
        String namespace = id.substring(0, lastSlash);
        String name = id.substring(lastSlash + 1);
        return new InventoryId(namespace, name);
    }

    /**
     * Constructs an immutable InventoryId.
     * @param namespace the namespace of this InventoryId
     * @param name the name of this InventoryId, must be all uppercase letters
     * @see BlockTypeId
     */
    public InventoryId(String namespace, String name) {
        if(name.length() == 0 || namespace.length() == 0) {
            throw new IllegalArgumentException("Name and namespace must not be empty string");
        }
        for(int i = 0; i < name.length(); i++) {
            if(!Character.isUpperCase(name.charAt(i))) {
                throw new IllegalArgumentException("All characters of inventory id name must be upper case");
            }
        }

        this.namespace = namespace;
        this.name = name;
    }

    /**
     * Returns the namespace of this <code>InventoryId</code>
     * @return the namespace
     * @see InventoryId
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Returns the name of this <code>InventoryId</code>
     * @return the name
     * @see InventoryId
     */
    public String getName() {
        return name;
    }

    /**
     * Return the id in its string representation "namespace / name"
     * @return the id in its string representation "namespace / name"
     */
    public String idString() {
        return namespace + "/" + name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryId that = (InventoryId) o;

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
        return "InventoryId(" +
                "namespace='" + namespace + '\'' +
                ", name='" + name + '\'' +
                ')';
    }
}
