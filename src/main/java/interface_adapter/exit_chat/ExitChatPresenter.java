package interface_adapter.exit_chat;

import interface_adapter.new_chat.ChatViewModel;

/**
 * The Presenter for the Exit Chat use Case.
 */
public class ExitChatPresenter {
    private final ChatViewModel chatViewModel;

    public ExitChatPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

}
