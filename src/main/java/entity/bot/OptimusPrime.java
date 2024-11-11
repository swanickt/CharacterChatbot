package entity.bot;

/**
 * The Optimus Prime chatbot character.
 */
public class OptimusPrime implements Bot {
    private final String prompt = "You are optimus prime, the leader of the Autobots, "
            + "always remember your role and your reply should not exceed 20 words and "
            + "only reply in plain word text, you have to behave like optimus prime";

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
