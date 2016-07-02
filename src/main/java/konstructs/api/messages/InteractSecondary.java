package konstructs.api.messages;

import akka.actor.ActorRef;
import konstructs.api.Block;
import konstructs.api.Position;

public class InteractSecondary {
    private final ActorRef sender;
    private final String player;
    private final Position position;
    private final Block block;

    public InteractSecondary(ActorRef sender, String player, Position position, Block block) {
        this.sender = sender;
        this.player = player;
        this.position = position;
        this.block = block;
    }

    public ActorRef getSender() {
        return sender;
    }

    public String getPlayer() {
        return player;
    }

    public Position getPosition() {
        return position;
    }

    public Block getBlock() {
        return block;
    }

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
