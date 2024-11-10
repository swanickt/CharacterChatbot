package use_case.ChatService;

public interface ChatServiceInterface {

    void addMessageToHistory(String role, String content);

    String getChatbotResponse();

    String buildMessagesJson();

    String parseAssistantMessage(String responseBody);
}
