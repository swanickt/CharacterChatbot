package data_access.gpt_api_calls;

import entity.message.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class GPTApiCallBotResponseDataAccessObject {
    private static final String API_KEY = System.getenv("OPENAI_KEY");
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private List<Message> conversationHistory;

    public GPTApiCallBotResponseDataAccessObject(String systemSetting) {
        this.conversationHistory = new ArrayList<>();
        // 添加初始系统设置
        addMessageToHistory("system", systemSetting);
    }

    public void addMessageToHistory(String role, String content) {
        conversationHistory.add(new Message(role, content));
    }

    public String getChatbotResponse() throws Exception {
        final String jsonInputString = "{\"model\": \"gpt-4\", \"messages\": " + buildMessagesJson() + "}";
        final URL url = new URL(API_URL);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            final byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        final StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        return parseAssistantMessage(response.toString());
    }

    private String buildMessagesJson() {
        final StringBuilder messagesJson = new StringBuilder("[");
        for (Message message : conversationHistory) {
            messagesJson.append("{")
                    .append("\"role\": \"").append(message.getRole()).append("\", ")
                    .append("\"content\": \"").append(message.getContent()).append("\"")
                    .append("},");
        }
        if (!conversationHistory.isEmpty()) {
            messagesJson.deleteCharAt(messagesJson.length() - 1);
        }
        messagesJson.append("]");
        return messagesJson.toString();
    }

    @SuppressWarnings("checkstyle:ReturnCount")
    private static String parseAssistantMessage(String responseBody) {
        final String contentKey = "\"content\":";
        final int contentIndex = responseBody.indexOf(contentKey);
        System.out.println(responseBody);
        if (contentIndex != -1) {
            final int startIndex = responseBody.indexOf("\"", contentIndex + contentKey.length()) + 1;
            final int endIndex = responseBody.indexOf("\"", startIndex);
            return responseBody.substring(startIndex, endIndex).trim();
        }
        else {
            return "Content not found in the response.";
        }
    }
}
