package interface_adapter.new_chat.custom_bot;

import interface_adapter.new_chat.normal_bot.NormalBotController;
import use_case.new_chat.custom_bot.CustomBotInputBoundary;
import use_case.new_chat.custom_bot.CustomBotInputData;

public class CustomBotController {

    private final CustomBotInputBoundary customBotInteractor;

    public CustomBotController(CustomBotInputBoundary customBotInteractor) {
        this.customBotInteractor = customBotInteractor;
    }

    /**
     * Executes the chat with custom bot Use Case.
     * @param username the username of the user chatting.
     * @param name the name of the custom bot.
     * @param occupation the occupation of the custom bot.
     */
    public void execute(String username, String name, String occupation) {
        final CustomBotInputData customBotInputData = new CustomBotInputData(username, name, occupation);

        customBotInteractor.execute(customBotInputData);
    }

}
