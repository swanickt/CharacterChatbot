package use_case.chat.optimus_prime;

import entity.bot.OptimusPrimeFactory;
import data_access.gpt_api_calls.GPTApiCallBotResponseDataAccessObject;

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
        final GPTApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject = new GPTApiCallBotResponseDataAccessObject(setting);
        final OptimusPrimeOutputData optimusPrimeOutputData = new OptimusPrimeOutputData(username, GPTApiCallBotResponseDataAccessObject);
        optimusPrimePresenter.beginChat(optimusPrimeOutputData);
    }
}
