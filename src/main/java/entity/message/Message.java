package entity.message;

/**
 * The representation of a message in a chat, either from a user or a character/bot.
 */
public interface Message {

    /**
     * Returns the contents of the message.
     * @return the contents of the message.
     */
    String getContents();

    /**
     * Returns the name of the message's sender.
     * @return the name of the message's sender.
     */
    String getSender();
}
