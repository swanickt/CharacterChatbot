package use_case.chat.normal_bot;

public class NormalBotOutputData {
    private final String username;
    private final String prompt;

    public NormalBotOutputData(String username, String prompt) {
        this.username = username;
        this.prompt = prompt;
    }

    public String getUsername() {
        return username;
    }

    public String getPrompt() {
        return prompt;
    }
}
