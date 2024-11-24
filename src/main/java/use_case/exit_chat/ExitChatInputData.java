package use_case.exit_chat;

public class ExitChatInputData {
    private final String username;

    public ExitChatInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
