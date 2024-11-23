package use_case.new_chat.optimus_prime;

import entity.bot.BotFactory;

/**
 * The Optimus Prime Interactor.
 */
public class OptimusPrimeInteractor implements OptimusPrimeInputBoundary {
    private final OptimusPrimeOutputBoundary optimusPrimePresenter;
    private final BotFactory optimusPrimeFactory;

    public OptimusPrimeInteractor(OptimusPrimeOutputBoundary optimusPrimePresenter,
                                  BotFactory optimusPrimeFactory) {
        this.optimusPrimePresenter = optimusPrimePresenter;
        this.optimusPrimeFactory = optimusPrimeFactory;
    }

    @Override
    public void execute(OptimusPrimeInputData optimusPrimeInputData) {
        // retrieves the current user's username from the input data
        final String username = optimusPrimeInputData.getUsername();
        // creates an optimus prime bot object and retrieves the relevant backend prompt to start the chat.
        final String prompt = optimusPrimeFactory.create().getPrompt();
        // create an optimus prime output data object with the above information
        final OptimusPrimeOutputData optimusPrimeOutputData = new OptimusPrimeOutputData(username, prompt);

        optimusPrimePresenter.beginChat(optimusPrimeOutputData);
    }
}
