package use_case.chat.normal_bot;

/**
 * Input Boundary for chatting with the normal bot.
 */
public interface NormalBotInputBoundary {

    /**
     * Executes the chat with the normal bot use case.
     * @param normalBotInputData the input data
     */
    void execute(NormalBotInputData normalBotInputData);
}
