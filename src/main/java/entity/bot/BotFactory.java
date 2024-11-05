package entity.bot;

/**
 * Factory for creating bots.
 */
public interface BotFactory {
    /**
    * Creates a new bot.
    * @return the new bot.
    */
    Bot create();
}
