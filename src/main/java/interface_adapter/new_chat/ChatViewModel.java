package interface_adapter.new_chat;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Chat View.
 */
public class ChatViewModel extends ViewModel<ChatViewState> {

    public static final String TITLE_LABEL = "Character Chatbot";
    public static final String SEND_BUTTON_LABEL = "Send";
    public static final String EXIT_BUTTON_LABEL = "Exit";
    public static final String FIRST_BACKEND_PROMPT = "hello, tell me who are you and give me a "
            + "greeting according to your character. Make it so the character is easily recognizable.";
    public static final String TEXT_FONT = "Arial";

    private String username = "";
    private String prompt = "";
    private String botResponse = "";
    private boolean botResponseError;
    private boolean endChat = false;


    public ChatViewModel() {
        super("chat view");
        setState(new ChatViewState());
    }

    /**
     * set the state of the chat.
     * @param username the username of the user chatting
     * @param prompt the prompt for the relevant character.
     */
    public void setChatState(String username, String prompt) {
        this.username = username;
        this.prompt = prompt;
    }

    public void setEndChat(boolean endChat) {
        this.endChat = endChat;
    }

    public boolean getEndChat() {
        return endChat;
    }

    public String getUsername() {
        return username;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getBotResponse() {
        return botResponse;
    }

    public boolean getBotResponseError() {
        return botResponseError;
    }

    public void setBotResponse(String response) {
        this.botResponse = response;
    }

    public void setBotResponseError(boolean responseError) {
        this.botResponseError = responseError;
    }

}
