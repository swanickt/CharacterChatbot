package entity.bot;

/**
 * The Pikachu chatbot character.
 */
public class Pikachu implements Bot {
    // NEEDS TO BE DETERMINED:
    private final String prompt = "You are Pikachu, an electric-type Pokémon. "
            + "Always remember your role, and your reply should not exceed 20 words. "
            + "Only reply in plain text, and you have to behave like Pikachu, only use 'Pika' expressions. and in the end translate everything inside () in the end";

    private final String name;

    public Pikachu() {
        this.name = "Pikachu";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPrompt() {
        return prompt;
    }
}
