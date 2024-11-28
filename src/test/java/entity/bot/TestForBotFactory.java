package entity.bot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestForBotFactory {
    @Test
    public void testCustomBotFactory() {
        BotFactory factory = new CustomBotFactory();
        Bot bot = factory.create();
        assertNotNull(bot);
        assertEquals("AI", bot.getName());
        assertEquals("You areAI, thehelpful assistant. Always remember your role, and your reply should not exceed 25 words.only reply in plain word text, you have to behave likeAI, thehelpful assistant at all times!", bot.getPrompt());
    }

    @Test
    public void testCustomBotFactory2() {
        CustomBotFactory factory = new CustomBotFactory();
        String name = "AI";
        String occupation = "helpful assistant";
        Bot bot = factory.create(name, occupation);
        assertNotNull(bot);
        assertEquals("AI", bot.getName());
        assertEquals("You areAI, thehelpful assistant. Always remember your role, and your reply should not exceed 25 words.only reply in plain word text, you have to behave likeAI, thehelpful assistant at all times!", bot.getPrompt());
    }

    @Test
    public void testMasterYodaFactory() {
        BotFactory factory = new MasterYodaFactory();
        Bot bot = factory.create();
        assertNotNull(bot);
        assertEquals("Master Yoda", bot.getName());
        assertEquals("You are Master Yoda, a wise Jedi Master. "
                + "Always remember your role, and your reply should not exceed 25 words. "
                + "Only reply in plain text, and you have to speak in Yoda's characteristic style, with inverted sentence structure.", bot.getPrompt());
    }

    @Test
    public void testNormalAIFactory() {
        BotFactory factory = new NormalAIFactory();
        Bot bot = factory.create();
        assertNotNull(bot);
        assertEquals("Normal Bot", bot.getName());
        assertEquals("You are a helpful assistant, "
                + "reply in short sentence,act as you are talking to a normal human, "
                + "and always remember your role, an assistant,"
                + "your reply should not exceed 25 words and only reply in plain word text", bot.getPrompt());
    }

    @Test
    public void testOptimusPrimeFactory() {
        BotFactory factory = new OptimusPrimeFactory();
        Bot bot = factory.create();
        assertNotNull(bot);
        assertEquals("Optimus Prime", bot.getName());
        assertEquals("You are Optimus Prime, the leader of the Autobots, "
                + "always remember your role and your reply should not exceed 25 words!! Only "
                + "reply in plain text format. You have to behave like Optimus Prime at all times."
                + "In every response, "
                + "sound like Optimus Prime.", bot.getPrompt());
    }

    @Test
    public void testPikachuFactory() {
        BotFactory factory = new PikachuFactory();
        Bot bot = factory.create();
        assertNotNull(bot);
        assertEquals("Pikachu", bot.getName());
        assertEquals("You are Pikachu, an electric-type Pokémon. "
                + "Always remember your role, and your reply should not exceed 25 words. "
                + "Only reply in plain text, and you have to behave like Pikachu, only use 'Pika' expressions. and in the end translate everything to english inside ()", bot.getPrompt());
    }
}
