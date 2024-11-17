package use_case.OptimusPrime;

import entity.bot.OptimusPrimeFactory;
import use_case.ChatService.ChatService;

/**
 * The Optimus Prime Interactor.
 */
public class OptimusPrimeInteractor implements OptimusPrimeInputBoundary {
    private final OptimusPrimeOutputBoundary optimusPrimePresenter;
    private final String setting;

    public OptimusPrimeInteractor(OptimusPrimeOutputBoundary optimusPrimePresenter) {
        this.optimusPrimePresenter = optimusPrimePresenter;
        this.setting = new OptimusPrimeFactory().create().getPrompt();
    }

    @Override
    public void execute(OptimusPrimeInputData optimusPrimeInputData) {
        final String username = optimusPrimeInputData.getUsername();
        final ChatService chatService = new ChatService(setting);
        final OptimusPrimeOutputData optimusPrimeOutputData = new OptimusPrimeOutputData(username, chatService);
        optimusPrimePresenter.beginChat(optimusPrimeOutputData);
    }
}
