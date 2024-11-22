package interface_adapter.Optimus_Prime;

import interface_adapter.chat.ChatController;
import data_access.gpt_api_calls.GPTApiCallBotResponseDataAccessObject;
import use_case.OptimusPrime.OptimusPrimeOutputBoundary;
import use_case.OptimusPrime.OptimusPrimeOutputData;
import view.ChatBotSwingApp;

public class OptimusPrimePresenter implements OptimusPrimeOutputBoundary {

    public OptimusPrimePresenter() {

    }

    @Override
    public void beginChat(OptimusPrimeOutputData optimusPrimeOutputData) {
        final String username = optimusPrimeOutputData.getUsername();
        final GPTApiCallBotResponseDataAccessObject GPTApiCallBotResponseDataAccessObject = optimusPrimeOutputData.getChatService();
        final ChatController chatController = new ChatController(GPTApiCallBotResponseDataAccessObject);
        final ChatBotSwingApp chatApp = new ChatBotSwingApp(chatController, username);
        chatApp.setVisible(true);
    }
}
