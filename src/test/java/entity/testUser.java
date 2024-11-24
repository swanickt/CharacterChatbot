package entity;
import entity.chat.Chat;
import entity.chat.CommonUserChat;
import entity.user.CommonUser;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class testUser {
    @Test
    public void testUserCreation() {
        User user = new CommonUser("testUser", "testPassword");
        assertNotNull(user);
        assertEquals("testUser", user.getName());
        assertEquals("testPassword", user.getPassword());
        assertNotNull(user.getChatHistory());
        assertEquals(0, user.getChatHistory().size());
    }

    @Test
    public void testGetName() {
        User user = new CommonUser("testUser", "testPassword");
        assertEquals("testUser", user.getName());
    }

    @Test
    public void testGetPassword() {
        User user = new CommonUser("testUser", "testPassword");
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void testGetChatHistory() {
        User user = new CommonUser("testUser", "testPassword");
        assertNotNull(user.getChatHistory());
        assertTrue(user.getChatHistory().isEmpty());

        Chat chat = new CommonUserChat();
        user.getChatHistory().add(chat);
        assertEquals(1, user.getChatHistory().size());
        assertEquals(chat, user.getChatHistory().get(0));
    }
    @Test
    public void testUserFactoryCreation() {
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("testUser", "testPassword");
        assertNotNull(user);
        assertEquals("testUser", user.getName());
        assertEquals("testPassword", user.getPassword());
    }
}
