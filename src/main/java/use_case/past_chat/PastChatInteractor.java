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
        // get the user's username from the input data.
        final String username = pastChatInputData.getUsername();

        // Get the past chat bot responses and user inputs corresponding to this user from the DB.
        // Store them as a list of strings for the output data.
        final List<Message> lst = pastChatAccess.mixedHistory(username);
        final List<String> pastChatMessagesInOrder = new ArrayList<String>();

        for (int i = 0; i < lst.size(); i++) {
            pastChatMessagesInOrder.add(lst.get(i).getContent());
        }

        final PastChatOutputData pastChatOutputData = new PastChatOutputData(pastChatMessagesInOrder);
        pastChatPresenter.presentPastChat(pastChatOutputData);
    }

}
