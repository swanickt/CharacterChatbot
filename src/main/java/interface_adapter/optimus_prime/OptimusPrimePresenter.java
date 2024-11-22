package interface_adapter.optimus_prime;

import interface_adapter.chat.ChatController;
import data_access.gpt_api_calls.GPTApiCallBotResponseDataAccessObject;
import use_case.optimus_prime.OptimusPrimeOutputBoundary;
import use_case.optimus_prime.OptimusPrimeOutputData;
import view.ChatView;

public class OptimusPrimePresenter implements OptimusPrimeOutputBoundary {

    public OptimusPrimePresenter() {

    }

    @Override
    public void beginChat(OptimusPrimeOutputData optimusPrimeOutputData) {
        final String username = optimusPrimeOutputData.getUsername();
        final GPTApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject = optimusPrimeOutputData.getChatService();
        final ChatController chatController = new ChatController(GPTApiCallBotResponseDataAccessObject);
        final ChatView chatApp = new ChatView(chatController, username);
        chatApp.setVisible(true);
    }
}
