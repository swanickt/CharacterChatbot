package entity.message;

/**
 * Factory for storing the bot responses.
 */
public class BotMessageFactory implements MessageFactory {

    @Override
    public BotMessage create(String contents, String botName) {
        return new BotMessage(contents, botName);
    }
}
