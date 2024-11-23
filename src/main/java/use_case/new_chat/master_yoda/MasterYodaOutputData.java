package use_case.new_chat.master_yoda;

public class MasterYodaOutputData {
    private final String username;
    private final String prompt;

    public MasterYodaOutputData(String username, String prompt) {
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
