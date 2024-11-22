package use_case.optimus_prime;

import data_access.gpt_api_calls.GPTApiCallBotResponseDataAccessObject;

/**
 * Output Data for the chat with optimus prime Use Case.
 */
public class OptimusPrimeOutputData {

    private final String username;
    private final GPTApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject;

    public OptimusPrimeOutputData(String username, GPTApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject) {
        this.username = username;
        this.GPTApiCallBotResponseDataAccessObject = GPTApiCallBotResponseDataAccessObject;
    }

    public String getUsername() {
        return username;
    }

    public GPTApiCallBotResponseDataAccessObject getChatService() {
        return GPTApiCallBotResponseDataAccessObject;
    }
}
