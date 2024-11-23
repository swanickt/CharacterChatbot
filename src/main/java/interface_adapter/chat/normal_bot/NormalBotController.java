package interface_adapter.chat.normal_bot;

import use_case.chat.normal_bot.NormalBotInputBoundary;
import use_case.chat.normal_bot.NormalBotInputData;

/**
 * The controller for the chatting with the normal bot Use case.
 */
public class NormalBotController {
    private final NormalBotInputBoundary normalBotInteractor;

    public NormalBotController(NormalBotInputBoundary normalBotInteractor) {
        this.normalBotInteractor = normalBotInteractor;
    }

    /**
     * Executes the chat with normal bot Use Case.
     * @param username the username of the user chatting.
     */
    public void execute(String username) {
        final NormalBotInputData normalBotInputData = new NormalBotInputData(username);

        normalBotInteractor.execute(normalBotInputData);
    }
}
