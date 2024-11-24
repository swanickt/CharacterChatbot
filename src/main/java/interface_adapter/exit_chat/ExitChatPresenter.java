package interface_adapter.exit_chat;

import interface_adapter.new_chat.ChatViewModel;
import use_case.exit_chat.ExitChatOutputBoundary;
import use_case.exit_chat.ExitChatOutputData;

/**
 * The Presenter for the Exit Chat use Case.
 */
public class ExitChatPresenter implements ExitChatOutputBoundary {

    private final ChatViewModel chatViewModel;

    public ExitChatPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void endChat(ExitChatOutputData exitChatOutputData) {
        final boolean endChat = exitChatOutputData.getEndChat();
        chatViewModel.setEndChat(endChat);
    }
}
