package interface_adapter.chat;

import entity.chat.CommonUserChat;
import use_case.ChatService.ChatService;
import use_case.ChatService.chatHistoryService;

public class ChatHistoryController {
    private chatHistoryService service;

    public ChatHistoryController(chatHistoryService chatService) {
        this.service = chatService;
    }

    public CommonUserChat getchat() {
        return this.service.getchat();
    }
}
