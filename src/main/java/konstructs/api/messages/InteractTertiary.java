package konstructs.api.messages;

import akka.actor.ActorRef;
import konstructs.api.Block;
import konstructs.api.Position;

public class InteractTertiary {
    private final ActorRef sender;
    private final String player;
    private final Position position;
    private final Block block;
    private final Block blockAtPosition;
    private final boolean worldPhase;

    public InteractTertiary(ActorRef sender, String player, Position position, Block block, Block blockAtPosition, boolean worldPhase) {
        this.sender = sender;
        this.player = player;
        this.position = position;
        this.block = block;
        this.blockAtPosition = blockAtPosition;
        this.worldPhase = worldPhase;
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

    public Block getBlockAtPosition() {
        return blockAtPosition;
    }

    public boolean isWorldPhase() {
        return worldPhase;
    }

    public InteractTertiary withBlock(Block block) {
        return new InteractTertiary(sender, player, position, block, blockAtPosition, worldPhase);
    }

    public InteractTertiary withBlockAtPosition(Block blockAtPosition) {
        return new InteractTertiary(sender, player, position, block, blockAtPosition, worldPhase);
    }

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
