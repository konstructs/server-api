package konstructs.api.messages;

import akka.actor.ActorRef;
import konstructs.api.Block;
import konstructs.api.Position;

/**
 * This class represents the result of a user's world interaction. This message is sent by the server
 * or a plugin (automatically by the {@link Filter#drop(ActorRef)} method). It informs the player
 * that the interaction has been processed and the result of the interaction. The result may mean
 * that the values of the data sent in the initial interaction message has been update. One example
 * is that the server will update the {@link Block#getHealth() health} value of the block held if
 * it was used to damage a block in the world and was itself damage while doing so.
 * <p>
 *     <b>
 *         Please note that there is usually no need for a plugin to send this message.
 *     </b>
 *     It is sent by the different Filter methods or the server when interaction is finished and sending it
 *     yourself may result in premature termination of the interaction. If the plugin want to return
 *     updated values of the data in the interaction message, an updated interaction message should be given to
 *     one of the filter methods that supports it: {@link Filter#nextWith(ActorRef, Object)},
 *     {@link Filter#skipWith(ActorRef, Object)} and {@link Filter#dropWith(ActorRef, Object)}
 * </p>
 */
public class InteractResult {
    private final Position position;
    private final Block block;
    private final Block blockAtPosition;

    /**
     * Creates an immutable instance
     * @param position The position that was interacted with (if any)
     * @param block The block that was held during the interaction (if any).
     *              This is possibly an updated version of the block.
     * @param blockAtPosition The block at the position interacted with (if any)
     *                        This is possibly an updated version of the block
     */
    public InteractResult(Position position, Block block, Block blockAtPosition) {
        this.position = position;
        this.block = block;
        this.blockAtPosition = blockAtPosition;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InteractResult that = (InteractResult) o;

        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (block != null ? !block.equals(that.block) : that.block != null) return false;
        return blockAtPosition != null ? blockAtPosition.equals(that.blockAtPosition) : that.blockAtPosition == null;

    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (block != null ? block.hashCode() : 0);
        result = 31 * result + (blockAtPosition != null ? blockAtPosition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InteractResult(" +
                "position=" + position +
                ", block=" + block +
                ", blockAtPosition=" + blockAtPosition +
                ')';
    }
}
