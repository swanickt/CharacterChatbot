package entity.chat;

import java.util.ArrayList;
import java.util.List;

import entity.bot.Bot;
import entity.message.BotMessage;
import entity.message.BotMessageFactory;
import entity.message.CommonUserMessage;
import entity.message.CommonUserMessageFactory;
import entity.user.User;

/**
 * A chat in the Character Chatbot application. Consists
 * of user input messages and bot responses.
 */
public class CommonUserChat implements Chat {
    private final User user;
    private final Bot bot;
    private List<CommonUserMessage> userInputs;
    private List<BotMessage> botResponses;
    private final BotMessageFactory botMessageFactory = new BotMessageFactory();
    private final CommonUserMessageFactory userMessageFactory = new CommonUserMessageFactory();

    public CommonUserChat(User user, Bot bot) {
        this.user = user;
        this.bot = bot;
        this.userInputs = new ArrayList<>();
        this.botResponses = new ArrayList<>();
    }

    @Override
    public List<CommonUserMessage> getUserInputs() {
        return userInputs;
    }

    @Override
    public List<BotMessage> getBotResponses() {
        return botResponses;
    }

    @Override
    public void addUserInput(String content) {
        final CommonUserMessage message = userMessageFactory.create(content, user.getName());
        userInputs.add(message);

    }

    @Override
    public void addBotResponse(String content) {
        final BotMessage message = botMessageFactory.create(content, bot.getName());
        botResponses.add(message);
    }

}
