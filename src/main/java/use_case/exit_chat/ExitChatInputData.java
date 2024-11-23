package use_case.exit_chat;

import entity.chat.CommonUserChat;
/**
 * Iutput Data for the exit chat Use Case.
 */
public class ExitChatInputData {
    private CommonUserChat chat;
    private String username;

    public ExitChatInputData(CommonUserChat chat, String username) {
        this.chat = chat;
        this.username = username;
    }

    public CommonUserChat getChat() {return chat;}

    public String getUsername() {return username;}
}
