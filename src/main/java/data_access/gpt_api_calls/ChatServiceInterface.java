package data_access.gpt_api_calls;

public interface ChatServiceInterface {

    void addMessageToHistory(String role, String content);

    String getChatbotResponse();

    String buildMessagesJson();

    String parseAssistantMessage(String responseBody);
}
