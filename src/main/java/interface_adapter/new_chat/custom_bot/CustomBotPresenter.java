package interface_adapter.new_chat.custom_bot;

import interface_adapter.new_chat.ChatViewModel;
import use_case.new_chat.custom_bot.CustomBotOutputBoundary;
import use_case.new_chat.custom_bot.CustomBotOutputData;

public class CustomBotPresenter implements CustomBotOutputBoundary {

    private final ChatViewModel chatViewModel;

    public CustomBotPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void beginChat(CustomBotOutputData customBotOutputData) {
        final String username = customBotOutputData.getUsername();
        final String prompt = customBotOutputData.getPrompt();
        chatViewModel.setChatState(username, prompt);
    }

    // only for test
    @Override
    public boolean isBeginChatCalled() {
        return false;
    }

    // only for test
    @Override
    public CustomBotOutputData getCapturedOutputData() {
        return null;
    }
}
