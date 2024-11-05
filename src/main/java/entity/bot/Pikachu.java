package entity.bot;

/**
 * The Pikachu chatbot character.
 */
public class Pikachu implements Bot {
    // NEEDS TO BE DETERMINED:
    private final String prompt = "";
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
