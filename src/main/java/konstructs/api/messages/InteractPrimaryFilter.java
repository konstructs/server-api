package konstructs.api.messages;

import akka.actor.ActorRef;

/**
 * This class represents a {@link Filter} for the user's {@link InteractPrimary primary} (left mouse button)
 * interaction event. The primary interaction events default behaviour is for the server to let the
 * block held by the user damage the block at the position in the world.
 * @see InteractPrimary
 */
public class InteractPrimaryFilter extends Filter<InteractPrimary>{

    /**
     * Creates an immutable instance of the Filter.
     * <b>This is used by the server internally and should not be done by a plugin.</b>
     * @param chain The chain of plugins, a.k.a. event queue
     * @param message The contained message
     */
    public InteractPrimaryFilter(ActorRef[] chain, InteractPrimary message) {
        super(chain, message);
    }

    @Override
    protected Filter<InteractPrimary> nextFilter(ActorRef[] chain) {
        return new InteractPrimaryFilter(chain, getMessage());
    }

    @Override
    protected Filter<InteractPrimary> nextFilter(ActorRef[] chain, InteractPrimary message) {
        return new InteractPrimaryFilter(chain, message);
    }

    @Override
    public void drop(ActorRef sender) {
        getMessage().getSender().tell(new InteractResult(getMessage().getPosition(), getMessage().getBlock(), null), sender);
    }

    @Override
    public void dropWith(ActorRef sender, InteractPrimary newMessage) {
        newMessage.getSender().tell(new InteractResult(newMessage.getPosition(), newMessage.getBlock(), null), sender);
    }
}
