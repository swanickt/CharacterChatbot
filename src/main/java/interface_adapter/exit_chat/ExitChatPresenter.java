package interface_adapter.exit_chat;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.new_chat.ChatViewModel;
import use_case.exit_chat.ExitChatOutputBoundary;
import use_case.exit_chat.ExitChatOutputData;

import javax.swing.text.View;

/**
 * The Presenter for the Exit Chat use Case.
 */
public class ExitChatPresenter implements ExitChatOutputBoundary {

    private final ChatViewModel chatViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public ExitChatPresenter(ChatViewModel chatViewModel,
                             ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel) {
        this.chatViewModel = chatViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;

    }

    @Override
    public void endChat(ExitChatOutputData exitChatOutputData) {
        final boolean endChat = exitChatOutputData.getEndChat();
        chatViewModel.setEndChat(endChat);
    }
}
