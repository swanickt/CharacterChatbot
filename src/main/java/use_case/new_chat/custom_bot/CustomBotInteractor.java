package use_case.new_chat.custom_bot;

import entity.bot.BotFactory;
import entity.bot.CustomBotFactory;
import use_case.new_chat.master_yoda.MasterYodaInputData;

public class CustomBotInteractor implements CustomBotInputBoundary {
    private final CustomBotOutputBoundary customBotPresenter;
    private final CustomBotFactory customBotFactory;

    public CustomBotInteractor(CustomBotOutputBoundary customBotPresenter, CustomBotFactory customBotFactory) {
        this.customBotFactory = customBotFactory;
        this.customBotPresenter = customBotPresenter;
    }

    @Override
    public void execute(CustomBotInputData customBotInputData) {
        // retrieves the current user's username, custom bot name, and occupation from the input data
        final String username = customBotInputData.getUsername();
        final String botName = customBotInputData.getBotName();
        final String occupation = customBotInputData.getBotOccupation();

        // creates a custom bot object and the relevant backend prompt to start the chat
        // based on the input data.
        final String prompt = customBotFactory.create(botName, occupation).getPrompt();

        // Tell the presenter to signal the beginning of a new custom bot chat with
        // this information.
        final CustomBotOutputData customBotOutputData = new CustomBotOutputData(username, prompt);
        customBotPresenter.beginChat(customBotOutputData);
    }
}
