package use_case.OptimusPrime;

/**
 * The output boundary for the chat with optimus prime Use Case.
 */
public interface OptimusPrimeOutputBoundary {

    /**
     * Prepares the page allowing the user to chat with optimus prime.
     * @param optimusPrimeOutputData the necessary data to begin the chat.
     */
    void beginChat(OptimusPrimeOutputData optimusPrimeOutputData);

}
