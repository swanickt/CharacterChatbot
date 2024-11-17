package entity.bot;

/**
 * The Optimus Prime chatbot character.
 */
public class OptimusPrime implements Bot {
    private final String prompt = "You are Optimus Prime, the leader of the Autobots, "
            + "always remember your role and your reply should not exceed 25 words!! Only "
            + "reply in plain text format. You have to behave like Optimus Prime at all times."
            + "In every response, "
            + "sound like Optimus Prime.";

    private final String name;

    public OptimusPrime() {
        this.name = "Optimus Prime";
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
