package use_case.past_chat;

import entity.chat.CommonUserChat;

public class ChatHistoryInteractor {
    String userName;
    CommonUserChat chat;

    public ChatHistoryInteractor(String userName, CommonUserChat chat) {
        this.userName = userName;
        this.chat = chat;
    }

    public CommonUserChat getchat() {
        return chat;
    }
}
