package use_case.new_chat.optimus_prime;

/**
 * Output Data for the chat with optimus prime Use Case.
 */
public class OptimusPrimeOutputData {
    private final String username;
    private final String prompt;

    public OptimusPrimeOutputData(String username, String prompt) {
        this.username = username;
        this.prompt = prompt;
    }

    public String getUsername() {
        return username;
    }

    public String getPrompt() {
        return prompt;
    }
}
