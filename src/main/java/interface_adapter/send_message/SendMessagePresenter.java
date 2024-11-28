package interface_adapter.send_message;

import interface_adapter.ViewManagerModel;
import interface_adapter.new_chat.ChatViewModel;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

import javax.swing.text.View;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private final ChatViewModel chatViewModel;
    private final ViewManagerModel viewManagerModel;

    public SendMessagePresenter(ChatViewModel chatViewModel,
                                ViewManagerModel viewManagerModel) {
        this.chatViewModel = chatViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void returnBotResponse(SendMessageOutputData sendMessageOutputData) {
        final String botResponse = sendMessageOutputData.getBotResponse();
        final boolean botResponseError = sendMessageOutputData.getBotResponseError();
        chatViewModel.setBotResponse(botResponse);
        chatViewModel.setBotResponseError(botResponseError);
    }
}
