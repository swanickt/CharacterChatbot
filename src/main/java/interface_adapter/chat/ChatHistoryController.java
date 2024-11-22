package interface_adapter.chat;

import entity.chat.CommonUserChat;
import use_case.chat_history.ChatHistoryInteractor;

public class ChatHistoryController {
    private ChatHistoryInteractor service;

    public ChatHistoryController(ChatHistoryInteractor chatService) {
        this.service = chatService;
    }

    public CommonUserChat getchat() {
        return this.service.getchat();
    }
}
