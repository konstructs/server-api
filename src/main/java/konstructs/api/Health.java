package konstructs.api;

/**
 * Health is a class that represents the Health level of a block.
 * The health level is an integer between 0 and {@link Health#MAX_HEALTH}.
 * A block is said to be in {@link Health#PRISTINE pristine} condition when
 * its health is {@link Health#MAX_HEALTH}. If a block has health 0 its
 * condition is {@link Health#DESTROYED destroyed}.
 */
public class Health {
    /**
     * Max health value that can be used
     */
    public static final int MAX_HEALTH = 2047;
    /**
     * Perfect health
     */
    public static final Health PRISTINE = new Health(MAX_HEALTH);
    /**
     * No health left
     */
    public static final Health DESTROYED = new Health(0);

    private final int health;

    /**
     * Creates an immutable Health instance with a given health level.
     * @param health Health level must be >= 0 and < {@link Health#MAX_HEALTH}
     */
    public Health(int health) {
        if(health > MAX_HEALTH)
            throw new IllegalArgumentException("Health can not be higher than " + MAX_HEALTH);
        if(health < 0)
            throw new IllegalArgumentException("Health can not be lower than 0");
        this.health = health;
    }

    /**
     * Returns the health level of this instance
     * @return The health level
     */
    public int getHealth() {
        return health;
    }

    /**
     * Query if the condition of this instance is destroyed
     * @return True if destroyed, otherwise false
     */
    public boolean isDestroyed() {
        return health == 0;
    }

    /**
     * Query if the condition of this instance is pristine
     * @return True if pristine, otherwise false
     */
    public boolean isPristine() {
        return health == MAX_HEALTH;
    }

    /**
     * Returns a new health instance that has suffered the given damage
     * @param damage the amount of damage to be done to this instance
     * @return A new Health instance with its health decreased with damaged or {@link Health#DESTROYED}
     */
    public Health damage(int damage) {
        int newHealth = health - damage;
        if(newHealth > 0) {
            return new Health(newHealth);
        } else {
            return DESTROYED;
        }
    }

    /**
     * Returns a new health instance that has been healed with the given health
     * @param health the amount of health to be healed to this instance
     * @return A new Health instance with its health increased with the health given or {@link Health#PRISTINE}
     */
    public Health heal(int health) {
        int newHealth = this.health + health;
        if(newHealth < MAX_HEALTH) {
            return new Health(newHealth);
        } else {
            return PRISTINE;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Health health1 = (Health) o;

        return health == health1.health;

    }

    @Override
    public int hashCode() {
        return health;
    }

    @Override
    public String toString() {
        return "Health(" +
                "health=" + health +
                ')';
    }
}
