package konstructs.api.messages;

import akka.actor.ActorRef;
import konstructs.api.Block;
import konstructs.api.Position;

/**
 * This class represents the user's tertiary interaction (middle mouse button or key <code>e</code>).
 * It is received by a plugin using {@link InteractTertiaryFilter} message sent from the server.
 * <p>
 *      This event is sent in two phases. It is first sent with the purpose of handling the user's interaction
 *      with the world. It is then sent with the purpose of handling the user's interaction with the block held.
 *      Before both phases all details of this message is resolved. This means that both in the first
 *      (world) and second (held block) phases, if the user is pointing at a block the {@link #getBlockAtPosition()}
 *      and {@link #getPosition()} will return none null values. The same is true if a block is held by the user
 *      the {@link #getBlock()} method will return a none null value in both phases. Therefore
 *      a plugin can in both phases also handle the interaction of a held block with a block in the world.
 * </p>
 */
public class InteractTertiary {
    private final ActorRef sender;
    private final String player;
    private final Position position;
    private final Block block;
    private final Block blockAtPosition;
    private final boolean worldPhase;

    /**
     * Creates a new immutable instance
     * @param sender The player actor that sent the interaction
     * @param player The name of the player
     * @param position The position that was pointed at (null if none)
     * @param block The block held in the players hand (null if none)
     * @param blockAtPosition The block at the position pointed at (null if none)
     * @param worldPhase Is this event sent in the world phase or the block held phase
     */
    public InteractTertiary(ActorRef sender, String player, Position position, Block block, Block blockAtPosition, boolean worldPhase) {
        this.sender = sender;
        this.player = player;
        this.position = position;
        this.block = block;
        this.blockAtPosition = blockAtPosition;
        this.worldPhase = worldPhase;
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
     * Returns the block at the {@link #getPosition() position} pointed at (if any)
     * @return The block pointed at or null if none
     */
    public Block getBlockAtPosition() {
        return blockAtPosition;
    }

    /**
     * Returns if this message was sent in the world interaction or held block phase
     * @return True if this message was sent in the world interaction phase or else false
     * @see InteractTertiary
     */
    public boolean isWorldPhase() {
        return worldPhase;
    }

    /**
     * Create a new instance with the specific block set
     * @param block The block to be set
     * @return The new instance with the new block set
     */
    public InteractTertiary withBlock(Block block) {
        return new InteractTertiary(sender, player, position, block, blockAtPosition, worldPhase);
    }

    /**
     * Create a new instance with a new value for the blockAtPosition property
     * @param blockAtPosition The block to be set for blockAtPosition
     * @return The new instance with the new block set
     */
    public InteractTertiary withBlockAtPosition(Block blockAtPosition) {
        return new InteractTertiary(sender, player, position, block, blockAtPosition, worldPhase);
    }

    /**
     * Create a new instance with a new value for the worldPhase property
     * @param worldPhase Set if the new instance is in the worldPhase or not
     * @return The new instance with the new worldPhase set
     */
    public InteractTertiary withWorldPhase(boolean worldPhase) {
        return new InteractTertiary(sender, player, position, block, blockAtPosition, worldPhase);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InteractTertiary that = (InteractTertiary) o;

        if (worldPhase != that.worldPhase) return false;
        if (!sender.equals(that.sender)) return false;
        if (!player.equals(that.player)) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (block != null ? !block.equals(that.block) : that.block != null) return false;
        return blockAtPosition != null ? blockAtPosition.equals(that.blockAtPosition) : that.blockAtPosition == null;

    }

    @Override
    public int hashCode() {
        int result = sender.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (block != null ? block.hashCode() : 0);
        result = 31 * result + (blockAtPosition != null ? blockAtPosition.hashCode() : 0);
        result = 31 * result + (worldPhase ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InteractTertiary(" +
                "sender=" + sender +
                ", player='" + player + '\'' +
                ", position=" + position +
                ", block=" + block +
                ", blockAtPosition=" + blockAtPosition +
                ", worldPhase=" + worldPhase +
                ')';
    }
}
