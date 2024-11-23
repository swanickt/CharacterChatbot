package use_case.send_chat;

public interface SendChatInputBoundary {

    void execute(SendChatInputData sendChatInputData);

    void setSystemSetting(String prompt);
}
