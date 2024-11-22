package interface_adapter.chat.optimus_prime;

import interface_adapter.chat.ChatController;
import data_access.gpt_api_calls.GptApiCallBotResponseDataAccessObject;
import interface_adapter.chat.ChatViewModel;
import use_case.chat.optimus_prime.OptimusPrimeOutputBoundary;
import use_case.chat.optimus_prime.OptimusPrimeOutputData;
import use_case.chat.pikachu.PikachuOutputData;
import view.ChatView;

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
