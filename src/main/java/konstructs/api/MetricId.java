package konstructs.api;

import java.io.Serializable;

/**
 * MetricId is a class that holds the <code>namespace</code> and
 * <code>name</code> of a metric. It is immutable and serializable.
 */
public class MetricId implements Serializable {
    /**
     * Create a new immutable MetricId from a name string. A name
     * string consist of the namespace appended with a / and then the
     * name, e.g. namespace "org/konstructs" and name "blocks" would
     * become "org/konstructs/blocks".
     * @param id the block id to be parsed for
     * @return a new immutable MetricId
     */
    public static MetricId fromString(String id) {
        int lastSlash = id.lastIndexOf('/');
        String namespace = id.substring(0, lastSlash);
        String name = id.substring(lastSlash + 1);
        return new MetricId(namespace, name);
    }

    private final String namespace;
    private final String name;

    /**
     * Constructs an immutable MetricId.
     * @param namespace the namespace of this MetricId
     * @param name the name of this MetricId
     * @see MetricId
     */
    public MetricId(String namespace, String name) {
        if(name.length() == 0 || namespace.length() == 0) {
            throw new IllegalArgumentException("Name and namespace must not be empty string");
        }
        this.namespace = namespace;
        this.name = name;
    }

    /**
     * Returns the namespace of this <code>MetricId</code>
     * @return the namespace
     * @see MetricId
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Returns the name of this <code>MetricId</code>
     * @return the name
     * @see MetricId
     */
    public String getName() {
        return name;
    }

    public String toMetricString() {
        return  namespace.replace('/','_') + "." + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetricId that = (MetricId) o;

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
        return "MetricId(" +
                "namespace='" + namespace + '\'' +
                ", name='" + name + '\'' +
                ')';
    }
}
