package konstructs.api.messages;

import akka.actor.ActorRef;

/**
 * This class represents a {@link Filter} for the user's {@link InteractTertiary tertiary} (middle mouse button
 * or button <code>e</code>) interaction event. The tertiary interaction event's default behaviour is to
 * update the world with the block returned by {@link InteractTertiary#getBlockAtPosition()}. Any updates
 * made to this field will therefore be reflected into the world (health, id, block type...). Therefore and since
 * an unchanged message means that no updates are made to the world the tertiary interaction event should be
 * used by plugins that want the user to interact either with a held block or a block in the world
 * (or both simultaneously).
 * <p>
 *     Please note that the {@link #drop(ActorRef)} and
 *     {@link #dropWith(ActorRef, InteractTertiary)} will <b>not</b> update the world with the block returned by
 *     {@link InteractTertiary#getBlockAtPosition()}. Also note that if {@link InteractTertiary#getPosition()} is null,
 *     it can not be updated and any change to {@link InteractTertiary#getBlockAtPosition()} will be ignore
 *     (since the user is not pointing at anything).
 * </p>
 * @see InteractTertiary
 */
public class InteractTertiaryFilter extends Filter<InteractTertiary>{

    /**
     * This class is used as a marker when the filter was skipped in the first phase
     */
    public static class Skipped {
        private final Filter<InteractTertiary> filter;

        public Skipped(Filter<InteractTertiary> filter) {
            this.filter = filter;
        }

        public Filter<InteractTertiary> getFilter() {
            return filter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Skipped skipped = (Skipped) o;

            return filter.equals(skipped.filter);

        }

        @Override
        public int hashCode() {
            return filter.hashCode();
        }
    }

    /**
     * Creates an immutable instance of the Filter.
     * <b>This is used by the server internally and should not be done by a plugin.</b>
     * @param chain The chain of plugins, a.k.a. event queue
     * @param message The contained message
     */
    public InteractTertiaryFilter(ActorRef[] chain, InteractTertiary message) {
        super(chain, message);
    }

    @Override
    protected Filter<InteractTertiary> nextFilter(ActorRef[] chain) {
        return new InteractTertiaryFilter(chain, getMessage());
    }

    @Override
    protected Filter<InteractTertiary> nextFilter(ActorRef[] chain, InteractTertiary message) {
        return new InteractTertiaryFilter(chain, message);
    }

    @Override
    public void drop(ActorRef sender) {
        getMessage()
                .getSender()
                .tell(new InteractResult(getMessage().getPosition(), getMessage().getBlock(),
                        getMessage().getBlockAtPosition()), sender);
    }

    @Override
    public void dropWith(ActorRef sender, InteractTertiary newMessage) {
        newMessage
                .getSender()
                .tell(new InteractResult(newMessage.getPosition(), newMessage.getBlock(),
                        newMessage.getBlockAtPosition()), sender);
    }

    @Override
    public void skip(ActorRef sender) {
        if(!getMessage().isWorldPhase()) {
            // This handles the skip as normally
            super.skip(sender);
        } else {
            // This indicates to the server that the message was skipped in the first
            // phase and should not continue with the next phase
            ActorRef[] chain = getChain();
            chain[chain.length - 1].tell(new Skipped(nextFilter(EMPTY_FILTER)), sender);
        }
    }

    @Override
    public void skipWith(ActorRef sender, InteractTertiary newMessage) {
        if(!getMessage().isWorldPhase()) {
            // This handles the skip as normally
            super.skipWith(sender, newMessage);
        } else {
            // This indicates to the server that the message was skipped in the first
            // phase and should not continue with the next phase
            ActorRef[] chain = getChain();
            chain[chain.length - 1].tell(new Skipped(nextFilter(EMPTY_FILTER, newMessage)), sender);
        }
    }

}
