package konstructs.api.messages;

import akka.actor.ActorRef;

/**
 * This class represents a {@link Filter} for the {@link Say chat messages} sent by the user.
 * The default behaviour is for the server to forward the chat message to all connected users.
 * @see Say
 */
public class SayFilter extends Filter<Say>{

    /**
     * Creates an immutable instance of the Filter.
     * <b>This is used by the server internally and should not be done by a plugin.</b>
     * @param chain The chain of plugins, a.k.a. event queue
     * @param message The contained message
     */
    public SayFilter(ActorRef[] chain, Say message) {
        super(chain, message);
    }

    @Override
    protected Filter<Say> nextFilter(ActorRef[] chain) {
        return new SayFilter(chain, getMessage());
    }

    @Override
    protected Filter<Say> nextFilter(ActorRef[] chain, Say message) {
        return new SayFilter(chain, message);
    }

    @Override
    public void drop(ActorRef sender) {
        /* This is a noop for chat messages */
    }

    @Override
    public void dropWith(ActorRef sender, Say newMessage) {
        /* This is a noop for chat messages */
    }
}
