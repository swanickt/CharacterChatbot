package interface_adapter.past_chat;

import use_case.past_chat.PastChatOutputBoundary;
import use_case.past_chat.PastChatOutputData;

import java.util.List;

public class PastChatPresenter implements PastChatOutputBoundary {

    private final PastChatViewModel pastChatViewModel;

    public PastChatPresenter(PastChatViewModel pastChatViewModel) {
        this.pastChatViewModel = pastChatViewModel;
    }

    @Override
    public void presentPastChat(PastChatOutputData pastChatOutputData) {
        final List<String> pastChat = pastChatOutputData.getPastChat();
        pastChatViewModel.setPastChat(pastChat);
    }
}
