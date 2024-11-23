package use_case.send_message;

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

    /**
     * Sets the api responses system setting to setting.
     */
    void setSystemSetting(String setting);
}
