package interface_adapter.send_message;

import interface_adapter.new_chat.ChatViewModel;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

public class SendMessagePresenter implements SendMessageOutputBoundary {

    private final ChatViewModel chatViewModel;

    public SendMessagePresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void returnBotResponse(SendMessageOutputData sendMessageOutputData) {
        final String botResponse = sendMessageOutputData.getBotResponse();
        final boolean botResponseError = sendMessageOutputData.getBotResponseError();
        chatViewModel.setBotResponse(botResponse);
        chatViewModel.setBotResponseError(botResponseError);
    }
}
