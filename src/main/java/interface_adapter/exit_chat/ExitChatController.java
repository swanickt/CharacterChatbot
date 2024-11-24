package interface_adapter.exit_chat;

import use_case.exit_chat.ExitChatInputBoundary;
import use_case.exit_chat.ExitChatInputData;

/**
 * The controller for the 'exit chat' Use case.
 */
public class ExitChatController {

    private final ExitChatInputBoundary exitChatInteractor;

    public ExitChatController(ExitChatInputBoundary exitChatInteractor) {
        this.exitChatInteractor = exitChatInteractor;
    }

    /**
     * Executes the exit/end chat Use Case.
     */
    public void execute(String username) {
        final ExitChatInputData exitChatInputData = new ExitChatInputData(username);

        exitChatInteractor.execute(exitChatInputData);
    }

    public void newChat(String username) {
        exitChatInteractor.newChat(username);
    }

    public void saveGreeting(String user, String initialResponse) {
        exitChatInteractor.saveGreeting(user, initialResponse);
    }

    public void saveHistory(String sender, String message) {
        exitChatInteractor.saveHistory(sender, message);
    }

    public void addBotResponse(String botResponse) {
        exitChatInteractor.addBotResponse(botResponse);
    }

    public void addUserInput(String userInput) {
        exitChatInteractor.addUserInput(userInput);
    }

    public void setUp(String username) {
        exitChatInteractor.newChat(username);
    }
}
