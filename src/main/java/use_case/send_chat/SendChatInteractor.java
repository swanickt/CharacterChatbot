package use_case.send_chat;

/**
 * The send chat use case interactor.
 */
public class SendChatInteractor implements SendChatInputBoundary {
    private final SendChatOutputBoundary sendChatPresenter;
    private final ChatApiAccessInterface apiAccess;

    public SendChatInteractor(SendChatOutputBoundary sendChatPresenter,
                              ChatApiAccessInterface apiAccess) {
        this.sendChatPresenter = sendChatPresenter;
        this.apiAccess = apiAccess;
    }

    @Override
    public void execute(SendChatInputData sendChatInputData) {
        final String userInput = sendChatInputData.getUserInput();
        apiAccess.addMessageToHistory("user", userInput);

        try {
            final String response = apiAccess.getChatbotResponse().replace("\n", "");
            apiAccess.addMessageToHistory("assistant", response);

            final boolean responseError = false;
            final String botResponse = response;

            final SendChatOutputData sendChatOutputData = new SendChatOutputData(botResponse, responseError);
            sendChatPresenter.returnBotResponse(sendChatOutputData);

        } catch (Exception ex) {
            final boolean responseError = true;
            final String botResponse = "Error: Unable to get response from GPT.";
            final SendChatOutputData sendChatOutputData = new SendChatOutputData(botResponse, responseError);
            sendChatPresenter.returnBotResponse(sendChatOutputData);
        }
    }

    public void setSystemSetting(String prompt) {
        apiAccess.setSystemSetting(prompt);
    }
}
