package interface_adapter.chat;

import entity.chat.CommonUserChat;
import data_access.gpt_api_calls.chatHistoryService;

public class ChatHistoryController {
    private chatHistoryService service;

    public ChatHistoryController(chatHistoryService chatService) {
        this.service = chatService;
    }

    public CommonUserChat getchat() {
        return this.service.getchat();
    }
}
