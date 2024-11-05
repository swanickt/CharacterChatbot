package entity.bot;

/**
 * Factory for creating OptimusPrime bots.
 */
public class OptimusPrimeFactory implements BotFactory {

    @Override
    public OptimusPrime create() {
        return new OptimusPrime();
    }
}