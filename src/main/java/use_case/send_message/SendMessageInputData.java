package use_case.send_message;

/**
 * The Input Data for the send chat Use Case.
 */
public class SendMessageInputData {
    private final String userInput;

    public SendMessageInputData(String userInput) {
        this.userInput = userInput;
    }

    String getUserInput() { return userInput; }
}
