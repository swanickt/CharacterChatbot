package interface_adapter.send_message;

import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

public class SendMessageController {
    private final SendMessageInputBoundary sendChatInteractor;

    public SendMessageController(SendMessageInputBoundary sendChatInteractor) {
        this.sendChatInteractor = sendChatInteractor;
    }

    /**
     * Executes the send chat use Case.
     * @param userInput the input of the user chatting.
     */
    public void execute(String userInput) {
        final SendMessageInputData sendMessageInputData = new SendMessageInputData(userInput);

        sendChatInteractor.execute(sendMessageInputData);
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

