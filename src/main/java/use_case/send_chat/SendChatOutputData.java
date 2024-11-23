package use_case.send_chat;

public class SendChatOutputData {
    private String botResponse = "";
    private boolean botResponseError = false;

    public SendChatOutputData(String botResponse, boolean botResponseError) {
        this.botResponse = botResponse;
        this.botResponseError = botResponseError;
    }

    public String getBotResponse() {
        return botResponse;
    }

    public boolean getBotResponseError() {
        return botResponseError;
    }
}
