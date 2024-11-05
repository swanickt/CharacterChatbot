package entity.bot;

/**
 * The Master Yoda chatbot character.
 */
public class MasterYoda implements Bot {
    // NEEDS TO BE DETERMINED:
    private final String prompt = "";
    private final String name;

    public MasterYoda() {
        this.name = "Master Yoda";
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
