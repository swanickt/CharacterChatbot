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
        // Get the user input from the input data and send it to the
        // gpt api to add it to the current conversation history.
        final String userInput = sendMessageInputData.getUserInput();
        apiAccess.addMessageToHistory("user", userInput);

        try {
            // retrieve the bot's response to the above user input from the api.
            final String response = apiAccess.getChatbotResponse().replace("\n", "");
            // add the bot's response to the current conversation history.
            apiAccess.addMessageToHistory("assistant", response);

            final boolean responseError = false;
            final String botResponse = response;

            // Pass the response to the presenter, through the output data.
            final SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(botResponse, responseError);
            sendChatPresenter.returnBotResponse(sendMessageOutputData);

        } catch (Exception ex) {
            // If we cannot retrieve the bot's response, then "simulate" a response error message.
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
