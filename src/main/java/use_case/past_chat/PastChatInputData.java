package use_case.past_chat;

public class PastChatInputData {
    private final String username;

    public PastChatInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
