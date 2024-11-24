package entity.chat;
import entity.message.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class testChat {
    @Test
    public void testChatCreation() {
        Chat chat = new CommonUserChat();
        assertNotNull(chat);
    }

    @Test
    public void testAddUserInput() {
        Chat chat = new CommonUserChat();
        chat.addUserInput("Hello, how are you?");
        List<Message> userInputs = chat.getUserInputs();
        assertEquals(1, userInputs.size());
        assertEquals("Hello, how are you?", userInputs.get(0).getContent());
    }

    @Test
    public void testAddBotResponse() {
        Chat chat = new CommonUserChat();
        chat.addBotResponse("I am fine, thank you!");
        List<Message> botResponses = chat.getBotResponses();
        assertEquals(1, botResponses.size());
        assertEquals("I am fine, thank you!", botResponses.get(0).getContent());
    }

    @Test
    public void testGetUserInputs() {
        Chat chat = new CommonUserChat();
        chat.addUserInput("User message 1");
        chat.addUserInput("User message 2");
        List<Message> userInputs = chat.getUserInputs();
        assertEquals(2, userInputs.size());
        assertEquals("User message 1", userInputs.get(0).getContent());
        assertEquals("User message 2", userInputs.get(1).getContent());
    }

    @Test
    public void testGetBotResponses() {
        Chat chat = new CommonUserChat();
        chat.addBotResponse("Bot response 1");
        chat.addBotResponse("Bot response 2");
        List<Message> botResponses = chat.getBotResponses();
        assertEquals(2, botResponses.size());
        assertEquals("Bot response 1", botResponses.get(0).getContent());
        assertEquals("Bot response 2", botResponses.get(1).getContent());
    }
}
