package konstructs.api.messages;

import konstructs.api.MetricId;

/**
 * This message sets a metric to a given value.
 */
public class SetMetric {
    private final MetricId id;
    private final long value;

    /**
     * Create a new immutable instance
     * @param id The id of the metric to set
     * @param value The value to set the metric to
     */
    public SetMetric(MetricId id, int value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Returns the id of the metric to be set
     * @return the id of the metric
     */
    public MetricId getId() {
        return id;
    }

    /**
     * Returns the value that the metric is set to
     * @return the value to be set
     */
    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IncreaseMetric(" +
                "id=" + id +
                ", value=" + value +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetMetric that = (SetMetric) o;

        if (value != that.value) return false;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (int) (value ^ (value >>> 32));
        return result;
    }
}
