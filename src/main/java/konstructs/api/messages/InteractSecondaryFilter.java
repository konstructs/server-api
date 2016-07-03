package konstructs.api.messages;

import akka.actor.ActorRef;


/**
 * This class represents a {@link Filter} for the user's {@link InteractSecondary secondary} (right mouse button)
 * interaction event. The secondary interaction events default behaviour is for the server to place the block
 * held by the user into the world at the position.
 * @see InteractSecondary
 */
public class InteractSecondaryFilter extends Filter<InteractSecondary>{

    /**
     * Creates an immutable instance of the Filter.
     * <b>This is used by the server internally and should not be done by a plugin.</b>
     * @param chain The chain of plugins, a.k.a. event queue
     * @param message The contained message
     */
    public InteractSecondaryFilter(ActorRef[] chain, InteractSecondary message) {
        super(chain, message);
    }

    @Override
    protected Filter<InteractSecondary> nextFilter(ActorRef[] chain) {
        return new InteractSecondaryFilter(chain, getMessage());
    }

    @Override
    protected Filter<InteractSecondary> nextFilter(ActorRef[] chain, InteractSecondary message) {
        return new InteractSecondaryFilter(chain, message);
    }

    @Override
    public void drop(ActorRef sender) {
        getMessage().getSender().tell(new InteractResult(getMessage().getPosition(), getMessage().getBlock(), null), sender);
    }

    @Override
    public void dropWith(ActorRef sender, InteractSecondary newMessage) {
        newMessage.getSender().tell(new InteractResult(newMessage.getPosition(), newMessage.getBlock(), null), sender);
    }
}
