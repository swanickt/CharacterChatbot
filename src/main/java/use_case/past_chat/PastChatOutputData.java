package use_case.past_chat;

import java.util.ArrayList;
import java.util.List;

public class PastChatOutputData {
    private List<String> pastChat = new ArrayList<String>();

    public PastChatOutputData(List<String> pastChat) {
        this.pastChat = pastChat;
    }

    public List<String> getPastChat() {
        return pastChat;
    }
}
