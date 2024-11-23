package use_case.chat.pikachu;

import entity.bot.BotFactory;

/**
 * The begin chat with Pikachu Interactor.
 */
public class PikachuInteractor implements PikachuInputBoundary {
    private final PikachuOutputBoundary pikachuPresenter;
    private final BotFactory pikachuFactory;

    public PikachuInteractor(PikachuOutputBoundary pikachuPresenter,
                             BotFactory pikachuFactory) {
        this.pikachuPresenter = pikachuPresenter;
        this.pikachuFactory = pikachuFactory;
    }

    @Override
    public void execute(PikachuInputData pikachuInputData) {
        // retrieves the current user's username from the input data
        final String username = pikachuInputData.getUsername();
        // creates a pikachu bot object and retrieves the relevant backend prompt to start the chat.
        final String prompt = pikachuFactory.create().getPrompt();
        // create a pikachu output data object with the above information
        final PikachuOutputData pikachuOutputData = new PikachuOutputData(username, prompt);

        pikachuPresenter.beginChat(pikachuOutputData);
    }
}
