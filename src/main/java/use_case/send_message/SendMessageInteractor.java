package use_case.send_message;

/**
 * The send chat use case interactor.
 */
public class SendMessageInteractor implements SendMessageInputBoundary {
    private final SendMessageOutputBoundary sendChatPresenter;
    private final ChatApiAccessInterface apiAccess;

    public SendMessageInteractor(SendMessageOutputBoundary sendChatPresenter,
                                 ChatApiAccessInterface apiAccess) {
        this.sendChatPresenter = sendChatPresenter;
        this.apiAccess = apiAccess;
    }

    @Override
    public void execute(SendMessageInputData sendMessageInputData) {
        final String userInput = sendMessageInputData.getUserInput();
        apiAccess.addMessageToHistory("user", userInput);

        try {
            final String response = apiAccess.getChatbotResponse().replace("\n", "");
            apiAccess.addMessageToHistory("assistant", response);

            final boolean responseError = false;
            final String botResponse = response;

            final SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(botResponse, responseError);
            sendChatPresenter.returnBotResponse(sendMessageOutputData);

        } catch (Exception ex) {
            final boolean responseError = true;
            final String botResponse = "Error: Unable to get response from GPT.";
            final SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(botResponse, responseError);
            sendChatPresenter.returnBotResponse(sendMessageOutputData);
        }
    }

    public void setSystemSetting(String prompt) {
        apiAccess.setSystemSetting(prompt);
    }
}
