package konstructs.api.messages;

import konstructs.api.MetricId;

/**
 * This message increases a metric with a given value
 */
public class IncreaseMetric {
    private final MetricId id;
    private final long increase;

    /**
     * Create a new immutable instance
     * @param id The id of the metric to increase
     * @param increase the value to be added to the metric
     */
    public IncreaseMetric(MetricId id, int increase) {
        this.id = id;
        this.increase = increase;
    }

    /**
     * Returns the id of the metric to be increased
     * @return The id of the metric
     */
    public MetricId getId() {
        return id;
    }

    /**
     * Returns the amount that the metric will be increased
     * @return the amount of increase
     */
    public long getIncrease() {
        return increase;
    }

    @Override
    public String toString() {
        return "IncreaseMetric(" +
                "id=" + id +
                ", increase=" + increase +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncreaseMetric that = (IncreaseMetric) o;

        if (increase != that.increase) return false;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (int) (increase ^ (increase >>> 32));
        return result;
    }
}
