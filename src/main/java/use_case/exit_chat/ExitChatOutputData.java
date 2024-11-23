package use_case.exit_chat;

import entity.chat.CommonUserChat;

/**
 * Output Data for the exit chat Use Case.
 */
public class ExitChatOutputData {
    private CommonUserChat chat;

    public ExitChatOutputData(CommonUserChat chat) {
        this.chat = chat;
    }

    public CommonUserChat getChat() {
        return chat;
    }
}
