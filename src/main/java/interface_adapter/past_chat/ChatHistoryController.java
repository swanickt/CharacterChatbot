package interface_adapter.past_chat;

import entity.chat.CommonUserChat;
import use_case.past_chat.ChatHistoryInteractor;

public class ChatHistoryController {
    private ChatHistoryInteractor service;

    public ChatHistoryController(ChatHistoryInteractor chatService) {
        this.service = chatService;
    }

    public CommonUserChat getchat() {
        return this.service.getchat();
    }
}
