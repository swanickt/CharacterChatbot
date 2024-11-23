package use_case.new_chat.normal_bot;

public class NormalBotInputData {
    private final String username;

    public NormalBotInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
