package konstructs.api.messages;

import java.io.Serializable;

public class Say implements Serializable {
    private final String player;
    private final String text;

    public Say(String player, String text) {
        this.player = player;
        this.text = text;
    }

    public String getPlayer() {
        return player;
    }

    public String getText() {
        return text;
    }

    public Say withText(String text) {
        return new Say(player, text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Say say = (Say) o;

        if (!player.equals(say.player)) return false;
        return text.equals(say.text);

    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Say(" +
                "player='" + player + '\'' +
                ", text='" + text + '\'' +
                ')';
    }
}
