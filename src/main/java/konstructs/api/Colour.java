package konstructs.api;

import java.io.Serializable;

public class Colour implements Serializable {
    private static int CHANNEL_COLOURS = 16;
    private static int COLOUR_MAX = CHANNEL_COLOURS - 1;
    /* Look-up table for colours */
    private static Colour COLOURS[] = new Colour[CHANNEL_COLOURS*CHANNEL_COLOURS*CHANNEL_COLOURS];

    static {
        /*
         * Generate look-up table for colour singletons
         */
        for(int r = 0; r < CHANNEL_COLOURS; r++) {
            for(int g = 0; g < CHANNEL_COLOURS; g++) {
                for(int b = 0; b < CHANNEL_COLOURS; b++) {
                    COLOURS[r*CHANNEL_COLOURS*CHANNEL_COLOURS + g*CHANNEL_COLOURS + b] = new Colour(r, g, b);
                }
            }
        }
    }

    public static Colour RED = get(COLOUR_MAX, 0, 0);
    public static Colour GREEN = get(0, COLOUR_MAX, 0);
    public static Colour BLUE = get(0, 0, COLOUR_MAX);
    public static Colour BLACK = get(0, 0, 0);
    public static Colour WHITE = get(COLOUR_MAX, COLOUR_MAX, COLOUR_MAX);

    /**
     * Return a colour instance. Colour channels have values 0 - 15.
     * @param red The amount of red, 0 - 15
     * @param green The amount of green, 0 - 15
     * @param blue The amount of blue, 0 - 15
     * @return The singleton colour instance that matches the parameters
     */
    public static Colour get(int red, int green, int blue) {
        if(red < 0 || red > COLOUR_MAX || green < 0 || green > COLOUR_MAX || blue < 0 || blue > COLOUR_MAX) {
            throw new IllegalArgumentException("All colour channels must have values ranging from 0 to (and including) 15");
        }
        return getUnchecked(red, green, blue);
    }

    /**
     * Return a colour instance. Colour channels have values 0 - 15. If other values are given,
     * the behaviour of this method is undefined. This method is useful when creating a big number
     * of colour objects.
     * @param red The amount of red, 0 - 15
     * @param green The amount of green, 0 - 15
     * @param blue The amount of blue, 0 - 15
     * @return The singleton colour instance that matches the parameters
     */
    public static Colour getUnchecked(int red, int green, int blue) {
        return COLOURS[red*CHANNEL_COLOURS*CHANNEL_COLOURS + green*CHANNEL_COLOURS + blue];
    }

    public static Colour fromRgbHexString(String hex) {
        int r, g, b;
        if(hex.length() == 3) {
            r = Integer.parseInt(hex.substring(0, 1), 16);
            g = Integer.parseInt(hex.substring(1, 2), 16);
            b = Integer.parseInt(hex.substring(2, 3), 16);
        } else if(hex.length() == 6) {
            r = Integer.parseInt(hex.substring(0, 2), 16) >> 4;
            g = Integer.parseInt(hex.substring(2, 4), 16) >> 4;
            b = Integer.parseInt(hex.substring(4, 6), 16) >> 4;
        } else {
            throw new IllegalArgumentException("Rgb hex string must be exactly 3 or 6 characters");
        }
        return get(r, g, b);
    }

    private final int red;
    private final int green;
    private final int blue;

    private Colour(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Returns the amount of red, 0 - 15
     * @return The amount of red
     */
    public int getRed() {
        return red;
    }

    /**
     * Returns the amount of green, 0 - 15
     * @return The amount of green
     */
    public int getGreen() {
        return green;
    }

    /**
     * Returns the amount of blue, 0 - 15
     * @return The amount of blue
     */
    public int getBlue() {
        return blue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Colour colour = (Colour) o;

        if (red != colour.red) return false;
        if (green != colour.green) return false;
        return blue == colour.blue;

    }

    @Override
    public int hashCode() {
        int result = red;
        result = 31 * result + green;
        result = 31 * result + blue;
        return result;
    }

    @Override
    public String toString() {
        return "Colour(" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ')';
    }
}
