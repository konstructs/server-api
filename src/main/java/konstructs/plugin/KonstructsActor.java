package konstructs.plugin;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import konstructs.api.messages.BoxQueryResult;
import scala.concurrent.duration.Duration;

import akka.actor.ActorRef;
import akka.actor.UntypedActorWithStash;
import konstructs.api.*;
import konstructs.api.messages.*;

public abstract class KonstructsActor extends UntypedActorWithStash {

    ActorRef universe;

    public KonstructsActor(ActorRef universe) {
        this.universe = universe;
    }

    /**
     * Called by Akka when we receive a message
     */
    public void onReceive(Object message) {

        if (message instanceof ViewBlockResult) {
            ViewBlockResult blockPosition = (ViewBlockResult)message;
            onViewBlockResult(blockPosition);
            return;
        }

        if (message instanceof BlockUpdateEvent) {
            BlockUpdateEvent event = (BlockUpdateEvent)message;
            onBlockUpdateEvent(event);
            return;
        }

        if (message instanceof BoxQueryResult) {
            BoxQueryResult result = (BoxQueryResult)message;
            onBoxQueryResult(result);
            return;
        }

        if (message instanceof GlobalConfig) {
            GlobalConfig config = (GlobalConfig)message;
            onGlobalConfig(config);
            return;
        }

        unhandled(message);
    }

    /**
     * Return universe ActorRef.
     * @return ActorRef
     */
    public ActorRef getUniverse() {
        return universe;
    }

    /**
     * This function is called when we receive a ViewBlockResult message.
     * @param block The block viewed
     */
    public void onViewBlockResult(ViewBlockResult block) {
        unhandled(block);
    }

    /**
     * This function is called when we receive a BoxQueryResult
     * @param result The BoxQueryResult message received
     */
    public void onBoxQueryResult(BoxQueryResult result) {
        unhandled(result);
    }

    /**
     * Called when a block is updated/created
     * @param event The block event
     */
    public void onBlockUpdateEvent(BlockUpdateEvent event) {
        unhandled(event);
    }

    /**
     * This function is called when we receive a GlobalConfig message.
     * @param config The GlobalConfig received
     */
    public void onGlobalConfig(GlobalConfig config) {
        unhandled(config);
    }

    /**
     * Ask the server for a block
     * @param   p   The position
     */
    public void viewBlock(Position p) {
        universe.tell(new ViewBlock(p), getSelf());
    }

    /** Query for a box of blocks
     *  @param box The box to query for
     */
    public void boxQuery(Box box) {
        universe.tell(new BoxQuery(box), getSelf());
    }

    /**
     * Replace all blocks that matches a filter with blocks from a
     * position to BlockTypeId mapping
     * @param filter The filter to be matched for each block to be replaced
     * @param blocks The blocks to replace with
     */
    public void replaceBlocks(BlockFilter filter, Map<Position, BlockTypeId> blocks) {
        getUniverse().tell(new ReplaceBlocks(filter, blocks), getSelf());
    }

    /**
     * Replace any block type at a given position with a block of
     * VACUUM.  This can be seen as a way to "remove" blocks.
     * @param position The position of the block to be replaced
     */
    public void replaceWithVacuum(Position position) {
        replaceWithVacuum(BlockFilterFactory.VACUUM, position);
    }

    /**
     * Replace a block that matches the filter at a given position
     * with a block of VACUUM.  This can be seen as a way to
     * "remove" blocks.
     * @param filter The filter to be matched before replacing the block
     * @param position The position of the block to be replaced
     */
    public void replaceWithVacuum(BlockFilter filter, Position position) {
        getUniverse().tell(new ReplaceBlock(filter, position, Block.create(BlockTypeId.VACUUM)), getSelf());
    }

    /**
     * Replace a block if it is VACUUM. This can be seen as a way to
     * only add a block to the world if what is already there is
     * VACUUM.
     * @param position The position of the block to try to replace
     * @param block The block to be added in the other blocks place
     */
    public void replaceVacuumBlock(Position position, Block block) {
        getUniverse().tell(new ReplaceBlock(BlockFilterFactory.VACUUM, position, block), getSelf());
    }

    /**
     * Schedule a message to my self
     * @param   obj  The object to send
     * @param   msec Time to wait, in milliseconds
     */
    public void scheduleSelfOnce(Object obj, int msec) {
        scheduleOnce(obj, msec, getSelf());
    }

    /**
     * Schedule a message to another actor
     * @param   obj  The object to send
     * @param   msec Time to wait, in milliseconds
     * @param   to   The actor that will receive the message
     */
    public void scheduleOnce(Object obj, int msec, ActorRef to) {
        getContext().system().scheduler().scheduleOnce(
                Duration.create(msec, TimeUnit.MILLISECONDS),
                to, obj, getContext().system().dispatcher(), null);
    }

    public static <T> java.util.List<T> nullAsEmpty(java.util.List<T> list) {
        if(list == null) {
            return Collections.emptyList();
        } else {
            return list;
        }
    }
}
