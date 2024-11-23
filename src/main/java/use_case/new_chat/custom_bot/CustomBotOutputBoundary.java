package use_case.new_chat.custom_bot;

import use_case.new_chat.master_yoda.MasterYodaOutputData;

public interface CustomBotOutputBoundary {

    /**
     * Prepares the page allowing the user to chat with the custom bot.
     * @param customBotOutputData the necessary data to begin the chat.
     */
    void beginChat(CustomBotOutputData customBotOutputData);
}
