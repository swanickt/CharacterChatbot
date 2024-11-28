package interface_adapter.past_chat;

import interface_adapter.ViewModel;

import java.util.List;

public class PastChatViewModel extends ViewModel<PastChatState> {

    private List<String> pastChat;

    public PastChatViewModel() {
        super("past chat");
        setState(new PastChatState());
    }


    public void setPastChat(List<String> chat) {
        this.pastChat = chat;
    }

    public List<String> getPastChat() {
        return pastChat;
    }
}
