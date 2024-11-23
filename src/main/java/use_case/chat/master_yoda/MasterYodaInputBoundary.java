package use_case.chat.master_yoda;

/**
 * Input Boundary for chatting with master yoda.
 */
public interface MasterYodaInputBoundary {

    /**
     * Executes the chat with master yoda use case.
     * @param masterYodaInputData the input data
     */
    void execute(MasterYodaInputData masterYodaInputData);
}
