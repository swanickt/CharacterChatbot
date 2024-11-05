package entity.message;

/**
 * Factory for creating messages.
 */
public interface MessageFactory {
    /**
     * Creates a new Message.
     * @param contents the contents of the new message.
     * @param sender the sender of the message.
     * @return the new message
     */
    Message create(String contents, String sender);
}
