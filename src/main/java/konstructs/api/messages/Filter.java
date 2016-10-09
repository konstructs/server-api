package konstructs.api.messages;

import java.io.Serializable;
import java.util.Arrays;

import akka.actor.ActorRef;

/**
 * Filter is a concept used in Konstructs to let a plugin act on different events produced.
 * It differs from pure events in the way that a plugin can decide to change or discard events
 * that are received via a filter. This is done by using the API provided by this abstract base class.
 * The different classes that extends this base class acts as containers for the event that the
 * plugin receives. This container is then sent as a message to the plugin. Currently the following
 * filter events are available:
 * <ul>
 *     <li>{@link InteractPrimaryFilter} ({@link InteractPrimary} event)</li>
 *     <li>{@link InteractSecondaryFilter} ({@link InteractSecondary} event)</li>
 *     <li>{@link InteractTertiaryFilter} ({@link InteractTertiary} event)</li>
 *     <li>{@link SayFilter} ({@link Say} event)</li>
 * </ul>
 *
 * <p>
 *     When a plugin receives an instance of a filter event it must do one of the following things:
 * </p>
 * <ul>
 *     <li>
 *         It may forward the filter event to the next plugin in the filter queue or ultimately
 *         back to the server for final processing. This is done using the {@link #next(ActorRef)} method.
 *         The plugin may also decide on altering the event before doing so by utilizing the
 *         {@link #nextWith(ActorRef, Object)} method.
 *     </li>
 *     <li>
 *         It may decide to forward the filter event directly to the server for final processing, skipping
 *         any subsequent plugin in the filter queue. This is done using the {@link #skip(ActorRef)} method.
 *         The plugin may also decide on altering the event before doing so by utilizing the
 *         {@link #skipWith(ActorRef, Object)} method.
 *     </li>
 *     <li>
 *         It may drop the filter event completely avoiding processing of the event by the server. This means
 *         that the event will not be processed by the server or any other subsequent plugin on the event queue.
 *         This is done using the {@link #drop(ActorRef)} method. The plugin may also decide on altering the event
 *         before doing so by utilizing the {@link #dropWith(ActorRef, Object)} method. Since the event is not
 *         forwarded, this means instead that the event response is generated from the altered event.
 *     </li>
 * </ul>
 * @param <T> The contained message type of the filtered event
 */
public abstract class Filter<T> implements Serializable {
    protected static ActorRef[] EMPTY_FILTER = {};

    private final ActorRef[] chain;
    private final T message;

    protected Filter(ActorRef[] chain, T message) {
        this.chain = chain;
        this.message = message;
    }

    protected abstract Filter<T> nextFilter(ActorRef[] chain);
    protected abstract Filter<T> nextFilter(ActorRef[] chain, T message);

    /**
     * Drops the event from the event queue. This avoids any further processing of the eventy.
     * @param sender The sender of any event response generated
     */
    public abstract void drop(ActorRef sender);

    /**
     * Drops the event from the event queue. This avoids any further processing of the eventy.
     * @param sender The sender of any event response generated
     * @param newMessage A altered event message used to generate the event response
     */
    public abstract void dropWith(ActorRef sender, T newMessage);

    /**
     * Returns the complete plugin chain (event queue)
     * @return The plugin chain
     */
    public ActorRef[] getChain() {
        return chain;
    }

    /**
     * Returns the contained message for the event
     * @return The event message
     */
    public T getMessage() {
        return message;
    }

    /**
     * Forwards the unchanged message to the next plugin in the event queue
     * @param sender The sender of the forwarded message
     */
    public void next(ActorRef sender) {
        ActorRef[] tail = Arrays.copyOfRange(chain, 1, chain.length);
        chain[0].tell(nextFilter(tail), sender);
    }

    /**
     * Forwards an altered message to the next plugin in the event queue
     * @param sender The sender of the forwarded message
     * @param newMessage The altered message
     */
    public void nextWith(ActorRef sender, T newMessage) {
        ActorRef[] tail = Arrays.copyOfRange(chain, 1, chain.length);
        chain[0].tell(nextFilter(tail, newMessage), sender);
    }

    /**
     * Skip all other plugins in chain, but let the server process the
     * unchanged message using its default procedure.
     * @param sender The sender of the message sent back to the server
     */
    public void skip(ActorRef sender) {
        chain[chain.length - 1].tell(nextFilter(EMPTY_FILTER), sender);
    }

    /**
     * Skip all other plugins in chain, but let the server process an
     * altered message.
     * @param sender The sender of the message sent back to the server
     * @param newMessage The altered message
     */
    public void skipWith(ActorRef sender, T newMessage) {
        chain[chain.length - 1].tell(nextFilter(EMPTY_FILTER, newMessage), sender);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter<?> filter = (Filter<?>) o;

        if (!Arrays.equals(chain, filter.chain)) return false;
        return message.equals(filter.message);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(chain);
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        /* getClass().getId() was added to print the name of the implementing class */
        return getClass().getName() + "(" +
                "chain=" + Arrays.toString(chain) +
                ", message=" + message +
                ')';
    }
}
