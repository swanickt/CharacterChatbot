package use_case.ChatService;

import entity.chat.Chat;
import entity.chat.CommonUserChat;

public class chatHistoryService {
    String userName;
    CommonUserChat chat;

    public chatHistoryService(String userName, CommonUserChat chat) {
        this.userName = userName;
        this.chat = chat;
    }

    public CommonUserChat getchat() {
        return chat;
    }
}
