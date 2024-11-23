package interface_adapter.new_chat.optimus_prime;

import interface_adapter.new_chat.ChatViewModel;
import use_case.new_chat.optimus_prime.OptimusPrimeOutputBoundary;
import use_case.new_chat.optimus_prime.OptimusPrimeOutputData;

public class OptimusPrimePresenter implements OptimusPrimeOutputBoundary {

    private final ChatViewModel chatViewModel;

    public OptimusPrimePresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void beginChat(OptimusPrimeOutputData optimusPrimeOutputData) {
        final String username = optimusPrimeOutputData.getUsername();
        final String prompt = optimusPrimeOutputData.getPrompt();
        chatViewModel.setChatState(username, prompt);
        chatViewModel.firePropertyChanged("new chat");
    }
}
