package interface_adapter.chat;

import data_access.gpt_api_calls.GPTApiCallBotResponseDataAccessObject;

public class ChatController {
    private GPTApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject;

    public ChatController(GPTApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject) {
        this.GPTApiCallBotResponseDataAccessObject = GPTApiCallBotResponseDataAccessObject;
    }

    public void addUserMessage(String message) {
        GPTApiCallBotResponseDataAccessObject.addMessageToHistory("user", message);
    }

    public String getAssistantResponse() throws Exception {
        return GPTApiCallBotResponseDataAccessObject.getChatbotResponse();
    }

    public void addAssistantMessage(String message) {
        GPTApiCallBotResponseDataAccessObject.addMessageToHistory("assistant", message);
    }
}
