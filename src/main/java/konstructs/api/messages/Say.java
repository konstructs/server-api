package konstructs.api.messages;

import java.io.Serializable;

/**
 * Say is an immutable class representing a chat message sent by a user.
 */
public class Say implements Serializable {
    private final String player;
    private final String text;

    /**
     * Construct an immutable instance of the Say message.
     * @param player The name of the player that sent the message
     * @param text The message
     */
    public Say(String player, String text) {
        this.player = player;
        this.text = text;
    }

    /**
     * Returns the name of the player that sent the messge
     * @return The name of the sender
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Returns the message sent
     * @return The message sent
     */
    public String getText() {
        return text;
    }

    /**
     * Returns a new instance with the text changed to the given value
     * @param text The new message text
     * @return A new instance with the new text set
     */
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
