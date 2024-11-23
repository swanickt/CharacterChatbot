package use_case.new_chat.pikachu;

/**
 * The output boundary for the chat with Pikachu Use Case.
 */
public interface PikachuOutputBoundary {

    /**
     * Prepares the page allowing the user to chat with pikachu.
     * @param pikachuOutputData the necessary data to begin the chat.
     */
    void beginChat(PikachuOutputData pikachuOutputData);

}
