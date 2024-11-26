package interface_adapter.past_chat;

import entity.chat.CommonUserChat;
import use_case.past_chat.PastChatInputBoundary;
import use_case.past_chat.PastChatInputData;
import use_case.past_chat.PastChatInteractor;
import use_case.send_message.SendMessageInputData;

public class PastChatController {
    private final PastChatInputBoundary pastChatInteractor;

    public PastChatController(PastChatInputBoundary pastChatInteractor) {
        this.pastChatInteractor = pastChatInteractor;
    }

    /**
     * Executes the see past chat use Case.
     * @param username the username of the user getting their past chat.
     */
    public void execute(String username) {
        final PastChatInputData pastChatInputData = new PastChatInputData(username);
        pastChatInteractor.execute(pastChatInputData);
    }
}
