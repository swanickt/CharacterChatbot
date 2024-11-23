package use_case.new_chat.master_yoda;

/**
 * The output boundary for the chat with master yoda Use Case.
 */
public interface MasterYodaOutputBoundary {

    /**
     * Prepares the page allowing the user to chat with master yoda.
     * @param masterYodaOutputData the necessary data to begin the chat.
     */
    void beginChat(MasterYodaOutputData masterYodaOutputData);
}