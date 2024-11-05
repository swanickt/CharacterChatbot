package entity.bot;

/**
 * Factory for creating Pikachu bots.
 */
public class PikachuFactory implements BotFactory {

    @Override
    public Pikachu create() {
        return new Pikachu();
    }
}
