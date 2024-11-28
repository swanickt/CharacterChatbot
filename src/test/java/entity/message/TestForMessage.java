package entity.message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestForMessage {
    @Test
    public void testMessageCreation() {
        MessageInterface message = new CommonUserMessage("Hello, how are you?", "User");
        assertNotNull(message);
        assertEquals("Hello, how are you?", message.getContent());
        assertEquals("User", message.getRole());
    }

    @Test
    public void testRegularMessageCreation() {
        MessageInterface message = new Message("Hello, how are you?", "User");
        assertNotNull(message);
        assertEquals("User", message.getContent());
        assertEquals("Hello, how are you?", message.getRole());
    }

    @Test
    public void testBotMessageCreation() {
        MessageInterface message = new BotMessage("I am fine, thank you!", "Bot");
        assertNotNull(message);
        assertEquals("I am fine, thank you!", message.getContent());
        assertEquals("Bot", message.getRole());
    }
    @Test
    public void testCommonUserMessageFactory() {
        MessageFactory factory = new CommonUserMessageFactory();
        MessageInterface message = ((CommonUserMessageFactory) factory).create("Hello, how are you?", "User");
        assertNotNull(message);
        assertEquals("Hello, how are you?", message.getContent());
        assertEquals("User", message.getRole());
    }

    @Test
    public void testBotMessageFactory() {
        MessageFactory factory = new BotMessageFactory();
        MessageInterface message = factory.create("I am fine, thank you!", "Bot");
        assertNotNull(message);
        assertEquals("I am fine, thank you!", message.getContent());
        assertEquals("Bot", message.getRole());
    }
}
