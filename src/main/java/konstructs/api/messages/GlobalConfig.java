package konstructs.api.messages;

/**
 * GlobalConfig is a message that informs the plugin of the current values of
 * the global server config. These values are used by the server to manage
 * overall behaviour of plugins and is informal.
 */
public class GlobalConfig {
    private final float simulationSpeed;

    /**
     * Construct an immutable GlobalConfig message
     * @param simulationSpeed The speed multiplier currently in use. 1.0 is normal speed.
     */
    public GlobalConfig(float simulationSpeed) {
        this.simulationSpeed = simulationSpeed;
    }

    /**
     * Get the simulation speed multiplier currently in use. 1.0 is normal speed.
     * @return The simulation speed multiplier
     */
    public float getSimulationSpeed() {
        return simulationSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalConfig that = (GlobalConfig) o;

        return Float.compare(that.simulationSpeed, simulationSpeed) == 0;

    }

    @Override
    public int hashCode() {
        return (simulationSpeed != +0.0f ? Float.floatToIntBits(simulationSpeed) : 0);
    }

    @Override
    public String toString() {
        return "GlobalConfig(" +
                "simulationSpeed=" + simulationSpeed +
                ')';
    }
}
