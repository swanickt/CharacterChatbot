package interface_adapter.send_chat;

import data_access.gpt_api_calls.GptApiCallBotResponseDataAccessObject;
import use_case.send_chat.SendChatInputBoundary;
import use_case.send_chat.SendChatInputData;

public class SendChatController {
    private final SendChatInputBoundary sendChatInteractor;

    public SendChatController(SendChatInputBoundary sendChatInteractor) {
        this.sendChatInteractor = sendChatInteractor;
    }

    /**
     * Executes the send chat use Case.
     * @param userInput the input of the user chatting.
     */
    public void execute(String userInput) {
        final SendChatInputData sendChatInputData = new SendChatInputData(userInput);

        sendChatInteractor.execute(sendChatInputData);
    }

    public void setSystemSetting(String prompt) {
        sendChatInteractor.setSystemSetting(prompt);
    }
}

//
//    public void addUserMessage(String message) {
//        GPTApiCallBotResponseDataAccessObject.addMessageToHistory("user", message);
//    }
//
//    public String getAssistantResponse() throws Exception {
//        return GPTApiCallBotResponseDataAccessObject.getChatbotResponse();
//    }
//
//    public void addAssistantMessage(String message) {
//        GPTApiCallBotResponseDataAccessObject.addMessageToHistory("assistant", message);
//    }

