package use_case.chat;

public interface ChatServiceInterface {

    void addMessageToHistory(String role, String content);

    String getChatbotResponse();

    String buildMessagesJson();

    String parseAssistantMessage(String responseBody);
}
