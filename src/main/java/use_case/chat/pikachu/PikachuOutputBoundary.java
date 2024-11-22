package use_case.chat.pikachu;

import use_case.chat.optimus_prime.OptimusPrimeOutputData;

/**
 * The output boundary for the chat with Pikachu prime Use Case.
 */
public interface PikachuOutputBoundary {

    /**
     * Prepares the page allowing the user to chat with pikachu.
     * @param pikachuOutputData the necessary data to begin the chat.
     */
    void beginChat(PikachuOutputData pikachuOutputData);

}
