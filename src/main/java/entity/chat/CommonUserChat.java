package entity.chat;

import java.util.ArrayList;
import java.util.List;

import entity.bot.Bot;
import entity.message.*;
import entity.user.User;

/**
 * A chat in the Character Chatbot application. Consists
 * of user input messages and bot responses.
 */
public class CommonUserChat implements Chat {
    private List<Message> userInputs;
    private List<Message> botResponses;
    private final BotMessageFactory botMessageFactory = new BotMessageFactory();
    private final CommonUserMessageFactory userMessageFactory = new CommonUserMessageFactory();

    public CommonUserChat() {
        this.userInputs = new ArrayList<>();
        this.botResponses = new ArrayList<>();
    }

    @Override
    public List<Message> getUserInputs() {
        return userInputs;
    }

    @Override
    public List<Message> getBotResponses() {
        return botResponses;
    }

    @Override
    public void addUserInput(String content) {
        final Message message = new Message("user", content);
        userInputs.add(message);

    }

    @Override
    public void addBotResponse(String content) {
        final Message message = new Message("assistant", content);
        botResponses.add(message);
    }

}
