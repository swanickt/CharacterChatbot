package interface_adapter.Optimus_Prime;

import interface_adapter.chat.ChatController;
import data_access.gpt_api_calls.ChatService;
import use_case.OptimusPrime.OptimusPrimeOutputBoundary;
import use_case.OptimusPrime.OptimusPrimeOutputData;
import view.ChatBotSwingApp;

public class OptimusPrimePresenter implements OptimusPrimeOutputBoundary {

    public OptimusPrimePresenter() {

    }

    @Override
    public void beginChat(OptimusPrimeOutputData optimusPrimeOutputData) {
        final String username = optimusPrimeOutputData.getUsername();
        final ChatService chatService = optimusPrimeOutputData.getChatService();
        final ChatController chatController = new ChatController(chatService);
        final ChatBotSwingApp chatApp = new ChatBotSwingApp(chatController, username);
        chatApp.setVisible(true);
    }
}
