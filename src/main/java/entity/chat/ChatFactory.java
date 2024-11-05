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
     * @param user the user involved in the chat.
     * @param bot the bot involved in the chat.
     * @return the new chat.
     */
    Chat create(User user, Bot bot);
}
