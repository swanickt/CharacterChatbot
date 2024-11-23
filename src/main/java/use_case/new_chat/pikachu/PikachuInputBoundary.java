package use_case.new_chat.pikachu;

/**
 * Input Boundary for chatting with Pikachu.
 */
public interface PikachuInputBoundary {

    /**
     * Executes the chat with Pikachu use case.
     * @param pikachuInputData the input data
     */
    void execute(PikachuInputData pikachuInputData);
}
