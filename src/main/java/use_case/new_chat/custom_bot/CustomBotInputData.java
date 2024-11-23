package use_case.new_chat.custom_bot;

public class CustomBotInputData {
    private final String username;
    private final String botName;
    private final String botOccupation;

    public CustomBotInputData(String username, String botName,
                              String botOccupation) {
        this.username = username;
        this.botName = botName;
        this.botOccupation = botOccupation;
    }

    String getUsername() { return username; }

    String getBotName() { return botName; }

    String getBotOccupation() { return botOccupation; }
}
