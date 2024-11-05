package entity.user;

import java.util.ArrayList;
import java.util.List;

import entity.chat.Chat;
/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private List<Chat> chatHistory;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.chatHistory = new ArrayList<>();

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<Chat> getChatHistory() {
        return chatHistory;
    }

}
