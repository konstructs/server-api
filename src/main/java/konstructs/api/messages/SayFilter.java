package konstructs.api.messages;

import akka.actor.ActorRef;

/**
 * Created by petter on 2016-07-02.
 */
public class SayFilter extends Filter<Say>{

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
