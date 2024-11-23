package interface_adapter.chat.normal_bot;

import interface_adapter.chat.ChatViewModel;
import use_case.chat.normal_bot.NormalBotOutputBoundary;
import use_case.chat.normal_bot.NormalBotOutputData;

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
