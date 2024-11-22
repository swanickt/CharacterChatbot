package use_case.chat;

public interface ChatApiAccessInterface {

    void addMessageToHistory(String role, String content);

    String getChatbotResponse();

    String buildMessagesJson();

    String parseAssistantMessage(String responseBody);
}
