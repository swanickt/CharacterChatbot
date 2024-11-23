package use_case.send_message;

public class SendMessageOutputData {
    private String botResponse = "";
    private boolean botResponseError = false;

    public SendMessageOutputData(String botResponse, boolean botResponseError) {
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
