package interface_adapter.chat;

import data_access.gpt_api_calls.ChatService;

public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    public void addUserMessage(String message) {
        chatService.addMessageToHistory("user", message);
    }

    public String getAssistantResponse() throws Exception {
        return chatService.getChatbotResponse();
    }

    public void addAssistantMessage(String message) {
        chatService.addMessageToHistory("assistant", message);
    }
}
