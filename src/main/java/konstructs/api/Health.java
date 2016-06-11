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

    /**
     * Helper method to calculate the damage received based on
     * the damage and durability fields of block type.
     * @param damage the amount of damage
     * @param durability the amount of durability
     * @return the integer damage to be dealt
     */
    public static int calculateDamage(float damage, float durability) {
        return (int)((damage / durability) * (float)MAX_HEALTH);
    }

    /**
     * Get an immutable health instance.
     *
     * @param health the health level of the instance, must be greater or equal to 0
     *               and smaller than {@link Health#MAX_HEALTH}.
     * @return A new instance, {@link Health#PRISTINE} or {@link Health#DESTROYED}
     */
    public static Health get(int health) {
        if(health == MAX_HEALTH) {
            return PRISTINE;
        } else if(health == 0) {
            return DESTROYED;
        } else {
            return new Health(health);
        }
    }

    private final int health;

    /**
     * Creates an immutable Health instance with a given health level.
     * @param health Health level must be greater or equal to 0 and smaller than {@link Health#MAX_HEALTH}
     */
    Health(int health) {
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
     * @return A new Health instance with its health decreased or {@link Health#DESTROYED}
     */
    public Health damage(int damage) {
        if(damage == 0)
            return this;

        int newHealth = health - damage;
        if(newHealth > 0) {
            return new Health(newHealth);
        } else {
            return DESTROYED;
        }
    }

    /**
     * Returns a new health instance that has suffered damage calculated from
     * a dealing block's {@link BlockType#getDamage() damage} damage and a receiving block's
     * {@link BlockType#getDurability() durability}.
     * @param damage the amount of damage the dealing block has
     * @param durability the amount of durability of the receiving block
     * @return A new Health instance with its health decreased or {@link Health#DESTROYED}
     */
    public Health damage(float damage, float durability) {
        return damage(calculateDamage(damage, durability));
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
