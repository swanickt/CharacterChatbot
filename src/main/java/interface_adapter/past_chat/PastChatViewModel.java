package interface_adapter.past_chat;

import java.util.List;

public class PastChatViewModel {

    private List<String> pastChat;


    public void setPastChat(List<String> chat) {
        this.pastChat = chat;
    }

    public List<String> getPastChat() {
        return pastChat;
    }
}
