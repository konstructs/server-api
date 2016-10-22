package konstructs.api;

public class LightLevel {
    public static final int DARK_ENCODING = 0;
    public static final int ONE_ENCODING = 1;
    public static final int TWO_ENCODING = 2;
    public static final int THREE_ENCODING = 3;
    public static final int FOUR_ENCODING = 4;
    public static final int FIVE_ENCODING = 5;
    public static final int SIX_ENCODING = 6;
    public static final int SEVEN_ENCODING = 7;
    public static final int EIGHT_ENCODING = 8;
    public static final int NINE_ENCODING = 9;
    public static final int TEN_ENCODING = 10;
    public static final int ELEVEN_ENCODING = 11;
    public static final int TWELVE_ENCODING = 12;
    public static final int THIRTEEN_ENCODING = 13;
    public static final int FOURTEEN_ENCODING = 14;
    public static final int FULL_ENCODING = 15;


    public static final LightLevel DARK = new LightLevel(DARK_ENCODING);
    public static final LightLevel ONE = new LightLevel(ONE_ENCODING);
    public static final LightLevel TWO = new LightLevel(TWO_ENCODING);
    public static final LightLevel THREE = new LightLevel(THREE_ENCODING);
    public static final LightLevel FOUR = new LightLevel(FOUR_ENCODING);
    public static final LightLevel FIVE = new LightLevel(FIVE_ENCODING);
    public static final LightLevel SIX = new LightLevel(SIX_ENCODING);
    public static final LightLevel SEVEN = new LightLevel(SEVEN_ENCODING);
    public static final LightLevel EIGHT = new LightLevel(EIGHT_ENCODING);
    public static final LightLevel NINE = new LightLevel(NINE_ENCODING);
    public static final LightLevel TEN = new LightLevel(TEN_ENCODING);
    public static final LightLevel ELEVEN = new LightLevel(ELEVEN_ENCODING);
    public static final LightLevel TWELVE = new LightLevel(TWELVE_ENCODING);
    public static final LightLevel THIRTEEN = new LightLevel(THIRTEEN_ENCODING);
    public static final LightLevel FOURTEEN = new LightLevel(FOURTEEN_ENCODING);
    public static final LightLevel FULL = new LightLevel(FULL_ENCODING);

    public static LightLevel get(int level) {
        switch(level) {
            case DARK_ENCODING:
                return DARK;
            case ONE_ENCODING:
                return ONE;
            case TWO_ENCODING:
                return TWO;
            case THREE_ENCODING:
                return THREE;
            case FOUR_ENCODING:
                return FOUR;
            case FIVE_ENCODING:
                return FIVE;
            case SIX_ENCODING:
                return SIX;
            case SEVEN_ENCODING:
                return SEVEN;
            case EIGHT_ENCODING:
                return EIGHT;
            case NINE_ENCODING:
                return NINE;
            case TEN_ENCODING:
                return TEN;
            case ELEVEN_ENCODING:
                return ELEVEN;
            case TWELVE_ENCODING:
                return TWELVE;
            case THIRTEEN_ENCODING:
                return THIRTEEN;
            case FOURTEEN_ENCODING:
                return FOURTEEN;
            case FULL_ENCODING:
                return FULL;
            default:
                throw new IllegalArgumentException("No LightLevel encoded by: " + level);
        }
    }

    private final int level;

    LightLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public boolean canIncrease() {
        return level != FULL_ENCODING;
    }

    public boolean canDecrease() {
        return level != DARK_ENCODING;
    }

    public LightLevel decrease() {
        if(level == DARK_ENCODING) {
            return this;
        } else {
            return get(level - 1);
        }
    }

    public LightLevel increase() {
        if(level == FULL_ENCODING) {
            return this;
        } else {
            return get(level + 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LightLevel that = (LightLevel) o;

        return level == that.level;

    }

    @Override
    public int hashCode() {
        return level;
    }

    @Override
    public String toString() {
        return "LightLevel(" +
                "level=" + level +
                ')';
    }
}
