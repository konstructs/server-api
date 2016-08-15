package konstructs.api.messages;

import konstructs.api.EventTypeId;

/**
 * SendEvent let plugins send events to other plugins registered as listeners in the server configuration.
 * Events are sent to a {@link EventTypeId} and all plugins registered to this ID will receive the event
 * message.
 */
public class SendEvent {
    private final EventTypeId id;
    private final Object message;

    /**
     * Creates an immutable instance of the SendEvent message
     * @param id The event type ID to which the message is to be sent
     * @param message The message to be sent to all registered listeners
     */
    public SendEvent(EventTypeId id, Object message) {
        this.id = id;
        this.message = message;
    }

    /**
     * Returns the event type ID to which the message will be sent
     * @return The Event type ID
     */
    public EventTypeId getId() {
        return id;
    }

    /**
     * The message that will be sent to the ID
     * @return The message
     */
    public Object getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SendEvent sendEvent = (SendEvent) o;

        if (!id.equals(sendEvent.id)) return false;
        return message.equals(sendEvent.message);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SendEvent(" +
                "id='" + id + '\'' +
                ", message=" + message +
                ')';
    }
}
