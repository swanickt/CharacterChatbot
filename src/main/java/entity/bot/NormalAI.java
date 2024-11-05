package entity.bot;

/**
 * The normal AI chatbot without a character.
 */
public class NormalAI implements Bot {
    private final String prompt = "You are a helpful assistant, "
            + "reply in short sentence,act as you are talking to a normal human, "
            + "and always remember your role, an assistant,"
            + "your reply should not exceed 20 words and only reply in plain word text";
    private final String name;

    public NormalAI() {
        this.name = "Normal Bot";
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
