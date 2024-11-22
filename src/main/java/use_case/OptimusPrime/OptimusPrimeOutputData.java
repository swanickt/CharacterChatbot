package use_case.OptimusPrime;

import data_access.gpt_api_calls.ChatService;

/**
 * Output Data for the chat with optimus prime Use Case.
 */
public class OptimusPrimeOutputData {

    private final String username;
    private final ChatService chatService;

    public OptimusPrimeOutputData(String username, ChatService chatService) {
        this.username = username;
        this.chatService = chatService;
    }

    public String getUsername() {
        return username;
    }

    public ChatService getChatService() {
        return chatService;
    }
}
