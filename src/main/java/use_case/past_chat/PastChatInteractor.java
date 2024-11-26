package use_case.past_chat;

import entity.message.Message;
import use_case.exit_chat.ExitChatOutputBoundary;

import java.util.ArrayList;
import java.util.List;

public class PastChatInteractor implements PastChatInputBoundary {
    private final PastChatOutputBoundary pastChatPresenter;
    private final PastChatUserDataAccessInterface pastChatAccess;

    public PastChatInteractor(PastChatOutputBoundary pastChatPresenter,
                              PastChatUserDataAccessInterface pastChatAccess) {
        this.pastChatPresenter = pastChatPresenter;
        this.pastChatAccess = pastChatAccess;
    }

    @Override
    public void execute(PastChatInputData pastChatInputData) {
        final String username = pastChatInputData.getUsername();
        final List<Message> lst = pastChatAccess.mixedHistory(username);
        final List<String> pastChatMessagesInOrder = new ArrayList<String>();

        for (int i = 0; i < lst.size(); i++) {
            pastChatMessagesInOrder.add(lst.get(i).getContent());
        }

        final PastChatOutputData pastChatOutputData = new PastChatOutputData(pastChatMessagesInOrder);
        pastChatPresenter.presentPastChat(pastChatOutputData);
    }

}
