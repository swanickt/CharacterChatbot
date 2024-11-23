package interface_adapter.new_chat.normal_bot;

import interface_adapter.new_chat.ChatViewModel;
import use_case.new_chat.normal_bot.NormalBotOutputBoundary;
import use_case.new_chat.normal_bot.NormalBotOutputData;

public class NormalBotPresenter implements NormalBotOutputBoundary {

    private final ChatViewModel chatViewModel;

    public NormalBotPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void beginChat(NormalBotOutputData normalBotOutputData) {
        final String username = normalBotOutputData.getUsername();
        final String prompt = normalBotOutputData.getPrompt();
        chatViewModel.setChatState(username, prompt);
        chatViewModel.firePropertyChanged("new chat");
    }
}
