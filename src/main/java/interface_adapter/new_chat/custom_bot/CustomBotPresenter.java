package interface_adapter.new_chat.custom_bot;

import interface_adapter.ViewManagerModel;
import interface_adapter.custom_bot_page.CustomBotViewModel;
import interface_adapter.new_chat.ChatViewModel;
import use_case.new_chat.custom_bot.CustomBotOutputBoundary;
import use_case.new_chat.custom_bot.CustomBotOutputData;

public class CustomBotPresenter implements CustomBotOutputBoundary {

    private final ChatViewModel chatViewModel;
    private final CustomBotViewModel customBotViewModel;
    private final ViewManagerModel viewManagerModel;

    public CustomBotPresenter(ChatViewModel chatViewModel,
                              ViewManagerModel viewManagerModel,
                              CustomBotViewModel customBotViewModel) {
        this.chatViewModel = chatViewModel;
        this.viewManagerModel = viewManagerModel;
        this.customBotViewModel = customBotViewModel;

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
