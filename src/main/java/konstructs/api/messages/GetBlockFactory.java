package konstructs.api.messages;

/**
 * This message is used to retrieve the {@link konstructs.api.BlockFactory} (block type database) from
 * the server.
 */
public class GetBlockFactory {
    /**
     * This singletone represents the message and should be sent to universe to get
     * the BlockFactory sent back as a message.
     */
    public final static GetBlockFactory MESSAGE = new GetBlockFactory();
    private GetBlockFactory() {
    }
}
