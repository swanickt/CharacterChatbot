package entity.user;

import java.util.List;

import entity.chat.Chat;
/**
 * The representation of a user in Character Chatbot.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the user's chat history.
     * @return the user's chat history.
     */
    List<Chat> getChatHistory();
}
