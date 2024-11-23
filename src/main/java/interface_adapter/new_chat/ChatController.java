package interface_adapter.new_chat;

import data_access.gpt_api_calls.GptApiCallBotResponseDataAccessObject;

public class ChatController {
    private GptApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject;

    public ChatController(GptApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject) {
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
