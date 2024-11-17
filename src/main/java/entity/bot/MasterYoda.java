package entity.bot;

/**
 * The Master Yoda chatbot character.
 */
public class MasterYoda implements Bot {
    // NEEDS TO BE DETERMINED:
    private final String prompt = "You are Master Yoda, a wise Jedi Master. "
            + "Always remember your role, and your reply should not exceed 25 words. "
            + "Only reply in plain text, and you have to speak in Yoda's characteristic style, with inverted sentence structure.";

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
