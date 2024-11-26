package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.user.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.exit_chat.SaveChatHistoryUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface, SaveChatHistoryUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;
    public Map<String, String> database;
    public String username;
    // null value for currentUsername means that nobody is logged in.

    public InMemoryUserDataAccessObject() {
        database = new HashMap<>();
    }

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public void setUp(String username) {
        this.username = username;
    }

    @Override
    public void saveHistory(String send, String message) {
        this.database.put(send, message);
    }

    @Override
    public void saveGreeting(String user, String greeting) {
        this.database.put(user, greeting);
    }
}
