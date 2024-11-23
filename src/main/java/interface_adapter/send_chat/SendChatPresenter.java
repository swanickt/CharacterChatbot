package interface_adapter.send_chat;

import interface_adapter.new_chat.ChatViewModel;
import use_case.send_chat.SendChatInputBoundary;
import use_case.send_chat.SendChatOutputBoundary;
import use_case.send_chat.SendChatOutputData;

public class SendChatPresenter implements SendChatOutputBoundary {

    private final ChatViewModel chatViewModel;

    public SendChatPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void returnBotResponse(SendChatOutputData sendChatOutputData) {
        final String botResponse = sendChatOutputData.getBotResponse();
        final boolean botResponseError = sendChatOutputData.getBotResponseError();
        chatViewModel.setBotResponse(botResponse);
        chatViewModel.setBotResponseError(botResponseError);
    }
}
