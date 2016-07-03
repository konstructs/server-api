package konstructs.api.messages;

import akka.actor.ActorRef;
import konstructs.api.Block;
import konstructs.api.Position;

/**
 * This class represents the user's secondary interaction (right mouse button). It is received by a plugin using
 *  {@link InteractSecondaryFilter} message sent from the server.
 */
public class InteractSecondary {
    private final ActorRef sender;
    private final String player;
    private final Position position;
    private final Block block;

    /**
     * Creates a new immutable instance
     * @param sender The player actor that sent the interaction
     * @param player The name of the player
     * @param position The position that was pointed at (null if none)
     * @param block The block held in the players hand (null if none)
     */
    public InteractSecondary(ActorRef sender, String player, Position position, Block block) {
        this.sender = sender;
        this.player = player;
        this.position = position;
        this.block = block;
    }

    /**
     * Returns the player actor that sent this interaction event
     * @return The player actor that sent the event
     */
    public ActorRef getSender() {
        return sender;
    }

    /**
     * Returns the name of the player that sent the interaction even
     * @return The name of the player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Returns the position pointed at if any
     * @return The position pointed at or null if none
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the block held in the players hand if any
     * @return The block held in the players hand or null if none
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Create a new instance with the specific block set
     * @param block The block to be set
     * @return The new instance with the new block set
     */
    public InteractSecondary withBlock(Block block) {
        return new InteractSecondary(sender, player, position, block);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InteractSecondary that = (InteractSecondary) o;

        if (!sender.equals(that.sender)) return false;
        if (!player.equals(that.player)) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        return block != null ? block.equals(that.block) : that.block == null;

    }

    @Override
    public int hashCode() {
        int result = sender.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (block != null ? block.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InteractSecondary(" +
                "sender=" + sender +
                ", player='" + player + '\'' +
                ", position=" + position +
                ", block=" + block +
                ')';
    }
}
