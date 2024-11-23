package use_case.new_chat.normal_bot;

/**
 * The output boundary for the chat with normal bot Use Case.
 */
public interface NormalBotOutputBoundary {

    /**
     * Prepares the page allowing the user to chat with the normal bot.
     * @param normalBotOutputData the necessary data to begin the chat.
     */
    void beginChat(NormalBotOutputData normalBotOutputData);
}
