package use_case.new_chat.custom_bot;

import use_case.new_chat.master_yoda.MasterYodaInputData;

/**
 * Input Boundary for chatting with a custom bot.
 */
public interface CustomBotInputBoundary {

    /**
     * Executes the chat with custom bot use case.
     * @param customBotInputData the input data
     */
    void execute(CustomBotInputData customBotInputData);
}
