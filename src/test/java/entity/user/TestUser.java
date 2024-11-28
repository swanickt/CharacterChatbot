package entity.user;
import entity.chat.Chat;
import entity.chat.CommonUserChat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUser {
    @Test
    public void testUserCreation() {
        User user = new CommonUser("TestUser", "testPassword");
        assertNotNull(user);
        assertEquals("TestUser", user.getName());
        assertEquals("testPassword", user.getPassword());
        assertNotNull(user.getChatHistory());
        assertEquals(0, user.getChatHistory().size());
    }

    @Test
    public void testGetName() {
        User user = new CommonUser("TestUser", "testPassword");
        assertEquals("TestUser", user.getName());
    }

    @Test
    public void testGetPassword() {
        User user = new CommonUser("TestUser", "testPassword");
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void testGetChatHistory() {
        User user = new CommonUser("TestUser", "testPassword");
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
        User user = factory.create("TestUser", "testPassword");
        assertNotNull(user);
        assertEquals("TestUser", user.getName());
        assertEquals("testPassword", user.getPassword());
    }
}
