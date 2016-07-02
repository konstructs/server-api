package konstructs.api.messages;

import akka.actor.ActorRef;

/**
 * Created by petter on 2016-07-02.
 */
public class InteractTertiaryFilter extends Filter<InteractTertiary>{

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
}
