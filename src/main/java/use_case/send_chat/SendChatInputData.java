package use_case.send_chat;

/**
 * The Input Data for the send chat Use Case.
 */
public class SendChatInputData {
    private final String userInput;

    public SendChatInputData(String userInput) {
        this.userInput = userInput;
    }

    String getUserInput() { return userInput; }
}
