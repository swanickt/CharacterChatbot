package view;

import data_access.MongoDBDataAccessObject;
import entity.chat.CommonUserChat;
import entity.chat.CommonUserChatFactory;
import entity.message.Message;
import interface_adapter.exit_chat.ExitChatController;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.new_chat.ChatViewModel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The chatting view/"app" for all chats, regardless of the character.
 */
public class ChatView extends JFrame {
    private JPanel chatPanel;
    private JTextField inputField;
    private JButton sendButton;
    private JButton exitButton;
    private SendMessageController sendMessageController;
    private ExitChatController exitChatController;
    private CommonUserChatFactory chatFactory;
    private String username;
    private CommonUserChat chat;
    private MongoDBDataAccessObject dbchatuser;

    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:LambdaParameterName", "checkstyle:RightCurly", "checkstyle:IllegalCatch", "checkstyle:LambdaBodyLength", "checkstyle:VariableDeclarationUsageDistance", "checkstyle:JavaNCSS"})

    public ChatView(SendMessageController sendMessageController,
                    String username,
                    ChatViewModel chatViewModel,
                    ExitChatController exitChatController) {
        this.sendMessageController = sendMessageController;
        this.exitChatController = exitChatController;
        this.username = username;

        exitChatController.newChat(username);
        // Initialize main frame
        setTitle(ChatViewModel.TITLE_LABEL);
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
        sendButton = new JButton(ChatViewModel.SEND_BUTTON_LABEL);
        exitButton = new JButton(ChatViewModel.EXIT_BUTTON_LABEL);
        final JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Add exit button to the top right corner
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(exitButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Send button action listener
        sendButton.addActionListener(new ActionListener() {
            @SuppressWarnings({"checkstyle:IllegalCatch", "checkstyle:ParameterName", "checkstyle:EmptyStatement", "checkstyle:MultipleStringLiterals"})
            @Override
            public void actionPerformed(ActionEvent e) {
                final String userInput = inputField.getText().replace("\n", "");
                if (!userInput.isEmpty()) {
                    addChatBubble(userInput, "user");
                    exitChatController.addUserInput(userInput);
                    inputField.setText("");
                    sendMessageController.execute(userInput);
                    // exitChatController.saveHistory(username, userInput);
                    // Fetch GPT response
                    if (chatViewModel.getBotResponseError() == true) {
                        addChatBubble("Error: Unable to get response from GPT.", "error");
                    }
                    else {
                        addChatBubble(chatViewModel.getBotResponse().replace("\n", ""), "assistant");
                        exitChatController.addBotResponse(chatViewModel.getBotResponse().replace("\n", ""));
                    }
                }
            }
        });

        // Exit button action listener
        exitButton.addActionListener(e -> {
            exitChatController.execute(username);
            if (chatViewModel.getEndChat()) {
                setVisible(false);
            }
        });
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            try {
                // 模拟发送 "hello" 消息但不显示在界面上
                sendMessageController.execute(ChatViewModel.FIRST_BACKEND_PROMPT);
                // 获取助手的响应并在界面上显示为气泡形式
                final String initialResponse = chatViewModel.getBotResponse();
                if (!initialResponse.isEmpty()) {
                    SwingUtilities.invokeLater(() -> {
                        addChatBubble(initialResponse.replace("\n", ""), "assistant");
                        exitChatController.saveGreeting("assistant", initialResponse.replace("\n", ""));
                        chatPanel.revalidate();
                        chatPanel.repaint();
                    });
                }
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    addChatBubble("Error: Unable to get response from GPT.", "error");
                    chatPanel.revalidate();
                    chatPanel.repaint();
                });
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
        messageLabel.setFont(new Font(ChatViewModel.TEXT_FONT, Font.PLAIN, 14));

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
}
