package konstructs.api;

/**
 * This class represents an event ID. An event ID should follow the same rules as a {@link BlockTypeId}.
 */
public class EventTypeId {
    private final String namespace;
    private final String name;

    /**
     * Create a new immutable EventTypeId from a name string. A name
     * string consist of the namespace appended with a / and then the
     * name, e.g. namespace "org/konstructs" and name "removed" would
     * become "org/konstructs/removed". The name of an event type always
     * begins with a lower case letter.
     * @param id the block id to be parsed for
     * @return a new immutable EventTypeId
     */
    public static EventTypeId fromString(String id) {
        int lastSlash = id.lastIndexOf('/');
        String namespace = id.substring(0, lastSlash);
        String name = id.substring(lastSlash + 1);
        return new EventTypeId(namespace, name);
    }

    /**
     * Creates a new immutable event type id.
     * Name must start with a lower case letter.
     * @param namespace The namespace of this EventTypeId
     * @param name The name of this block type id (must begin with a lower case letter)
     */
    public EventTypeId(String namespace, String name) {
        if(name.length() == 0 || namespace.length() == 0) {
            throw new IllegalArgumentException("Name and namespace must not be empty string");
        }
        if(Character.isUpperCase(name.charAt(0))) {
            throw new IllegalArgumentException("First character of event type name must be lower case");
        }
        this.namespace = namespace;
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventTypeId that = (EventTypeId) o;

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
        return "EventTypeId(" +
                "namespace='" + namespace + '\'' +
                ", name='" + name + '\'' +
                ')';
    }
}
