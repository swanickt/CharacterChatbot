package entity.bot;

/**
 * Factory for creating MasterYoda bots.
 */
public class MasterYodaFactory implements BotFactory {

    @Override
    public MasterYoda create() {
        return new MasterYoda();
    }
}
