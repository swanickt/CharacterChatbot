package data_access.gpt_api_calls;

import use_case.send_message.ChatApiAccessInterface;

import java.util.Map;

public class MockGPTapi implements ChatApiAccessInterface {
    public String setting;
    public Map<String, String> history;

    @Override
    public void addMessageToHistory(String role, String content) {
        history.put(role, content);
    }

    @Override
    public String getChatbotResponse() {
        return "response from GPT";
    }

    @Override
    public void setSystemSetting(String settings) {
        this.setting = settings;
    }
}
