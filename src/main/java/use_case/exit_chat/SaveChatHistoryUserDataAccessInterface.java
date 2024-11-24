package use_case.exit_chat;

/**
 * The interface for saving and getting chat history from the MongoDB.
 */
public interface SaveChatHistoryUserDataAccessInterface {
    /**
     * Sets up a new past chat for the user.
     * @param username the user's username.
     */
    void setUp(String username);

    /**
     * Saves a message in the current chat history.
     * @param sender the sender of the message. Typically, "user" or "assistant" (bot).
     * @param message the message being sent.
     */
    void saveHistory(String sender, String message);

    /**
     * Saves the original bot greeting to the current chat history.
     */
    void saveGreeting(String user, String greeting);
}
