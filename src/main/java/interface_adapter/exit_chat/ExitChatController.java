package interface_adapter.exit_chat;

import entity.chat.CommonUserChat;
import use_case.exit_chat.ExitChatInputBoundary;
import use_case.exit_chat.ExitChatInputData;
import use_case.exit_chat.ExitChatInteractor;

/**
 * The controller for the 'exit chat' Use case.
 */
public class ExitChatController {

    private ExitChatInputBoundary exitChatInteractor;

    public ExitChatController() {
    }

    /**
     * Executes the exit/end chat Use Case.
     */
    public void execute(String username, CommonUserChat chat) {
        exitChatInteractor = new ExitChatInteractor(chat);
        final ExitChatInputData inputData = new ExitChatInputData(chat, username);
        exitChatInteractor.execute(inputData);
    }
}
