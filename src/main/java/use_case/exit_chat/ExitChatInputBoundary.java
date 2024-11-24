package use_case.exit_chat;

public interface ExitChatInputBoundary {

    void newChat(String username);

    void saveGreeting(String user, String initialResponse);

    void saveHistory(String sender, String message);

    void addBotResponse(String botResponse);

    void addUserInput(String userInput);

    void execute(ExitChatInputData exitChatInputData);
}
