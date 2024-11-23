package view;

import data_access.MongoDBDataAccessObject;
import entity.message.Message;
import interface_adapter.chat_history.ChatHistoryController;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ChatHistoryView extends JFrame {
    private JPanel chatPanel;
    private String username;
    private JButton exitButton;
    private final List<Message> lst;
    private final List<Message> lst2;
    private ChatHistoryController chathistorycontroller;
    private MongoDBDataAccessObject dbchatuser;
    @SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:SuppressWarnings", "checkstyle:LambdaParameterName", "checkstyle:EmptyLineSeparator"})
    public ChatHistoryView(ChatHistoryController chatHistoryController, String username) {
        this.dbchatuser = new MongoDBDataAccessObject();
        this.username = username;
        lst = dbchatuser.userHistory(username);
        lst2 = dbchatuser.chatHistory(username);
        // Initialize main frame
        setTitle("Past Chat");
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

        exitButton = new JButton("Exit");
        // Add exit button to the top right corner
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(exitButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        SwingUtilities.invokeLater(() -> {

            for (int i = 0; i < lst.size(); i++) {
                addChatBubble(lst.get(i).getContent(), "user");
                System.out.println(lst.get(i).getContent());
                addChatBubble(lst2.get(i).getContent(), "assistant");
                System.out.println(lst2.get(i).getContent());
            }
        });

        // Exit button action listener
        exitButton.addActionListener(e -> {
            setVisible(false);
        });

    }
    @SuppressWarnings({"checkstyle:EqualsAvoidNull", "checkstyle:FinalLocalVariable",
            "checkstyle:MagicNumber", "checkstyle:AvoidInlineConditionals", "checkstyle:Indentation", "checkstyle:EmptyLineSeparator", "checkstyle:SuppressWarnings"})
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

    public static void main(String[] args) {
    }

}
