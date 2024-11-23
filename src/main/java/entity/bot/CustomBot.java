package entity.bot;

/**
 * A custom bot in character chatbot.
 */
public class CustomBot implements Bot {
    private final String prompt;
    private final String name;

    public CustomBot(String name, String occupation) {
        this.prompt = "You are" + name + ", the" + occupation + ". Always remember your role, "
                + "and your reply should not exceed 25 words."
                + "only reply in plain word text, you have to behave like" + name
                + ", the" + occupation + " at all times!";
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPrompt() { return prompt; }
}
