package konstructs.api.messages;

import akka.actor.ActorRef;

/**
 * Created by petter on 2016-07-02.
 */
public class InteractSecondaryFilter extends Filter<InteractSecondary>{

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
