package interface_adapter.past_chat;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.past_chat.PastChatOutputBoundary;
import use_case.past_chat.PastChatOutputData;

import java.util.List;

public class PastChatPresenter implements PastChatOutputBoundary {

    private final PastChatViewModel pastChatViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public PastChatPresenter(PastChatViewModel pastChatViewModel,
                             ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel) {
        this.pastChatViewModel = pastChatViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void presentPastChat(PastChatOutputData pastChatOutputData) {
        final List<String> pastChat = pastChatOutputData.getPastChat();
        pastChatViewModel.setPastChat(pastChat);
    }
}
