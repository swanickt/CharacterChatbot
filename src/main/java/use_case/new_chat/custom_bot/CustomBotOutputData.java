package use_case.new_chat.custom_bot;

public class CustomBotOutputData {
    private final String username;
    private final String prompt;

    public CustomBotOutputData(String username, String prompt) {
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
