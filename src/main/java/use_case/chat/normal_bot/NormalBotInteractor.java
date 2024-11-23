package use_case.chat.normal_bot;

import entity.bot.BotFactory;

/**
 * The begin chat with Normal Bot Interactor.
 */
public class NormalBotInteractor implements NormalBotInputBoundary {
    private final NormalBotOutputBoundary normalBotPresenter;
    private final BotFactory normalBotFactory;

    public NormalBotInteractor(NormalBotOutputBoundary normalBotPresenter,
                               BotFactory normalBotFactory) {
        this.normalBotPresenter = normalBotPresenter;
        this.normalBotFactory = normalBotFactory;
    }

    @Override
    public void execute(NormalBotInputData normalBotInputData) {
        // retrieves the current user's username from the input data
        final String username = normalBotInputData.getUsername();
        // creates a normal bot object and retrieves the relevant backend prompt to start the chat.
        final String prompt = normalBotFactory.create().getPrompt();
        // create a pikachu output data object with the above information
        final NormalBotOutputData normalBotOutputData = new NormalBotOutputData(username, prompt);

        normalBotPresenter.beginChat(normalBotOutputData);
    }

}