package entity.chat;

import java.util.List;

import entity.message.BotMessage;
import entity.message.CommonUserMessage;
import entity.message.Message;

/**
 * The representation of a chat in Character Chatbot.
 */
public interface Chat {

    /**
     * Returns the User's messages in the order they were sent.
     * @return the User's messages.
     */
    List<Message> getUserInputs();

    /**
     * Returns the Bot's responses in the order they were sent.
     * @return the Bot's messages.
     */
    List<Message> getBotResponses();

    /**
     * @param content the contents of the new message.
     * Adds a new user message to the list of user inputs
     */
    void addUserInput(String content);

    /**
     * @param content the contents of the new bot response.
     * Adds a new bot message to the list of bot responses.
     */
    void addBotResponse(String content);
}
