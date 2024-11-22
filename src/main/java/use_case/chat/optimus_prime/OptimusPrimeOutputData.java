package use_case.chat.optimus_prime;

import data_access.gpt_api_calls.GptApiCallBotResponseDataAccessObject;

/**
 * Output Data for the chat with optimus prime Use Case.
 */
public class OptimusPrimeOutputData {

    private final String username;
    private final GptApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject;

    public OptimusPrimeOutputData(String username, GptApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject) {
        this.username = username;
        this.GPTApiCallBotResponseDataAccessObject = GPTApiCallBotResponseDataAccessObject;
    }

    public String getUsername() {
        return username;
    }

    public GptApiCallBotResponseDataAccessObject getChatService() {
        return GPTApiCallBotResponseDataAccessObject;
    }
}
