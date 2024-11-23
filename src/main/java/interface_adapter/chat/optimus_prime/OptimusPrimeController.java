package interface_adapter.chat.optimus_prime;

import use_case.chat.optimus_prime.OptimusPrimeInputBoundary;
import use_case.chat.optimus_prime.OptimusPrimeInputData;

/**
 * The controller for the chatting with Optimus Prime Use case.
 */
public class OptimusPrimeController {

    private final OptimusPrimeInputBoundary optimusPrimeInteractor;

    public OptimusPrimeController(OptimusPrimeInputBoundary optimusPrimeInteractor) {
        this.optimusPrimeInteractor = optimusPrimeInteractor;
    }

    /**
     * Executes the chat with Optimus Prime Use Case.
     * @param username the username of the user chatting.
     */
    public void execute(String username) {
        final OptimusPrimeInputData optimusPrimeInputData = new OptimusPrimeInputData(
                username);

        optimusPrimeInteractor.execute(optimusPrimeInputData);
    }
}
