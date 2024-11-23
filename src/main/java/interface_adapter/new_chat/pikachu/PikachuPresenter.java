package interface_adapter.new_chat.pikachu;

import interface_adapter.new_chat.ChatViewModel;
import use_case.new_chat.pikachu.PikachuOutputBoundary;
import use_case.new_chat.pikachu.PikachuOutputData;

public class PikachuPresenter implements PikachuOutputBoundary {

    private final ChatViewModel chatViewModel;

    public PikachuPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void beginChat(PikachuOutputData pikachuOutputData) {
        final String username = pikachuOutputData.getUsername();
        final String prompt = pikachuOutputData.getPrompt();
        chatViewModel.setChatState(username, prompt);
        chatViewModel.firePropertyChanged("new chat");
    }
}
