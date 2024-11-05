package entity.bot;

/**
 * The representation of a bot in Character Chatbot.
 */
public interface Bot {
    /**
     * Returns the name of the bot.
     * @return the name of the bot.
     */
    String getName();

    /**
     * Returns the bot's backend pre-chat prompt.
     * @return the bot's backend pre-chat prompt.
     */
    String getPrompt();
}
