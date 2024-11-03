package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class ChatBotSwingApp extends JFrame {
    private static final String API_KEY = "sk-FQEt-6SN5RGeq_GDQTBovqwPoDa0Oj9wsOiOJEwdhhT3BlbkFJqrncaGUW0oEU8n04Swa_K-1qALE3eUtSjiIwxVnu8A";// Replace with your actual API key
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private JPanel chatPanel;
    private JTextField inputField;
    private JButton sendButton;
    private List<Map<String, String>> conversationHistory;

    @SuppressWarnings("checkstyle:MagicNumber")
    public ChatBotSwingApp(String system_setting) {
        // Initialize main frame
        setTitle("Character Chatbot");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chat display panel with BoxLayout for vertical stacking
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(Color.WHITE);

        final JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Input panel
        inputField = new JTextField();
        sendButton = new JButton("Send");
        final JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Initialize conversation history
        conversationHistory = new ArrayList<>();
        addMessageToHistory("system", system_setting);
        // Send button action listener
        sendButton.addActionListener(new ActionListener() {
            @SuppressWarnings({"checkstyle:FinalLocalVariable", "checkstyle:RightCurly",
                    "checkstyle:IllegalCatch", "checkstyle:ParameterName", "checkstyle:MultipleStringLiterals",
                    "checkstyle:Indentation"})
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim();
                if (!userInput.isEmpty()) {
                    addChatBubble(userInput, "user");
                    inputField.setText("");

                    addMessageToHistory("user", userInput);

                    // Fetch GPT response
                    try {
                        final String response = getChatbotResponse();
                        addChatBubble(response, "assistant");
                        addMessageToHistory("assistant", response);
                    } catch (Exception ex) {
                        addChatBubble("Error: Unable to get response from GPT.", "error");
                    }
                }
            }
        });
    }

    // Function to add message bubble to chat panel
    @SuppressWarnings({"checkstyle:EqualsAvoidNull", "checkstyle:FinalLocalVariable",
            "checkstyle:MagicNumber", "checkstyle:AvoidInlineConditionals", "checkstyle:Indentation"})
    private void addChatBubble(String message, String role) {
        // 设置 messagePanel 的布局为 FlowLayout，根据角色不同设置对齐方向
        final JPanel messagePanel =
                new JPanel(new FlowLayout(role.equals("user") ? FlowLayout.RIGHT : FlowLayout.LEFT, 0, 2));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        JTextArea messageLabel = new JTextArea(message);
        messageLabel.setLineWrap(true);
        messageLabel.setWrapStyleWord(true);
        messageLabel.setOpaque(true);
        messageLabel.setEditable(false);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        messageLabel.setBackground(new Color(211, 211, 211));
        messageLabel.setForeground(Color.BLACK);

        // 设置宽度限制，但不限制高度，让高度自动扩展
        int maxWidth = chatPanel.getWidth() - 50;
        messageLabel.setPreferredSize(null);
        messageLabel.setMaximumSize(new Dimension(maxWidth, Integer.MAX_VALUE));

        // 计算内容所需的最小宽度和高度
        messageLabel.setSize(new Dimension(maxWidth, Integer.MAX_VALUE));
        int bubbleWidth = Math.min(maxWidth, messageLabel.getPreferredSize().width);
        int bubbleHeight = messageLabel.getPreferredSize().height;

        // 使用适合内容的宽度和高度设置最终尺寸
        messageLabel.setPreferredSize(new Dimension(bubbleWidth, bubbleHeight));

        // 将气泡添加到 messagePanel
        messagePanel.add(messageLabel);

        // 将 messagePanel 添加到 chatPanel
        chatPanel.add(messagePanel);
        chatPanel.revalidate();
        chatPanel.repaint();

        // 自动滚动到底部
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalBar = ((JScrollPane) chatPanel.getParent().getParent()).getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMaximum());
        });
    }

    // Add message to conversation history
    @SuppressWarnings("checkstyle:FinalLocalVariable")
    private void addMessageToHistory(String role, String content) {
        Map<String, String> message = new HashMap<>();
        message.put("role", role);
        message.put("content", content);
        conversationHistory.add(message);
    }

    // Fetch response from GPT API
    @SuppressWarnings("checkstyle:FinalLocalVariable")
    private String getChatbotResponse() throws Exception {
        String jsonInputString = "{\"model\": \"gpt-4\", \"messages\": " + buildMessagesJson() + "}";

        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        return parseAssistantMessage(response.toString());
    }

    // Build JSON formatted conversation history
    @SuppressWarnings({"checkstyle:FinalLocalVariable", "checkstyle:MultipleStringLiterals"})
    private String buildMessagesJson() {
        StringBuilder messagesJson = new StringBuilder("[");
        for (Map<String, String> message : conversationHistory) {
            messagesJson.append("{")
                    .append("\"role\": \"").append(message.get("role")).append("\", ")
                    .append("\"content\": \"").append(message.get("content")).append("\"")
                    .append("},");
        }
        if (!conversationHistory.isEmpty()) {
            messagesJson.deleteCharAt(messagesJson.length() - 1);
        }
        messagesJson.append("]");
        return messagesJson.toString();
    }

    // Parse assistant's message from API response
    @SuppressWarnings({"checkstyle:FinalLocalVariable", "checkstyle:ReturnCount", "checkstyle:RightCurly"})
    private static String parseAssistantMessage(String responseBody) {
        String contentKey = "\"content\":";
        int contentIndex = responseBody.indexOf(contentKey);
        if (contentIndex != -1) {
            int startIndex = responseBody.indexOf("\"", contentIndex + contentKey.length()) + 1;
            int endIndex = responseBody.indexOf("\"", startIndex);
            String content = responseBody.substring(startIndex, endIndex);
            return content.trim();
        } else {
            return "Content not found in the response.";
        }
    }

    @SuppressWarnings({"checkstyle:FinalLocalVariable", "checkstyle:UncommentedMain", "checkstyle:LineLength", "checkstyle:MissingJavadocMethod", "checkstyle:UnusedLocalVariable"})
    public static void main(String[] args) {
        String setting = "You are a helpful assistant, reply in short sentence,act as you are talking to a normal human, and always remember your role, an assistant,your reply should not exceed 20 words and only reply in plain word text";
        String setting2 = "You are optimus prime, the leader of the Autobots, always remember your role and your reply should not exceed 20 words and only reply in plain word text, you have to behave like optimus";
        ChatBotSwingApp chatApp = new ChatBotSwingApp(setting2);
        chatApp.setVisible(true);
    }
}
