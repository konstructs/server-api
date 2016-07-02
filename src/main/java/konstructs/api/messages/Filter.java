package konstructs.api.messages;

import java.io.Serializable;
import java.util.Arrays;

import akka.actor.ActorRef;

public abstract class Filter<T> implements Serializable {
    private static ActorRef[] EMPTY_FILTER = {};

    private final ActorRef[] chain;
    private final T message;

    protected Filter(ActorRef[] chain, T message) {
        this.chain = chain;
        this.message = message;
    }

    protected abstract Filter<T> nextFilter(ActorRef[] chain);
    protected abstract Filter<T> nextFilter(ActorRef[] chain, T message);

    public abstract void drop(ActorRef sender);
    public abstract void dropWith(ActorRef sender, T newMessage);

    public ActorRef[] getChain() {
        return chain;
    }

    public T getMessage() {
        return message;
    }

    /**
     * Let next plugin in chain handle the unchanged message
     */
    public void next(ActorRef sender) {
        ActorRef[] tail = Arrays.copyOfRange(chain, 1, chain.length);
        chain[0].tell(nextFilter(tail), sender);
    }

    /**
     * Let next plugin in chain handle an updated message
     */
    public void nextWith(ActorRef sender, T newMessage) {
        ActorRef[] tail = Arrays.copyOfRange(chain, 1, chain.length);
        chain[0].tell(nextFilter(tail, newMessage), sender);
    }

    /**
     * Skip all other plugins in chain, but let the server process the
     * unchanged message, using its default procedure
     */
    public void skip(ActorRef sender) {
        chain[chain.length - 1].tell(nextFilter(EMPTY_FILTER), sender);
    }

    /**
     * Skip all other plugins in chain, but let the server process an
     * updated message
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
        /* getClass().getName() was added to print the name of the implementing class */
        return getClass().getName() + "(" +
                "chain=" + Arrays.toString(chain) +
                ", message=" + message +
                ')';
    }
}
