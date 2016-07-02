package konstructs.api.messages;

import akka.actor.ActorRef;

/**
 * Created by petter on 2016-07-02.
 */
public class InteractPrimaryFilter extends Filter<InteractPrimary>{

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
