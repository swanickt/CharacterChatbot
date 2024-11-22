package use_case.chat.pikachu;

public class PikachuOutputData {
    private final String username;
    private final String prompt;

    public PikachuOutputData(String username, String prompt) {
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
