package entity.chat;

import entity.bot.Bot;
import entity.user.User;

/**
 * Factory for creating CommonUserChat objects.
 */
public class CommonUserChatFactory implements ChatFactory {

    @Override
    public CommonUserChat create() {
        return new CommonUserChat();
    }
}
