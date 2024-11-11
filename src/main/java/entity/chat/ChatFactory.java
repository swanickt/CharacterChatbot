package entity.chat;

import entity.bot.Bot;
import entity.user.User;

/**
 * Factory for creating chats. At creation, both the lists of user inputs
 * and bot responses are empty.
 */
public interface ChatFactory {

    /**
     * Creates a new Chat.
     * @return the new chat.
     */
    Chat create();
}
