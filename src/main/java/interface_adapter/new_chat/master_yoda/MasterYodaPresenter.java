package interface_adapter.new_chat.master_yoda;

import interface_adapter.new_chat.ChatViewModel;
import use_case.new_chat.master_yoda.MasterYodaOutputBoundary;
import use_case.new_chat.master_yoda.MasterYodaOutputData;

public class MasterYodaPresenter implements MasterYodaOutputBoundary {

    private final ChatViewModel chatViewModel;

    public MasterYodaPresenter(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void beginChat(MasterYodaOutputData masterYodaOutputData) {
        final String username = masterYodaOutputData.getUsername();
        final String prompt = masterYodaOutputData.getPrompt();
        chatViewModel.setChatState(username, prompt);
        chatViewModel.firePropertyChanged("new chat");
    }

    // only for test
    @Override
    public boolean isBeginChatCalled() {
        return false;
    }

    // only for test
    @Override
    public MasterYodaOutputData getCapturedOutputData() {
        return null;
    }
}
