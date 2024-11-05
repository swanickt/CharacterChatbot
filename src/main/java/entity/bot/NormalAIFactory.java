package entity.bot;

/**
 * Factory for creating NormalAI bots.
 */
public class NormalAIFactory implements BotFactory {

    @Override
    public NormalAI create() {
        return new NormalAI();
    }
}
