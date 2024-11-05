package entity.message;

/**
 * Factory for creating CommonUserMessage objects.
 */
public class CommonUserMessageFactory implements MessageFactory {

    @Override
    public CommonUserMessage create(String contents, String userName) {
        return new CommonUserMessage(contents, userName);
    }
}
