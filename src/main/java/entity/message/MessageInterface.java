package entity.message;

/**
 * The representation of a message in a chat, either from a user or a character/bot.
 */
public interface MessageInterface {

    /**
     * Returns the contents of the message.
     * @return the contents of the message.
     */
    String getContent();

    /**
     * Returns the name of the message's sender.
     * @return the name of the message's sender.
     */
    String getRole();
}
