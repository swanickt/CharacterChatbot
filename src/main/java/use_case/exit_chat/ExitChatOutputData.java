package use_case.exit_chat;

public class ExitChatOutputData {

    private final boolean endChat;

    public ExitChatOutputData(boolean endChat) {
        this.endChat = endChat;
    }

    public boolean getEndChat() { return endChat; }
}
