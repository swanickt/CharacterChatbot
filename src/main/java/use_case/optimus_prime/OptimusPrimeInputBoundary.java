package use_case.optimus_prime;

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
