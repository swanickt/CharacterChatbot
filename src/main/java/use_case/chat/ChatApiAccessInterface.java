package use_case.chat;

/**
 * The interface for getting api call responses from the gpt API.
 */
public interface ChatApiAccessInterface {
    /**
     * Records whether it's from a user or both ("role) and its contents.
     */
    void addMessageToHistory(String role, String content);

    /**
     * Get the bot response from the gpt api, throws an exception if the response cannot
     * be retrieved.
     */
    String getChatbotResponse() throws Exception;

}
