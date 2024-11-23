package use_case.new_chat.optimus_prime;

/**
 * Input Boundary for chatting with Optimus Prime.
 */
public interface OptimusPrimeInputBoundary {

    /**
     * Executes the chat with optimus prime use case.
     * @param optimusPrimeInputData the input data
     */
    void execute(OptimusPrimeInputData optimusPrimeInputData);
}
