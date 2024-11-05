package entity.message;

/**
 * A chat message sent from a Bot.
 */
public class BotMessage implements Message {

    private final String contents;
    private final String botName;

    public BotMessage(String contents, String botName) {
        this.contents = contents;
        this.botName = botName;
    }

    @Override
    public String getContents() {
        return contents;
    }

    @Override
    public String getSender() {
        return botName;
    }
}
